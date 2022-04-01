package chess.domain.piece;

import static chess.domain.board.position.MoveDirection.*;

import chess.constant.TargetType;
import chess.domain.board.position.MoveDirection;
import chess.domain.board.position.Position;
import java.util.List;

public class Knight extends Piece {

    private static final String EMBLEM = "N";
    private static final double SCORE = 2.5;
    private static final List<MoveDirection> POSSIBLE_MOVE_DIRECTIONS = List.of(
            TWO_UP_ONE_LEFT, TWO_UP_ONE_RIGHT,
            TWO_DOWN_ONE_LEFT, TWO_DOWN_ONE_RIGHT,
            TWO_LEFT_ONE_UP, TWO_LEFT_ONE_DOWN,
            TWO_RIGHT_ONE_UP, TWO_RIGHT_ONE_DOWN
    );

    public Knight(PieceTeam pieceTeam) {
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
}
