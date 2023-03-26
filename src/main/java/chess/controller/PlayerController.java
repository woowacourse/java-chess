package chess.controller;

import chess.dao.PlayerDao;
import chess.domain.player.Player;
import chess.view.InputView;

public class PlayerController {

    public Player handle() {
        String name = InputView.readPlayerName();
        Player player = PlayerDao.findByName(name);

        if (player == null) {
            player = PlayerDao.create(name);
        }

        return player;
    }
}
