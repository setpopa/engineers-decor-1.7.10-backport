package wile.engineersdecor;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import org.apache.logging.log4j.Logger;
import wile.engineersdecor.proxy.CommonProxy;

@Mod(
    modid = ModEngineersDecor.MODID,
    name = ModEngineersDecor.MODNAME,
    version = ModEngineersDecor.MODVERSION,
    dependencies = "required-after:Forge@[10.13.4.1614,);after:ImmersiveEngineering"
)
public class ModEngineersDecor
{
    public static final String MODID = "engineersdecor";
    public static final String MODNAME = "Engineer's Decor";
    public static final String MODVERSION = "1.1.5-1.7.10-alpha01";

    @Mod.Instance(MODID)
    public static ModEngineersDecor instance;

    @SidedProxy(
        clientSide = "wile.engineersdecor.proxy.ClientProxy",
        serverSide = "wile.engineersdecor.proxy.CommonProxy"
    )
    public static CommonProxy proxy;

    public static Logger logger;

    public static final CreativeTabs CREATIVE_TAB_ENGINEERSDECOR = new CreativeTabs("tabengineersdecor")
    {
        @Override
        public Item getTabIconItem()
        {
            if (ModContent.SIGN_MODLOGO != null) {
                return ModContent.SIGN_MODLOGO;
            }
            return Items.iron_ingot;
        }
    };

    @EventHandler
    public void preInit(final FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        logger.info(MODNAME + ": Version " + MODVERSION + ".");
        proxy.preInit(event);
        ModContent.preInit();
    }

    @EventHandler
    public void init(final FMLInitializationEvent event)
    {
        proxy.init(event);
        ModContent.init();
    }

    @EventHandler
    public void postInit(final FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
        ModContent.postInit();
    }
}
