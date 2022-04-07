package chess.dao;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.piece.Position;
import chess.domain.state.BlackTurn;
import chess.view.OutputView;
import org.junit.jupiter.api.Test;

class BoardDaoTest {

    @Test
    void save() {
        final Board board = new Board(BoardInitializer.initBoard());
        board.movePiece(new Position("a2"), new Position("a4"));

        OutputView.printBoard(new BlackTurn(board));

        final BoardDao boardDao = new BoardDao();
        boardDao.save(board);
    }

    @Test
    void findBoard() {
        final BoardDao boardDao = new BoardDao();
        final Board board = boardDao.findBoard();

        OutputView.printBoard(new BlackTurn(board));
    }
}
