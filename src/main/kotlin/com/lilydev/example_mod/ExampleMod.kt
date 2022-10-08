package com.lilydev.example_mod

import org.quiltmc.loader.api.ModContainer
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object ExampleMod : ModInitializer {

    const val MOD_ID: String = "example_mod"
    const val MOD_NAME: String = "Example Mod"

    @JvmField
    val LOGGER: Logger = LoggerFactory.getLogger(MOD_NAME)

    override fun onInitialize(mod: ModContainer) {
        LOGGER.info("Hello Quilt world from ${mod.metadata().name()}!")
    }
}
