package chess.model.state.finished;

import chess.model.board.Board;
import chess.model.board.GameResult;
import chess.model.piece.Piece;
import java.util.Map;

public final class Status extends Finished {

    public Status(Board board) {
        super(board);
    }

    @Override
    public boolean isStatus() {
        return true;
    }

    @Override
    public Map<String, Piece> getBoardForWeb() {
        return board.getBoardForWeb();
    }

    @Override
    public GameResult getScore() {
        return board.calculateScore();
    }
}
