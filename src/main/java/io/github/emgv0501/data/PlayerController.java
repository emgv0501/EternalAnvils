package io.github.emgv0501.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerController {
    private final List<UUID> playersList;

    public PlayerController(){
        playersList = new ArrayList<>();

    }

    public void addPlayer(UUID uuid){
        playersList.add(uuid);
    }
    public void removePlayer(UUID uuid){
        playersList.remove(uuid);
    }

    public boolean checkPlayers(UUID uuid){
        if (playersList.contains(uuid)){
            return true;
        } else return false;
    }


}
