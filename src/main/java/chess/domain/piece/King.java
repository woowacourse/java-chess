package chess.domain.piece;

import chess.domain.board.position.Direction;
import chess.domain.board.position.Position;
import chess.domain.piece.attribute.Name;
import chess.domain.piece.attribute.Team;
import java.util.List;

public final class King extends AbstractPiece {
    private static final String NO_MOVE_MESSAGE = "킹이 이동할 수 없는 위치입니다.";
    private static final double SCORE = 0;

    public King(Team team) {
        super(new Name("K"), team);
    }

    @Override
    public boolean canMove(Piece targetPiece, Position from, Position to) {
        List<Direction> directions = Direction.royalDirection(team);
        if (Direction.isInvalidDistance(from, to, directions)) {
            throw new IllegalArgumentException(NO_MOVE_MESSAGE);
        }
        return true;
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
