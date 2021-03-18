package chess.domain.game;

import chess.domain.piece.Color;

import java.util.Optional;

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

    @Override
    public Optional<Color> getWinnerColor() {
        double blackScore = chessGame.getBlackScore();
        double whiteScore = chessGame.getWhiteScore();

        return calculateWinner(blackScore, whiteScore);
    }

    private Optional<Color> calculateWinner(double blackScore, double whiteScore) {
        if (blackScore > whiteScore) {
            return Optional.of(Color.BLACK);
        }

        if (whiteScore > blackScore) {
            return Optional.of(Color.WHITE);
        }

        return Optional.empty();
    }
}
