package chess.model.board.state;

import chess.dao.MoveDao;
import chess.model.ChessGame;
import chess.model.Scores;
import chess.model.position.Position;

public class Status extends ProgressState {

    public Status(final ChessGame chessGame, final MoveDao moveDao) {
        super(chessGame, moveDao);
    }

    @Override
    public Scores calculateScores() {
        return chessGame.calculateScoreAll();
    }

    @Override
    public void executeAndSave(final Position source, final Position target) {
        // 상태 정보를 표현할 때 사용할 수 없음
    }

    @Override
    public boolean isStatus() {
        return true;
    }
}
