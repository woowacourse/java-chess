package chess.domain.piece;

import chess.domain.movement.Movement;
import chess.domain.position.Position;
import java.util.Map;
import java.util.Map.Entry;

public class Pawn extends Piece {

    private static final int WHITE_PAWN_FIRST_RANK = 2;
    private static final int BLACK_PAWN_FIRST_RANK = 7;

    public Pawn(Color color) {
        super(PieceType.PAWN, color);
    }

    @Override
    protected boolean filterMovement(Movement movement, Position source, Position target, Map<Position, Piece> pieces) {
        return movement.isSatisfied(getColor(), isFirstRank(getColor(), source), existEnemy(target, pieces));
    }

    private boolean isFirstRank(Color color, Position source) {
        if (color == Color.WHITE) {
            return source.rank() == WHITE_PAWN_FIRST_RANK;
        }
        return source.rank() == BLACK_PAWN_FIRST_RANK;
    }

    private boolean existEnemy(final Position target, final Map<Position, Piece> pieces) {
        Piece enemyPiece = pieces.getOrDefault(target, new Empty());
        return enemyPiece.isNotEmpty() && !enemyPiece.isSameColor(this.getColor());
    }

    @Override
    protected boolean filterObstacles(Position source, Position target, Entry<Position, Piece> entry) {
        if (isDiagonalMove(source, target) && entry.getValue().isNotEmpty()) {
            return entry.getValue().isSameColor(this.getColor());
        }
        return entry.getValue().isNotEmpty();
    }

    private boolean isDiagonalMove(Position source, Position target) {
        return source.rank() != target.rank() && source.file() != target.file();
    }
}
