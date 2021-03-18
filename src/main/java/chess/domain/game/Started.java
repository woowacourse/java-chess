package chess.domain.game;

public abstract class Started implements State {
    protected static final String MESSAGE_UNSUPPORTED = "명령을 수행할 수 없습니다.";

    protected final ChessGame chessGame;

    public Started(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
