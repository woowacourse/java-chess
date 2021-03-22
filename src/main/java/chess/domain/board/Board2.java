package chess.domain.board;

import chess.domain.piece.PieceColor;
import chess.domain.position.PositionName;

public final class Board2 {

    public Board2() {

    }

    public static Board2 initiate() {
        // todo
        return new Board2();
    }

    public void move(final PositionName source, final PositionName target) {

    }

    // TODO
    public boolean isEnemyPiece(final PositionName source, final PieceColor enemyColor) {
        return false;
    }

    // TODO
    public boolean kingDead() {
        return false;
    }
}
