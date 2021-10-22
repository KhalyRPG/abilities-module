package me.khaly.module.abilities.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import me.khaly.core.KhalyCore;
import me.khaly.core.rpg.classes.object.RPGClass;
import me.khaly.core.rpg.classes.object.RPGClassAbility;
import me.khaly.core.user.User;
import me.khaly.core.user.profile.Profile;

public class AbilityListener implements Listener {
	
	private KhalyCore core;
	
	public AbilityListener(KhalyCore core) {
		this.core = core;
	}
	
	@EventHandler
	public void onSneak(PlayerToggleSneakEvent event) {
		castAbility(event);
	}
	
	private void castAbility(PlayerEvent event) {
		Player player = event.getPlayer();
		User user = core.getUser(player);
		
		if(!user.hasProfile()) {
			return;
		}
		
		Profile profile = user.getProfile();
		RPGClass clazz = profile.getRPGClass();
		
		if(clazz == null) {
			return;
		}
		
		for(RPGClassAbility ability : clazz.getAbilities().values()) {
			if(event.getClass() == ability.getEventClass()) {
				boolean execute = ability.getPredicate().test(user, event);
				
				if(!execute) {
					return;
				}
				
				
				if(ability.getAction().test(user, event)) {
					player.sendMessage("§cYour class casted " + ability.getDisplayName() + " ability.");
				}
				break;
			}
		}
	}
	
}
