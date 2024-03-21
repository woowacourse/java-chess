package chess.domain;

import java.util.Deque;

public class CandidatePositions {

    private final Deque<Position> positions;

    public CandidatePositions(Deque<Position> positions) {
        this.positions = positions;
    }
}
