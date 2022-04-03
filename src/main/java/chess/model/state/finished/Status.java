package chess.model.state.finished;

import chess.model.board.Board;
import chess.model.board.GameResult;
import chess.model.piece.Piece;
import java.util.Map;

public final class Status extends Finished {

    private final Board board;

    public Status(Board board) {
        this.board = board;
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
