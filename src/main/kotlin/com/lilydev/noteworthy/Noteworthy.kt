package com.lilydev.noteworthy

import org.quiltmc.loader.api.ModContainer
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object Noteworthy : ClientModInitializer {

    const val MOD_ID: String = "noteworthy"
    const val MOD_NAME: String = "Noteworthy"

    @JvmField
    val LOGGER: Logger = LoggerFactory.getLogger(MOD_NAME)

    override fun onInitializeClient(mod: ModContainer) {
        LOGGER.info("Hello Quilt world from ${mod.metadata().name()}!")
    }
}
