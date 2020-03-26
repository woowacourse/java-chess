package chess.domain.gamestatus;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;

import java.util.List;

public abstract class Started implements GameStatus {
    protected Board board = BoardFactory.createInitially();

    @Override
    public List<List<String>> getBoard() {
        return board.getBoard();
    }
}
