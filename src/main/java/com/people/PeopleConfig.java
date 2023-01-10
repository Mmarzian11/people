package com.people;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("people")
public interface PeopleConfig extends Config
{
	@ConfigItem(
			keyName = "spawnPerson",
			name = "Spawn Person",
			description = "Toggle to spawn a person"
	)
	default boolean spawnPerson() {return false;}
}
