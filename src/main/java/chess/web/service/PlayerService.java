package chess.web.service;

import chess.web.dao.PlayerDao;
import chess.web.dto.PlayerDto;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PlayerService {

    private static final int NAME_MIN_SIZE = 1;
    private static final int NAME_MAX_SIZE = 12;
    private static final String ERROR_NAME_SIZE = "닉네임 길이는 1자 이상, 12자 이하입니다.";
    private final PlayerDao playerDao = new PlayerDao();

    public Map<String, Object> login(String name) {
        Map<String, Object> data = new HashMap<>();
        validateNameSize(name);
        Optional<PlayerDto> playerDto = playerDao.find(name);
        if (playerDto.isEmpty()) {
            playerDao.save(name);
        }
        data.put("player", playerDao.find(name).get());
        return data;
    }

    private void validateNameSize(String name) {
        if (name.length() < NAME_MIN_SIZE || name.length() > NAME_MAX_SIZE) {
            throw new IllegalArgumentException(ERROR_NAME_SIZE);
        }
    }
}
