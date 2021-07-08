package cf.thdisstudio;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLDataException;
import java.sql.SQLException;

public final class hellow extends JavaPlugin implements Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (label.equalsIgnoreCase("사업자")) {
            if (args.length == 0) {
                if (player.hasPermission("Thdis.CityHall.job.Enrollment")) {
                    player.sendMessage("§c§l/사업자 등록 [닉네임]");
                } else {
                    player.sendMessage("§4당신의 권한이 없습니다.");
                }
            } else {
                if (!player.hasPermission("Thdis.CityHall.job.Enrollment")) {
                    player.sendMessage("§4당신의 권한이 없습니다.");
                } else {
                    if (args[0].equals("등록")) {
                        if (args[1] == null) {
                            player.sendMessage("§c§l플레이어의 닉네임 적어주세요.");
                        } else {
                            try {
                                if(!new sql().is_player_frist_join(player.getUniqueId().toString())){
                                    new sql().savee(args[1],player.getUniqueId().toString());
                                    player.sendMessage("§c§l등록 완료");
                                }else
                                    player.sendMessage("§c§l이미 등록 되어있습니다");
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this,this);
    }
    @EventHandler
    public void join(PlayerJoinEvent event) throws SQLException {
        if(!new sql().is_player_frist_join(event.getPlayer().getUniqueId().toString()))
            new sql().save(event.getPlayer().getUniqueId().toString(), event.getPlayer().getDisplayName(), event.getPlayer().getAddress().getAddress().getHostAddress());
    }
}