package chess.domain.game.state;

import chess.domain.board.Board;

public abstract class Preparing extends Started {
    public Preparing(Board board) {
        super(board);
    }

    @Override
    public String finishReason() {
        throw new IllegalStateException("아직 게임이 종료되지 않았습니다");
    }
}
