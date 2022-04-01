package chess.domain.piece;

import static chess.domain.board.position.MoveDirection.*;

import chess.constant.SquareType;
import chess.domain.board.position.MoveDirection;
import chess.domain.board.position.Position;
import java.util.List;

public class King extends Piece {

    private static final String EMBLEM = "K";
    private static final double SCORE = 0;
    private static final List<MoveDirection> POSSIBLE_MOVE_DIRECTIONS = List.of(
            UP, DOWN, LEFT, RIGHT,
            UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT
    );

    public King(PieceTeam pieceTeam) {
        super(pieceTeam);
    }

    @Override
    public String getConcreteEmblem() {
        return EMBLEM;
    }

    @Override
    public boolean isMovable(Position from, Position to, SquareType squareType) {
        return super.isMovable(from, to, squareType, POSSIBLE_MOVE_DIRECTIONS) &&
                from.fileDistance(to) <= 1 &&
                from.rankDistance(to) <=1;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
