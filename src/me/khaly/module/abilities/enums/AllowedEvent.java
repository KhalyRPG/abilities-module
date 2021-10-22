package me.khaly.module.abilities.enums;

import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public enum AllowedEvent {
	PLAYER_TOGGLE_SNEAK_EVENT(PlayerToggleSneakEvent.class)
	;
	private Class<? extends Event> clazz;
	AllowedEvent(Class<? extends Event> clazz) {
		this.clazz = clazz;
	}
	
	public Class<? extends Event> getEventClass() {
		return clazz;
	}
}
