package chess.domain.piece;

import static chess.domain.position.BoardDirection.N;
import static chess.domain.position.BoardDirection.NE;
import static chess.domain.position.BoardDirection.NW;
import static chess.domain.position.BoardDirection.S;
import static chess.domain.position.BoardDirection.SE;
import static chess.domain.position.BoardDirection.SW;
import static chess.domain.position.Direction.DOWN;
import static chess.domain.position.Direction.UP;

import chess.domain.position.BoardDirection;
import chess.domain.position.Direction;
import chess.domain.position.Rank;
import java.util.List;

public enum Team {
    WHITE(UP, List.of(NW, N, NE), Rank.TWO),
    BLACK(DOWN, List.of(SW, S, SE), Rank.SEVEN);

    private final Direction direction;
    private final List<BoardDirection> teamForwardDirections;
    private final Rank initialPawnRank;

    Team(Direction direction, List<BoardDirection> teamForwardDirections, Rank initialPawnRank) {
        this.direction = direction;
        this.teamForwardDirections = teamForwardDirections;
        this.initialPawnRank = initialPawnRank;
    }

    public Direction getDirection() {
        return direction;
    }

    public Rank getInitialPawnRank() {
        return initialPawnRank;
    }

    public boolean isTeamForwardDirectionsContains(BoardDirection direction) {
        return teamForwardDirections.contains(direction);
    }

    public boolean isInitialPawnRankSameWith(Rank rank) {
        return initialPawnRank == rank;
    }
}
