package chess.domain.game;

public abstract class Started implements State {
    protected static final String MESSAGE_UNSUPPORTED = "명령을 수행할 수 없습니다.";

    protected final ChessGame chessGame;
    protected final String value;

    public Started(final ChessGame chessGame, final String value) {
        this.chessGame = chessGame;
        this.value = value;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public String getValue() {
        return value;
    }

}
