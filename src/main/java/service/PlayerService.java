package service;

import domain.player.Player;
import domain.player.PlayerName;
import repository.PlayerDao;

import java.sql.Connection;

public class PlayerService {

    private final PlayerDao playerDao;

    public PlayerService(final Connection connection) {
        this.playerDao = new PlayerDao(connection);
    }

    public Player roadPlayer(final String name) {
        final PlayerName playerName = new PlayerName(name);

        if (playerDao.exist(playerName)) {
            return new Player(playerName);
        }

        return playerDao.add(playerName);
    }

    public PlayerName findPlayerName(final String name) {
        return playerDao.findName(name)
                .orElseThrow(() -> new IllegalArgumentException("플레이어를 찾을 수 없습니댜."));
    }
}
