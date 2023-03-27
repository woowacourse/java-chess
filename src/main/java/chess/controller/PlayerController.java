package chess.controller;

import chess.dao.PlayerDao;
import chess.domain.player.Player;
import chess.view.InputView;

public class PlayerController {

    private final PlayerDao playerDao = new PlayerDao();

    public Player handle() {
        final String name = InputView.readPlayerName();
        playerDao.createIfNotExist(name);

        return playerDao.findByName(name)
                .orElseThrow(() -> new RuntimeException("플레이어 생성 실패"));
    }
}
