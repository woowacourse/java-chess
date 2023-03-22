package chess.domain.exception;

public class RankNotFoundException extends IllegalArgumentException {

    public RankNotFoundException() {
        super("존재하지 않는 Rank입니다.");
    }
}
