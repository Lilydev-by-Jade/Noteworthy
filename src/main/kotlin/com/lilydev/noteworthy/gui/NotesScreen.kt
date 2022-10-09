package com.lilydev.noteworthy.gui

import com.lilydev.noteworthy.Noteworthy
import dev.lambdaurora.spruceui.Position
import dev.lambdaurora.spruceui.screen.SpruceScreen
import dev.lambdaurora.spruceui.widget.SpruceButtonWidget
import dev.lambdaurora.spruceui.widget.SpruceLabelWidget
import dev.lambdaurora.spruceui.widget.container.SpruceContainerWidget
import dev.lambdaurora.spruceui.widget.container.tabbed.SpruceTabbedWidget
import dev.lambdaurora.spruceui.widget.container.tabbed.SpruceTabbedWidget.ContainerFactory
import dev.lambdaurora.spruceui.widget.text.SpruceTextAreaWidget
import dev.lambdaurora.spruceui.widget.text.SpruceTextFieldWidget
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.Screen
import net.minecraft.text.Text
import net.minecraft.util.Formatting


class NotesScreen(private val parent: Screen) : SpruceScreen(Text.translatable("noteworthy.gui.screen.notes.title")) {

    private var tabbedWidget: SpruceTabbedWidget? = null

    override fun init() {
        super.init()

        tabbedWidget = SpruceTabbedWidget(
            Position.origin(),
            width, height, null, 100.coerceAtLeast(width / 8), 0
        )

        tabbedWidget!!.addTabEntry(
            Text.translatable("noteworthy.gui.create_note"), null,
            tabContainerBuilder { width: Int, height: Int -> null })

        tabbedWidget!!.addSeparatorEntry(this.title)


        addDrawableChild(tabbedWidget)

    }

    private fun tabContainerBuilder(innerFactory: ContainerFactory): ContainerFactory {
        return ContainerFactory { width: Int, height: Int -> buildCreateNoteTabContainer(width, height, innerFactory) }
    }

    private fun buildCreateNoteTabContainer(width: Int, height: Int, factory: ContainerFactory): SpruceContainerWidget {
        val container = SpruceContainerWidget(Position.origin(), width, height)
        val label = SpruceLabelWidget(Position.of(0, 18), Text.translatable("noteworthy.title").formatted(Formatting.WHITE), width)
        label.isCentered = true
        container.addChild(label)


        val textAreaWidth: Int = width - 30
        val textAreaHeight: Int = (height - 80 * 2.5).toInt()

        val getCoordsButtonWidth = 155


        val textArea = SpruceTextAreaWidget(
            Position.of((container.width / 2) - (textAreaWidth / 2), (container.height / 2) - (textAreaHeight / 2) + 20),
            textAreaWidth,
            textAreaHeight,
            null
        )

        val textAreaLabel = SpruceLabelWidget(
            Position.of(textArea.x, textArea.y - 15),
            Text.translatable("noteworthy.gui.label.text_area").formatted(Formatting.WHITE),
            100
        )

        val noteTitleTextField = SpruceTextFieldWidget(
            Position.of(textArea.x, textAreaLabel.y - 30),
            300,
            20,
            null
        )

        val noteTitleTextFieldLabel = SpruceLabelWidget(
            Position.of(textArea.x, noteTitleTextField.y - 15),
            Text.translatable("noteworthy.gui.label.note_title").formatted(Formatting.WHITE),
            100
        )

        val pasteCoordsButton = SpruceButtonWidget(
            Position.of(textArea.width - getCoordsButtonWidth + 15, noteTitleTextField.y),
            getCoordsButtonWidth,
            20,
            Text.translatable("noteworthy.gui.button.paste_coords")
        ) { Noteworthy.LOGGER.info("pressed paste coordinates") }


        if (MinecraftClient.getInstance().world == null) {
            pasteCoordsButton.isActive = false
        }

        container.addChild(textArea)
        container.addChild(textAreaLabel)

        container.addChild(pasteCoordsButton)

        container.addChild(noteTitleTextField)
        container.addChild(noteTitleTextFieldLabel)


        container.addChild(SpruceButtonWidget(
            Position.of(this, width / 2 - 155, height - 29), 150, 20,
            Text.translatable("noteworthy.gui.button.cancel")
        ) { client!!.setScreen(parent) })

        container.addChild(SpruceButtonWidget(
            Position.of(this, width / 2 - 155 + 160, height - 29), 150, 20,
            Text.translatable("noteworthy.gui.button.create")
        ) { Noteworthy.LOGGER.info("created thing!") })
        return container
    }


}
