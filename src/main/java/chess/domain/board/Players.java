package chess.domain.board;

public class Players {

    private static final int MIN_NAME_SIZE = 2;
    private static final int MAX_NAME_SIZE = 12;

    private final String whitePlayer;
    private final String blackPlayer;

    public Players(final String whitePlayer, final String blackPlayer) {
        validate(whitePlayer, blackPlayer);

        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
    }

    private void validate(String whitePlayer, String blackPlayer) {
        if (whitePlayer.length() < MIN_NAME_SIZE || blackPlayer.length() < MIN_NAME_SIZE
        || whitePlayer.length() > MAX_NAME_SIZE || blackPlayer.length() > MAX_NAME_SIZE) {
            throw new IllegalArgumentException("사용자 이름은 2자 이상 12자 이하입니다.");
        }
    }

    public String getWhitePlayer() {
        return whitePlayer;
    }

    public String getBlackPlayer() {
        return blackPlayer;
    }
}
