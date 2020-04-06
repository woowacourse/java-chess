package chess.domain.gamestate;

import chess.dao.JdbcTemplatePieceDAO;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;

import java.util.List;

public abstract class Started implements GameState {
    protected Board board = BoardFactory.createInitially(JdbcTemplatePieceDAO.getInstance());

    @Override
    public List<List<String>> getBoardForPrint() {
        return board.getBoardForPrinting();
    }
}
