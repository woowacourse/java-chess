package chess.model.dao;

import chess.ConnectionFactory;
import chess.model.ChessGame;
import chess.model.Column;
import chess.model.Row;
import chess.model.Square;
import chess.model.board.BasicBoardInitializer;
import chess.model.board.Board;
import chess.model.dto.GameInfo;
import chess.model.unit.Side;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ChessDaoTest {
    private ChessDao chessDao;

    @BeforeEach
    void setUp() {
        Connection con = ConnectionFactory.connect();
        chessDao = new ChessDao(con);
    }

    @Test
    void 보드_초기화_테스트() {
        Board board = new Board();
        board.initialize(new BasicBoardInitializer());
        ChessGame game = new ChessGame(board, Side.WHITE);
        assertDoesNotThrow(() -> chessDao.initializeBoard(game.createBoardInfo(), game.createGameInfo()));
    }

    @Test
    void 화이트_턴일때_loadTurn_테스트() {
        Board board = new Board();
        board.initialize(new BasicBoardInitializer());
        ChessGame game = new ChessGame(board, Side.WHITE);
        try {
            chessDao.initializeBoard(game.createBoardInfo(), game.createGameInfo());
            assertThat(chessDao.loadTurn()).isEqualTo(Side.WHITE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void 블랙_턴일때_loadTurn_테스트() {
        Board board = new Board();
        board.initialize(new BasicBoardInitializer());
        ChessGame game = new ChessGame(board, Side.BLACK);
        try {
            chessDao.initializeBoard(game.createBoardInfo(), game.createGameInfo());
            assertThat(chessDao.loadTurn()).isEqualTo(Side.BLACK);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void 기본_보드_일때_loadBoard_테스트() {
        Board board = new Board();
        board.initialize(new BasicBoardInitializer());
        ChessGame game = new ChessGame(board, Side.BLACK);
        try {
            chessDao.initializeBoard(game.createBoardInfo(), game.createGameInfo());
            assertThat(chessDao.loadBoard()).isEqualTo(board);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void updateMove_에러_미발생_테스트() {
        Board board = new Board();
        board.initialize(new BasicBoardInitializer());
        ChessGame game = new ChessGame(board, Side.BLACK);
        try {
            chessDao.initializeBoard(game.createBoardInfo(), game.createGameInfo());
            assertDoesNotThrow(() -> chessDao.updateMove(Square.of(Column._7, Row.C)
                    , Square.of(Column._5, Row.C)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void updateGameInfo_테스트() {
        Board board = new Board();
        board.initialize(new BasicBoardInitializer());
        ChessGame game = new ChessGame(board, Side.BLACK);
        try {
            chessDao.initializeBoard(game.createBoardInfo(), game.createGameInfo());
            GameInfo gameInfo = new GameInfo();
            gameInfo.setTurn(Side.WHITE.getSymbol());
            gameInfo.setFinished(true);
            chessDao.updateGameInfo(gameInfo);
            assertThat(Side.WHITE).isEqualTo(chessDao.loadTurn());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
        try {
            chessDao.deleteAllData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
