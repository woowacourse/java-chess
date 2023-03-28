package chess.dao;

import static org.assertj.core.api.Assertions.*;

import chess.domain.Board;
import chess.domain.ChessGame;
import chess.domain.Color;
import chess.domain.GameStatus;
import chess.dto.GameInfoDto;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JdbcGameDaoTest {
    private final JdbcGameDao gameDao = new JdbcGameDao();

    @BeforeEach
    public void deleteAll() {
        gameDao.deleteAll();
    }
    @Test
    public void connection() {
        try (final var connection = ConnectionProvider.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void save() {
        Board board = Board.create();

        ChessGame chessGame = new ChessGame(board, Color.BLACK, GameStatus.MOVE);
        gameDao.save(1, GameInfoDto.create(chessGame.getStatus(), chessGame.getTurn()));
    }

    @Test
    public void findAllPossibleId() {
        Board board1 = Board.create();
        ChessGame moveStateChessGame = new ChessGame(board1, Color.BLACK, GameStatus.MOVE);
        gameDao.save(1, GameInfoDto.create(moveStateChessGame.getStatus(), moveStateChessGame.getTurn()));

        Board board2 = Board.create();
        ChessGame startStateChessGame = new ChessGame(board2, Color.BLACK, GameStatus.START);
        gameDao.save(2, GameInfoDto.create(startStateChessGame.getStatus(), startStateChessGame.getTurn()));

        Board board3 = Board.create();
        ChessGame endStateChessGame = new ChessGame(board3, Color.BLACK, GameStatus.END);
        gameDao.save(3, GameInfoDto.create(endStateChessGame.getStatus(), endStateChessGame.getTurn()));

        Board board4 = Board.create();
        ChessGame catchStateChessGame = new ChessGame(board4, Color.BLACK, GameStatus.CATCH);
        gameDao.save(4, GameInfoDto.create(catchStateChessGame.getStatus(), catchStateChessGame.getTurn()));

        List<Integer> possibleId = gameDao.findAllPossibleId();
        assertThat(possibleId).contains(1,2);
    }

    @Test
    public void findAllImpossibleId() {
        Board board1 = Board.create();
        ChessGame moveStateChessGame = new ChessGame(board1, Color.BLACK, GameStatus.MOVE);
        gameDao.save(1, GameInfoDto.create(moveStateChessGame.getStatus(), moveStateChessGame.getTurn()));

        Board board2 = Board.create();
        ChessGame startStateChessGame = new ChessGame(board2, Color.BLACK, GameStatus.START);
        gameDao.save(2, GameInfoDto.create(startStateChessGame.getStatus(), startStateChessGame.getTurn()));

        Board board3 = Board.create();
        ChessGame endStateChessGame = new ChessGame(board3, Color.BLACK, GameStatus.END);
        gameDao.save(3, GameInfoDto.create(endStateChessGame.getStatus(), endStateChessGame.getTurn()));

        Board board4 = Board.create();
        ChessGame catchStateChessGame = new ChessGame(board4, Color.BLACK, GameStatus.CATCH);
        gameDao.save(4, GameInfoDto.create(catchStateChessGame.getStatus(), catchStateChessGame.getTurn()));

        List<Integer> possibleId = gameDao.findAllImpossibleId();
        assertThat(possibleId).contains(3,4);
    }

    @Test
    public void findById() {
        Board board = Board.create();
        ChessGame givenGame = new ChessGame(board, Color.BLACK, GameStatus.MOVE);
        gameDao.save(1, GameInfoDto.create(givenGame.getStatus(), givenGame.getTurn()));

        GameInfoDto gameInfo = gameDao.findById(1);
        assertThat(givenGame.getTurn()).isEqualTo(gameInfo.getTurn());
        assertThat(givenGame.getStatus()).isEqualTo(gameInfo.getStatus());

        // Board에 대한 테스트는 어떻게?
    }

    @Test
    public void updateById() {
        Board board = Board.create();
        ChessGame givenGame = new ChessGame(board, Color.BLACK, GameStatus.MOVE);
        gameDao.save(1, GameInfoDto.create(givenGame.getStatus(), givenGame.getTurn()));

        ChessGame newGame = new ChessGame(board, Color.WHITE, GameStatus.END);
        gameDao.updateById(1, GameInfoDto.create(newGame.getStatus(), newGame.getTurn()));

        GameInfoDto findGameInfo = gameDao.findById(1);
        assertThat(findGameInfo.getTurn()).isEqualTo(newGame.getTurn());
        assertThat(findGameInfo.getStatus()).isEqualTo(newGame.getStatus());

        // Board에 대한 테스트는 어떻게?
    }

    @Test
    public void deleteById() {
        Board board = Board.create();
        ChessGame givenGame = new ChessGame(board, Color.BLACK, GameStatus.MOVE);
        gameDao.save(1, GameInfoDto.create(givenGame.getStatus(), givenGame.getTurn()));

        gameDao.deleteById(1);

        assertThat(gameDao.findById(1)).isNull();
    }

}
