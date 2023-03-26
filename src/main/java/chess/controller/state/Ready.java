package chess.controller.state;

import chess.domain.game.Game;
import chess.view.OutputView;

public class Ready extends State {
    public Ready(final Game game) {
        super(game);
    }

    @Override
    public Running start() {
        final Game lastGame = lastGame();
        OutputView.printChessBoard(lastGame.getPieces());
        return new Running(lastGame);
    }
}
