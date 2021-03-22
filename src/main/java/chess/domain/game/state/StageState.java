package chess.domain.game.state;

import chess.domain.board.Board2;
import chess.domain.piece.PieceColor;
import chess.domain.position.PositionName;

public abstract class StageState implements GameState {

    private final Board2 board;

    protected StageState(final Board2 board) {
        this.board = board;
    }

    protected Board2 board() {
        return board;
    }

    protected void moveInBoard(final PositionName source, final PositionName target,
            final PieceColor currentTurnColor) {
        if (board.isEnemyPiece(source, currentTurnColor.reversed())) {
            throw new IllegalArgumentException("해당 체스말을 움직일 권한이 없습니다.");
        }
        board.move(source, target);
    }

    protected boolean kingDead() {
        return board.kingDead();
    }
}
