package chess.model.board.state;

import chess.dao.MoveDao;
import chess.dao.MoveSaveStrategy;
import chess.model.ChessGame;
import chess.model.Scores;
import chess.model.position.Position;

public class Playing extends ProgressState {

    public Playing(final ChessGame chessGame, final MoveDao moveDao) {
        super(chessGame, moveDao);
    }

    @Override
    public void executeAndSave(final Position source, final Position target) {
        chessGame.move(source, target);
        moveDao.save(new MoveSaveStrategy(source, target));
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
