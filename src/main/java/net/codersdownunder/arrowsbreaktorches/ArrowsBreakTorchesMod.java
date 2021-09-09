package net.codersdownunder.arrowsbreaktorches;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TorchBlock;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("arrowsbreaktorches")
public class ArrowsBreakTorchesMod
{
    //private static final Logger LOGGER = LogManager.getLogger();

    public ArrowsBreakTorchesMod() {
        
        MinecraftForge.EVENT_BUS.register(this);
        
        MinecraftForge.EVENT_BUS.register(Events.class);
    }

    
    public static class Events {

    @SubscribeEvent
    public static void arrowHitEvent(ProjectileImpactEvent.Arrow event) {
        
        RayTraceResult ray = event.getRayTraceResult();
        BlockState state;
        BlockPos pos = event.getEntity().blockPosition();
        World world = event.getEntity().level;
        
        if (ray.getType() == RayTraceResult.Type.BLOCK) {
          state = world.getBlockState(new BlockPos(ray.getLocation().x(), ray.getLocation().y(), ray.getLocation().z()));
          
          if (state.getBlock() instanceof TorchBlock || state.getBlock() instanceof WallTorchBlock) {
          Block.updateOrDestroy(state, Blocks.AIR.defaultBlockState(), world, pos, 1, 0);
          //LOGGER.info("******* " + state);
          }
        }
        
        //if (ray.getType() == RayTraceResult.Type.BLOCK) {
        //    state = world.getBlockState(new BlockPos(ray.getLocation().x(), ray.getLocation().y(), ray.getLocation().z()));
            
        //    Block.updateOrDestroy(state, Blocks.TORCH.defaultBlockState(), world, pos.offset(0, 0.1, 0), 1, 0);
        //    //LOGGER.info("******* " + state);
        //    }
        }
       
    }

}
