package chess.dao;

import static org.assertj.core.api.Assertions.*;

import chess.domain.Board;
import chess.domain.ChessGame;
import chess.domain.Color;
import chess.domain.GameStatus;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JdbcChessGameDaoTest {
    private final JdbcChessGameDao gameDao = new JdbcChessGameDao();

    @BeforeEach
    public void deleteAll() {
        gameDao.deleteAll();
    }
    @Test
    public void connection() {
        try (final var connection = gameDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void save() {
        Board board = Board.create();
        board.initialize();

        ChessGame chessGame = new ChessGame(board, Color.BLACK, GameStatus.MOVE);
        gameDao.save(1, chessGame);
    }

    @Test
    public void findAllPossibleId() {
        Board board1 = Board.create();
        board1.initialize();
        ChessGame moveStateChessGame = new ChessGame(board1, Color.BLACK, GameStatus.MOVE);
        gameDao.save(1, moveStateChessGame);

        Board board2 = Board.create();
        board2.initialize();
        ChessGame startStateChessGame = new ChessGame(board2, Color.BLACK, GameStatus.START);
        gameDao.save(2, startStateChessGame);

        Board board3 = Board.create();
        board3.initialize();
        ChessGame endStateChessGame = new ChessGame(board3, Color.BLACK, GameStatus.END);
        gameDao.save(3, endStateChessGame);

        Board board4 = Board.create();
        board4.initialize();
        ChessGame catchStateChessGame = new ChessGame(board4, Color.BLACK, GameStatus.CATCH);
        gameDao.save(4, catchStateChessGame);

        List<Integer> possibleId = gameDao.findAllPossibleId();
        assertThat(possibleId).contains(1,2);
    }

    @Test
    public void findAllImpossibleId() {
        Board board1 = Board.create();
        board1.initialize();
        ChessGame moveStateChessGame = new ChessGame(board1, Color.BLACK, GameStatus.MOVE);
        gameDao.save(1, moveStateChessGame);

        Board board2 = Board.create();
        board2.initialize();
        ChessGame startStateChessGame = new ChessGame(board2, Color.BLACK, GameStatus.START);
        gameDao.save(2, startStateChessGame);

        Board board3 = Board.create();
        board3.initialize();
        ChessGame endStateChessGame = new ChessGame(board3, Color.BLACK, GameStatus.END);
        gameDao.save(3, endStateChessGame);

        Board board4 = Board.create();
        board4.initialize();
        ChessGame catchStateChessGame = new ChessGame(board4, Color.BLACK, GameStatus.CATCH);
        gameDao.save(4, catchStateChessGame);

        List<Integer> possibleId = gameDao.findAllImpossibleId();
        assertThat(possibleId).contains(3,4);
    }

    @Test
    public void findById() {
        Board board = Board.create();
        board.initialize();
        ChessGame givenGame = new ChessGame(board, Color.BLACK, GameStatus.MOVE);
        gameDao.save(1, givenGame);

        ChessGame findGame = gameDao.findById(1);
        assertThat(givenGame.getTurn()).isEqualTo(findGame.getTurn());
        assertThat(givenGame.getStatus()).isEqualTo(findGame.getStatus());

        // Board에 대한 테스트는 어떻게?
    }

    @Test
    public void updateById() {
        Board board = Board.create();
        board.initialize();
        ChessGame givenGame = new ChessGame(board, Color.BLACK, GameStatus.MOVE);
        gameDao.save(1, givenGame);

        ChessGame newGame = new ChessGame(board, Color.WHITE, GameStatus.END);
        gameDao.updateById(1, newGame);

        ChessGame findGame = gameDao.findById(1);
        assertThat(findGame.getTurn()).isEqualTo(newGame.getTurn());
        assertThat(findGame.getStatus()).isEqualTo(newGame.getStatus());

        // Board에 대한 테스트는 어떻게?
    }

    @Test
    public void deleteById() {
        Board board = Board.create();
        board.initialize();
        ChessGame givenGame = new ChessGame(board, Color.BLACK, GameStatus.MOVE);
        gameDao.save(1, givenGame);

        gameDao.deleteById(1);

        assertThat(gameDao.findById(1)).isNull();
    }

}
