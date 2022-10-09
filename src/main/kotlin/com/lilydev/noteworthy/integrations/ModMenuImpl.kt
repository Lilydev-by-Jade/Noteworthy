package com.lilydev.noteworthy.integrations

import com.lilydev.noteworthy.gui.NotesScreen
import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi

class ModMenuImpl : ModMenuApi {
    override fun getModConfigScreenFactory(): ConfigScreenFactory<*> = ConfigScreenFactory {
        parent -> NotesScreen(parent)
    }
}
