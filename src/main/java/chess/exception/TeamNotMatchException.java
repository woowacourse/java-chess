package chess.exception;

import chess.domain.piece.Team;

public class TeamNotMatchException extends PieceCanNotMoveException {
    private static final String ERROR_MESSAGE = "팀의 말을 선택해주세요.";

    public TeamNotMatchException(Team team) {
        super(team.name() + ERROR_MESSAGE);
    }
}
