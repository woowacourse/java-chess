package chess.domain.team;

public class TeamNotFoundException extends RuntimeException {

    public TeamNotFoundException(final String value) {
        super(String.format("%s인 Team을 찾을 수 없습니다.", value));
    }
}
