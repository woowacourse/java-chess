package chess.model.board.state;

import chess.model.ChessGame;
import chess.model.Scores;

public class Status extends ProgressState {

    protected Status(final ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public Scores calculateScores() {
        return chessGame.calculateScoreAll();
    }

    @Override
    public boolean isStatus() {
        return true;
    }
}
