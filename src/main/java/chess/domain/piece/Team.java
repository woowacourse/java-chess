package chess.domain.piece;

import static chess.domain.position.Direction.N;
import static chess.domain.position.Direction.NE;
import static chess.domain.position.Direction.NW;
import static chess.domain.position.Direction.S;
import static chess.domain.position.Direction.SE;
import static chess.domain.position.Direction.SW;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.List;

public enum Team {
    WHITE(List.of(NW, N, NE), Rank.TWO),
    BLACK(List.of(SW, S, SE), Rank.SEVEN);

    private final List<Direction> forwardDirections;
    private final Rank initialPawnRank;

    Team(List<Direction> forwardDirections, Rank initialPawnRank) {
        this.forwardDirections = forwardDirections;
        this.initialPawnRank = initialPawnRank;
    }

    public boolean isPositionOnTeamInitialPawnRank(Position position) {
        return position.isRankSameWith(initialPawnRank);
    }

    public boolean isTeamForwardDirectionsContains(Direction direction) {
        return forwardDirections.contains(direction);
    }

    public Team otherTeam() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }
}
