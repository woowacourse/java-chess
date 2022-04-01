package chess.domain.piece;

import static chess.domain.board.position.MoveDirection.DOWN;
import static chess.domain.board.position.MoveDirection.DOWN_LEFT;
import static chess.domain.board.position.MoveDirection.DOWN_RIGHT;
import static chess.domain.board.position.MoveDirection.LEFT;
import static chess.domain.board.position.MoveDirection.RIGHT;
import static chess.domain.board.position.MoveDirection.UP;
import static chess.domain.board.position.MoveDirection.UP_LEFT;
import static chess.domain.board.position.MoveDirection.UP_RIGHT;

import chess.constant.TargetType;
import chess.domain.board.position.MoveDirection;
import chess.domain.board.position.Position;
import java.util.List;

public class Queen extends Piece {

    private static final String EMBLEM = "Q";
    private static final double SCORE = 9;
    private static final List<MoveDirection> POSSIBLE_MOVE_DIRECTIONS = List.of(
            UP, DOWN, LEFT, RIGHT,
            UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT
    );

    public Queen(PieceTeam pieceTeam) {
        super(pieceTeam);
    }

    @Override
    public String getConcreteEmblem() {
        return EMBLEM;
    }

    @Override
    public boolean isMovable(Position from, Position to, TargetType targetType) {
        return super.isMovable(from, to, targetType, POSSIBLE_MOVE_DIRECTIONS);
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public String toString() {
        return "Queen{" +
                "pieceTeam=" + pieceTeam +
                '}';
    }
}
