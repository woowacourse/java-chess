package chess.web.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.position.Position;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChessBoardDaoTest {

    @BeforeEach
    void init() {
        final ChessBoardDao chessBoardDao = new ChessBoardDao();
        chessBoardDao.deleteAll();
    }

    @Test
    void save() {
        final ChessBoardDao chessBoardDao = new ChessBoardDao();
        ChessGame chessGame = new ChessGame();
        Map<Position, Piece> initBoard = chessGame.start();
        for (Position position : initBoard.keySet()) {
            chessBoardDao.save(position, initBoard.get(position));
        }
    }

    @Test
    void deleteAll() {
        final ChessBoardDao chessBoardDao = new ChessBoardDao();
        ChessGame chessGame = new ChessGame();
        Map<Position, Piece> initBoard = chessGame.start();
        for (Position position : initBoard.keySet()) {
            chessBoardDao.save(position, initBoard.get(position));
        }
        chessBoardDao.deleteAll();
        final Map<Position, Piece> board = chessBoardDao.findAll();

        assertThat(board).isEmpty();
    }
}
