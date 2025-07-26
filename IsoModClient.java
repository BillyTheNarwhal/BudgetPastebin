package com.billythenarwhal;

import com.billythenarwhal.item.ModItems;
import com.billythenarwhal.networking.ModPackets;
import com.billythenarwhal.satin.RainbowWitherEntityRenderer;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import ladysnake.satin.api.event.EntitiesPreRenderCallback;
import ladysnake.satin.api.event.ShaderEffectRenderCallback;
import ladysnake.satin.api.managed.ManagedCoreShader;
import ladysnake.satin.api.managed.ManagedShaderEffect;
import ladysnake.satin.api.managed.ShaderEffectManager;
import ladysnake.satin.api.managed.uniform.Uniform1f;
import ladysnake.satin.api.managed.uniform.Uniform4f;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.util.Identifier;

import java.io.IOException;

import static com.billythenarwhal.IsoMod.RAINBOW_WITHER;

public class IsoModClient implements ClientModInitializer {

    public static boolean shouldShader = false;
    public static  ManagedShaderEffect testShader = ShaderEffectManager.getInstance().manage(new Identifier(IsoMod.MOD_ID, "shaders/post/blit.json"));
    public static ManagedCoreShader testShader2 = ShaderEffectManager.getInstance().manageProgram(new Identifier(IsoMod.MOD_ID, "rainbow"));


    private static final Uniform1f uniformSTime = testShader2.findUniform1f("STime");

    private static final Uniform4f color = testShader.findUniform4f("ColorModulate");


    public static void randomColour(){
        color.set((float) Math.random(), (float) Math.random(), (float) Math.random(), 1.0f);

    }



    private static int ticks;


    @Override
    public void onInitializeClient() {
        ModPackets.registerS2CPackets();

        FabricDefaultAttributeRegistry.register(RAINBOW_WITHER, WitherEntity.createWitherAttributes());
        EntityRendererRegistry.INSTANCE.register(RAINBOW_WITHER, (context) -> new RainbowWitherEntityRenderer(context));


        ClientTickCallback.EVENT.register(client -> ticks++);
        EntitiesPreRenderCallback.EVENT.register((camera, frustum, tickDelta) -> uniformSTime.set((ticks + tickDelta) * 0.05f));


        ShaderEffectRenderCallback.EVENT.register( (tickDelta) ->{
            if (shouldShader) {
                testShader.render(tickDelta);
            }
                MinecraftClient client = MinecraftClient.getInstance();
                client.getFramebuffer().beginWrite(true);
                RenderSystem.enableBlend();
                RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SrcFactor.ZERO, GlStateManager.DstFactor.ONE);
                client.getFramebuffer().beginWrite(true);
                RenderSystem.disableBlend();

        });



    }




}
