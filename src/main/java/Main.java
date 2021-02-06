import arc.util.CommandHandler;
import mindustry.content.Blocks;
import mindustry.gen.Call;
import mindustry.gen.Playerc;
import mindustry.mod.Plugin;
import mindustry.world.Block;

import static mindustry.Vars.world;

public class Main extends Plugin {
    public Main(){}

    @Override
    public void registerClientCommands(CommandHandler handler) {
        handler.<Playerc>register("spawn-core","<small/normal/big>", "Make new core", (arg, player) -> {
            // Check player is admin
            if(!player.admin()){
                player.sendMessage("[scarlet]You're not admin!");
                return;
            }

            // Core type
            Block core = switch (arg[0]) {
                case "normal" -> Blocks.coreFoundation;
                case "big" -> Blocks.coreNucleus;
                default -> Blocks.coreShard;
            };

            // Core spawn
            if(player.tileOn().breakable()) {
                Call.constructFinish(world.tile(player.tileX(),player.tileY()), core, player.unit(), (byte)0,player.team(),false);
                player.sendMessage("[green]Core spawned!");
            } else {
                player.sendMessage("[scarlet]Core spawn failed!");
            }
        });
    }
}