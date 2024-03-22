package chess.domain.piece.type;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;

public final class Knight extends Piece {
    public Knight(PieceColor color) {
        super(color, PieceType.KNIGHT);
    }

    @Override
    public boolean isInMovableRange(Position source, Position target) {
        int fileDistance = source.calculateFileDistanceTo(target);
        int rankDistance = source.calculateRankDistanceTo(target);

        return (fileDistance == 1 && rankDistance == 2) || (fileDistance == 2 && rankDistance == 1);
    }
}
