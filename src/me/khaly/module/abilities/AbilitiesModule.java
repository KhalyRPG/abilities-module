package me.khaly.module.abilities;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

import me.khaly.core.misc.Classes;
import me.khaly.core.module.Module;
import me.khaly.core.rpg.classes.object.RPGClass;
import me.khaly.module.abilities.ability.BerserkerModeAbility;
import me.khaly.module.abilities.listener.AbilityListener;

public class AbilitiesModule extends Module {
	
	private List<Listener> listeners;
	
	public AbilitiesModule() {
		super("Abilities", "abilities", 0.1F);
		this.setAuthor("Khaly");
		this.setPersistent(true);
		
		this.listeners = new ArrayList<>();
	}

	@Override
	public void load() {
		registerListeners(new AbilityListener(getProvider()));
		
		RPGClass berserk = Classes.getRPGClass("berserk");
		if(berserk != null) {
			berserk.addAbility(new BerserkerModeAbility());
		}
	}

	@Override
	public void unload() {
		for(int i = 0; i < listeners.size(); i++) {
			Listener listener = listeners.remove(i);
			HandlerList.unregisterAll(listener);
		}
	}
	
	private void registerListeners(Listener...listeners) {
		PluginManager manager = Bukkit.getPluginManager();
		for(Listener listener : listeners) {
			manager.registerEvents(listener, getProvider());
			AbilitiesModule.this.listeners.add(listener);
		}
	}
	
}
