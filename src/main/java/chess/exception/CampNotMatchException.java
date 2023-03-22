package chess.exception;

import chess.domain.piece.Team;

public class CampNotMatchException extends PieceCanNotMoveException{
    private static final String ERROR_MESSAGE = "팀의 말을 선택해주세요.";

    public CampNotMatchException(final Team team) {
        super(team.name()+ERROR_MESSAGE);
    }
}
