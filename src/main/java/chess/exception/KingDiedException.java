package chess.exception;

import chess.domain.piece.Team;

public class KingDiedException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "의 왕이 죽었습니다.";

    public KingDiedException(Team team) {
        super(team.name() + DEFAULT_MESSAGE);
    }
}
