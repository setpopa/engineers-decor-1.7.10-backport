package wile.engineersdecor.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import wile.engineersdecor.client.render.DecorRenderHandler;

public class ClientProxy extends CommonProxy
{
    public static int decorRenderId = 0;

    @Override
    public void preInit(final FMLPreInitializationEvent event)
    {
        super.preInit(event);
        decorRenderId = RenderingRegistry.getNextAvailableRenderId();
    }

    @Override
    public void init(final FMLInitializationEvent event)
    {
        super.init(event);
        RenderingRegistry.registerBlockHandler(new DecorRenderHandler());
    }

    @Override
    public int getDecorRenderId()
    {
        return decorRenderId;
    }
}
