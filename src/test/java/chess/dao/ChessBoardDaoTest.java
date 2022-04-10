package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.position.Position;
import java.util.Map;
import java.util.Optional;
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
        chessBoardDao.save(initBoard);
    }

    @Test
    void findByPosition() {
        final ChessBoardDao chessBoardDao = new ChessBoardDao();
        ChessGame chessGame = new ChessGame();
        Map<Position, Piece> initBoard = chessGame.start();
        chessBoardDao.save(initBoard);
        Optional<String> piece = chessBoardDao.findByPosition("A8");

        assertThat(piece.orElseThrow()).isEqualTo("R_BLACK");
    }

    @Test
    void deleteAll() {
        final ChessBoardDao chessBoardDao = new ChessBoardDao();
        ChessGame chessGame = new ChessGame();
        Map<Position, Piece> initBoard = chessGame.start();
        chessBoardDao.save(initBoard);
        chessBoardDao.deleteAll();
        final Map<String, String> board = chessBoardDao.findAll();

        assertThat(board).isEmpty();
    }
}
