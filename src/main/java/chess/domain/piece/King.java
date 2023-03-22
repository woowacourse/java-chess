package chess.domain.piece;

import static chess.domain.piece.King.KingMovablePosition.X_MOVES;
import static chess.domain.piece.King.KingMovablePosition.Y_MOVES;
import static java.util.stream.Collectors.*;

import chess.domain.Position;
import chess.domain.Role;
import chess.domain.Team;
import java.util.List;
import java.util.stream.IntStream;

public class King extends Piece {
    private static final King WHITE = new King(Team.WHITE);
    private static final King BLACK = new King(Team.BLACK);

    private King(Team team) {
        super(Role.KING, team);
    }

    public static King of(Team team) {
        if (team == Team.WHITE) {
            return WHITE;
        }
        if (team == Team.BLACK) {
            return BLACK;
        }
        throw new IllegalArgumentException(INVALID_TEAM_EXCEPTION_MESSAGE);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return source.getDistanceTo(target) == 1;
    }

    public List<Position> getKingMovablePositions(Position source) {
        return IntStream.range(0, 8)
                .mapToObj(value -> new KingMovablePosition(source.getX() + X_MOVES.get(value),
                        source.getY() + Y_MOVES.get(value)))
                .filter(KingMovablePosition::isInBoardRange)
                .map(kingMovablePosition -> Position.of(kingMovablePosition.x, kingMovablePosition.y))
                .collect(toList());
    }

    static class KingMovablePosition {
        private static final int MIN_RANGE = 0;
        private static final int MAX_RANGE = 7;
        static final List<Integer> X_MOVES = List.of(0, 1, 1, 1, 0, -1, -1, -1);
        static final List<Integer> Y_MOVES = List.of(1, 1, 0, -1, -1, -1, 0, 1);

        private final int x;
        private final int y;

        public KingMovablePosition(int x, int y) {
            this.x = x;
            this.y = y;
        }

        boolean isInBoardRange() {
            return isInRange(x) && isInRange(y);
        }

        private boolean isInRange(int value) {
            return value >= MIN_RANGE && value <= MAX_RANGE;
        }
    }
}
