package chess.domain.gamestate;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;

import java.util.List;

public abstract class Started implements GameState {
    protected Board board = BoardFactory.createInitially();

    @Override
    public List<List<String>> getBoard() {
        return board.getBoard();
    }
}
