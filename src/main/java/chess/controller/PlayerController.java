package chess.controller;

import chess.dao.PlayerDao;
import chess.domain.player.Player;
import chess.view.InputView;

public class PlayerController {

    public Player handle() {
        final String name = InputView.readPlayerName();
        final Player player = PlayerDao.findByName(name);

        return player;
    }
}
