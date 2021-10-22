package me.khaly.module.abilities.ability;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import me.khaly.core.enums.StatAttribute;
import me.khaly.core.user.attributes.modifier.StatModifier;
import me.khaly.core.user.attributes.stat.UserAttribute;
import me.khaly.module.abilities.abstraction.AdaptedRPGClassAbility;
import me.khaly.module.abilities.enums.AllowedEvent;

public class BerserkerModeAbility extends AdaptedRPGClassAbility {

	public BerserkerModeAbility() {
		super("berserker_mode", "Berserker Mode", (user, pureEvent) -> {
			PlayerToggleSneakEvent event = (PlayerToggleSneakEvent) pureEvent;
			Player player = event.getPlayer();
			if(player.isSneaking()) {
				return true;
			}
			return false;
		}, AllowedEvent.PLAYER_TOGGLE_SNEAK_EVENT);
		
		this.setAction((user, pureEvent) -> {
			UserAttribute attribute = user.getAttribute(StatAttribute.MELEE_DAMAGE);
			long currentTimestamp = System.currentTimeMillis();
			
			if(attribute.hasModifier("berserk_mode")) {
				return false;
			}
			
			StatModifier modifier = new StatModifier("berserk_mode", (value) -> {
				if(System.currentTimeMillis() > currentTimestamp + (5 * 1000)) {
					attribute.removeModifier("berserk_mode");
					value.set(0);
					return;
				}
				
				value.set(user.getProfile().getLevel() * 3);
			});
			
			attribute.addModifier(modifier);
			return true;
		});
	}

}
