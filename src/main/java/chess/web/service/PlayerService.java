package chess.web.service;

import chess.web.dao.PlayerDao;
import chess.web.dto.PlayerDto;
import java.util.Optional;

public class PlayerService {

    private final PlayerDao playerDao = new PlayerDao();

    public PlayerDto login(String name) {
        Optional<PlayerDto> playerDto = playerDao.find(name);
        if (playerDto.isEmpty()) {
            playerDao.save(name);
        }
        return playerDao.find(name).get();
    }
}
