package chess.domain.exceptions;

public class SameTeamException extends RuntimeException {

    public SameTeamException() {
        super("같은 팀의 말입니다.");
    }
}
