package com.people;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "People Plugin"
)
public class PeoplePlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	@Inject
	private PeopleConfig config;

	private PeopleData person;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Person plugin starter");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("People plugin stopped!");
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged configChanged) {
		if (configChanged.getKey().equals("spawnPerson")) {
			if (config.spawnPerson()) {
				log.info("Spawn person - true");
				spawnPerson();
			} else {
				log.info("Spawn person - false");
				removePerson();
			}
		}
	}

	public void spawnPerson() {
		person = new PeopleData(client, clientThread);
		if (person != null) {
			person.load();
		}
	}

	public void removePerson() {
		if (person != null) {
			person.unload();
		}
	}

	@Provides
	PeopleConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(PeopleConfig.class);
	}
}

