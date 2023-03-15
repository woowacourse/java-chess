package chess.domain.piece.type;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.WayPointsWithCondition;
import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;

import java.util.Collections;

public class King extends Piece {

    public King(final Color color, final PiecePosition piecePosition) {
        super(color, piecePosition);
    }

    @Override
    protected WayPointsWithCondition wayPointsWithCondition(final Path path) {
        if (path.isUnitDistance()) {
            return WayPointsWithCondition.possible(Collections.emptyList());
        }
        return WayPointsWithCondition.impossible();
    }
}
