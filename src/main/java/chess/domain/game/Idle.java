package chess.domain.game;

public abstract class Idle extends Started {

    protected Idle(final ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public void end() {
        throw new UnsupportedOperationException(MESSAGE_UNSUPPORTED);
    }

}
