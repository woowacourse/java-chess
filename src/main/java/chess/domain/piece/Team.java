package chess.domain.piece;

import static chess.domain.position.BoardDirection.N;
import static chess.domain.position.BoardDirection.NE;
import static chess.domain.position.BoardDirection.NW;
import static chess.domain.position.BoardDirection.S;
import static chess.domain.position.BoardDirection.SE;
import static chess.domain.position.BoardDirection.SW;

import chess.domain.position.BoardDirection;
import chess.domain.position.Rank;
import java.util.List;

public enum Team {
    WHITE(List.of(NW, N, NE), Rank.TWO),
    BLACK(List.of(SW, S, SE), Rank.SEVEN);

    private final List<BoardDirection> forwardDirections;
    private final Rank initialPawnRank;

    Team(List<BoardDirection> forwardDirections, Rank initialPawnRank) {
        this.forwardDirections = forwardDirections;
        this.initialPawnRank = initialPawnRank;
    }

    public Rank getInitialPawnRank() {
        return initialPawnRank;
    }

    public boolean isTeamForwardDirectionsContains(BoardDirection direction) {
        return forwardDirections.contains(direction);
    }
}
