package chess.domain.game.state;

import chess.domain.game.ChessGame;
import chess.domain.piece.black.BlackPiece;
import chess.domain.piece.white.WhitePiece;

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
    public Optional<String> getWinnerColorNotation() {
        double blackScore = chessGame.getBlackScore();
        double whiteScore = chessGame.getWhiteScore();

        return calculateWinner(blackScore, whiteScore);
    }

    private Optional<String> calculateWinner(double blackScore, double whiteScore) {
        if (blackScore > whiteScore) {
            return Optional.of(BlackPiece.NOTATION);
        }

        if (whiteScore > blackScore) {
            return Optional.of(WhitePiece.NOTATION);
        }

        return Optional.empty();
    }

}
