package chess.domain.game.gamestate;

import chess.domain.board.Board;

abstract class BeforeRunning extends Started {

    BeforeRunning(Board board) {
        super(board);
    }

    @Override
    public State startGame() {
        return new RunningWhite(Board.createInitializedBoard());
    }
}
