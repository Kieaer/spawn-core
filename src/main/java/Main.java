import arc.util.CommandHandler;
import mindustry.content.Blocks;
import mindustry.entities.type.Player;
import mindustry.gen.Call;
import mindustry.plugin.Plugin;
import mindustry.world.Block;

import static mindustry.Vars.world;

public class Main extends Plugin{
    public Main(){}

    @Override
    public void registerClientCommands(CommandHandler handler) {
        handler.<Player>register("spawn-core","<small/normal/big>", "Make new core", (arg, player) -> {
            // Check player is admin
            if(!player.isAdmin){
                player.sendMessage("[scarlet]You're not admin!");
                return;
            }

            // Core type
            Block core = Blocks.coreShard;
            switch(arg[0]){
                case "normal":
                    core = Blocks.coreFoundation;
                    break;
                case "big":
                    core = Blocks.coreNucleus;
                    break;
            }

            // Core spawn
            if(player.tileOn().breakable()) {
                Call.onConstructFinish(world.tile(player.tileX(),player.tileY()), core,0,(byte)0,player.getTeam(),false);
                player.sendMessage("[green]Core spawned!");
            } else {
                player.sendMessage("[scarlet]Core spawn failed!");
            }
        });
    }
}