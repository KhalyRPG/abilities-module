package me.khaly.module.abilities.abstraction;

import java.util.function.BiPredicate;

import org.bukkit.event.Event;

import me.khaly.core.rpg.classes.object.RPGClassAbility;
import me.khaly.core.user.User;
import me.khaly.module.abilities.enums.AllowedEvent;

public abstract class AdaptedRPGClassAbility extends RPGClassAbility {

	public AdaptedRPGClassAbility(String id, String displayName, BiPredicate<User, Event> predicate, AllowedEvent event) {
		super(id, displayName, predicate, event.getEventClass());
	}

}
