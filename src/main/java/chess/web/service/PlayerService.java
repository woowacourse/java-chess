package chess.web.service;

import chess.web.dao.PlayerDao;
import chess.web.dto.PlayerDto;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PlayerService {

    private final PlayerDao playerDao = new PlayerDao();

    public Map<String, Object> login(String name) {
        Map<String, Object> data = new HashMap<>();
        Optional<PlayerDto> playerDto = playerDao.find(name);
        if (playerDto.isEmpty()) {
            playerDao.save(name);
        }
        data.put("player", playerDao.find(name).get());
        return data;
    }
}
