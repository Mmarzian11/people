package com.people;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.coords.LocalPoint;
import net.runelite.client.callback.ClientThread;

@Slf4j
public class PeopleData {
    private RuneLiteObject object;

    private Client client;

    private ClientThread thread;

    private Model model;

    public PeopleData(Client client, ClientThread thread) {
        this.client = client;

        this.thread = thread;

        this.model = client.getLocalPlayer().getModel();

        object = client.createRuneLiteObject();

        thread.invokeLater(() -> {
            boolean active = false;
            object.setActive(active);

            LocalPoint point = client.getLocalPlayer().getLocalLocation();
            int plane = client.getPlane();
            object.setLocation(point, plane);

            log.info(model.toString());
            object.setModel(model);

            Animation animation = client.loadAnimation(client.getLocalPlayer().getAnimation());
            object.setAnimation(animation);

            boolean shouldLoop = true;
            object.setShouldLoop(shouldLoop);
        });
    }

    public void load() {
        thread.invokeLater(() -> object.setActive(true));
    }

    public void unload() {
        thread.invokeLater(() -> object.setActive(false));
    }
}

