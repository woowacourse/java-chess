package chess.domain.piece.pawn;

import static chess.domain.direction.Direction.DOWN;
import static chess.domain.direction.Direction.DOWN_LEFT;
import static chess.domain.direction.Direction.DOWN_RIGHT;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.Position;
import chess.domain.direction.Direction;
import chess.domain.piece.Piece;
import java.util.Arrays;
import java.util.List;

public final class BlackPawn extends Pawn {

    private static final int FIRST_MOVABLE_COUNT = 2;
    private static final int NORMAL_MOVABLE_COUNT = 1;

    private static final Color BLACK_PAWN_COLOR = Color.BLACK;
    private static final Direction MOVE_DIRECTION = DOWN;
    private static final List<Direction> MOVE_DIRECTION_TO_ENEMY = Arrays.asList(DOWN_RIGHT, DOWN_LEFT);

    private BlackPawn(int movableCount) {
        super(BLACK_PAWN_COLOR, movableCount, MOVE_DIRECTION, MOVE_DIRECTION_TO_ENEMY);
    }

    public BlackPawn() {
        this(FIRST_MOVABLE_COUNT);
    }

    @Override
    public Piece move(Position source, Position target, ChessBoard chessBoard) {
        super.move(source, target, chessBoard);
        return new BlackPawn(NORMAL_MOVABLE_COUNT);
    }
}
