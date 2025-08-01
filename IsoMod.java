package com.billythenarwhal;

import com.billythenarwhal.effect.ModEffects;
import com.billythenarwhal.item.ModItems;
import com.billythenarwhal.item.ModToolMaterials;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IsoMod implements ModInitializer {
	public static final String MOD_ID = "isomod";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static int renderChance;



	public static final EntityType<WitherEntity> RAINBOW_WITHER =
			Registry.register(
					Registries.ENTITY_TYPE,
					new Identifier("isomod", "rainbow_wither"),
					FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, (EntityType<WitherEntity> entityType, World world) -> {
						WitherEntity witherEntity = new WitherEntity(entityType, world);
						witherEntity.setAiDisabled(true);
						return witherEntity;
					}).dimensions(EntityType.WITHER.getDimensions()).build()
			);







	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		renderChance = 2;

		ModToolMaterials.registerToolMaterials();
		ModItems.registerModItems();
		ModEffects.registerEffects();





		LOGGER.info("Hello Fabric world!");
	}

















	}


}
