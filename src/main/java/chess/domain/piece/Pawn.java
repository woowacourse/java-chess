package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public abstract class Pawn extends Piece {

    private static final List<Direction> WHITE_PAWN_DIRECTION = List.of(
            Direction.SAME_FILE_POSITIVE_RANK
    );
    private static final List<Direction> WHITE_PAWN_ATTACK_DIRECTION = List.of(
            Direction.POSITIVE_FILE_POSITIVE_RANK,
            Direction.NEGATIVE_FILE_POSITIVE_RANK
    );
    private static final List<Direction> BLACK_PAWN_DIRECTION = List.of(
            Direction.SAME_FILE_NEGATIVE_RANK
    );
    private static final List<Direction> BLACK_PAWN_ATTACK_DIRECTION = List.of(
            Direction.POSITIVE_FILE_NEGATIVE_RANK,
            Direction.NEGATIVE_FILE_NEGATIVE_RANK
    );

    public Pawn(Color color) {
        super(color, getPawnDirectionByColor(color));
    }

    private static List<Direction> getPawnDirectionByColor(Color color) {
        if (color == Color.WHITE) {
            return WHITE_PAWN_DIRECTION;
        }
        return BLACK_PAWN_DIRECTION;
    }

    @Override
    public boolean isAttackable(Position source, Position destination) {
        Direction direction = Direction.calculateBetween(source, destination);
        if (hasColorOf(Color.WHITE)) {
            return WHITE_PAWN_ATTACK_DIRECTION.contains(direction);
        }
        return BLACK_PAWN_ATTACK_DIRECTION.contains(direction);
    }
}
