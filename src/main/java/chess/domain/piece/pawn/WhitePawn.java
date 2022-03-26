package chess.domain.piece.pawn;

import static chess.domain.Color.WHITE;
import static chess.domain.direction.Direction.UP;
import static chess.domain.direction.Direction.UP_LEFT;
import static chess.domain.direction.Direction.UP_RIGHT;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.Position;
import chess.domain.direction.Direction;
import chess.domain.piece.Piece;
import java.util.Arrays;
import java.util.List;

public final class WhitePawn extends Pawn {

    private static final int FIRST_MOVABLE_COUNT = 2;
    private static final int NORMAL_MOVABLE_COUNT = 1;

    private static final Color WHITE_PAWN_COLOR = WHITE;
    private static final Direction MOVE_DIRECTION = UP;
    private static final List<Direction> MOVE_DIRECTION_TO_ENEMY = Arrays.asList(UP_RIGHT, UP_LEFT);

    private WhitePawn(int movableCount) {
        super(WHITE_PAWN_COLOR, movableCount, MOVE_DIRECTION, MOVE_DIRECTION_TO_ENEMY);
    }

    public WhitePawn() {
        this(FIRST_MOVABLE_COUNT);
    }

    @Override
    public Piece move(Position source, Position target, ChessBoard chessBoard) {
        super.move(source, target, chessBoard);
        return new WhitePawn(NORMAL_MOVABLE_COUNT);
    }
}
