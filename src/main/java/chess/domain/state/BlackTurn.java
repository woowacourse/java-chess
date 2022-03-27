package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public class BlackTurn extends Turn {

    private static final Color color = Color.BLACK;

    BlackTurn(final Board board) {
        super(board);
    }

    @Override
    public State move(final Position from, final Position to) {
        checkValidPosition(from, to, color);
        board.move(from, to);

        if (isEnded(color)) {
            return new Ready(board);
        }
        return new WhiteTurn(board);
    }

    @Override
    public void status() {
        throw new IllegalStateException("게임이 종료된 이후 점수 조회가 가능합니다.");
    }
}
