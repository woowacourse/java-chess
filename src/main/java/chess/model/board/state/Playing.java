package chess.model.board.state;

import chess.model.ChessGame;
import chess.model.Scores;
import chess.model.position.Position;

public class Playing extends ProgressState {

    protected Playing(final ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public void execute(final Position source, final Position target) {
        chessGame.moveAndSaveRecord(source, target);
    }

    @Override
    public boolean isStatus() {
        return false;
    }

    @Override
    public final Scores calculateScores() {
        throw new UnsupportedOperationException("지원하지 않는 기능 입니다.");
    }
}
