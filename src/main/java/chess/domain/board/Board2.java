package chess.domain.board;

import chess.domain.piece.PieceColor;
import chess.domain.position.Position;
import chess.domain.position.PositionName;
import java.util.HashMap;
import java.util.Map;

public final class Board2 {

    private final Map<PositionName, Position> boardPositions;

    public Board2(Map<PositionName, Position> boardPositions) {
        this.boardPositions = boardPositions;
    }

    public static Board2 initiate() {
        // todo
        return new Board2(new HashMap<>());
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
