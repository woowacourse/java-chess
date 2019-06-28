package chess.model.dao;

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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ChessDaoImpTest {
    private ChessDao chessDao;

    @BeforeEach
    void setUp() {
        chessDao = ChessDao.getInstance();
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
        chessDao.initializeBoard(game.createBoardInfo(), game.createGameInfo());
        assertThat(chessDao.loadTurn()).isEqualTo(Side.WHITE);
    }

    @Test
    void 블랙_턴일때_loadTurn_테스트() {
        Board board = new Board();
        board.initialize(new BasicBoardInitializer());
        ChessGame game = new ChessGame(board, Side.BLACK);
        chessDao.initializeBoard(game.createBoardInfo(), game.createGameInfo());
        assertThat(chessDao.loadTurn()).isEqualTo(Side.BLACK);
    }

    @Test
    void 기본_보드_일때_loadBoard_테스트() {
        Board board = new Board();
        board.initialize(new BasicBoardInitializer());
        ChessGame game = new ChessGame(board, Side.BLACK);
        chessDao.initializeBoard(game.createBoardInfo(), game.createGameInfo());
        assertThat(chessDao.loadBoard()).isEqualTo(board);
    }

    @Test
    void updateMove_에러_미발생_테스트() {
        Board board = new Board();
        board.initialize(new BasicBoardInitializer());
        ChessGame game = new ChessGame(board, Side.BLACK);
        chessDao.initializeBoard(game.createBoardInfo(), game.createGameInfo());
        assertDoesNotThrow(() -> chessDao.updateMove(Square.of(Column.Col_7, Row.Row_C)
                , Square.of(Column.Col_5, Row.Row_C)));
    }

    @Test
    void updateGameInfo_테스트() {
        Board board = new Board();
        board.initialize(new BasicBoardInitializer());
        ChessGame game = new ChessGame(board, Side.BLACK);
        chessDao.initializeBoard(game.createBoardInfo(), game.createGameInfo());
        GameInfo gameInfo = new GameInfo();
        gameInfo.setTurn(Side.WHITE.getSymbol());
        gameInfo.setFinished(true);
        chessDao.updateGameInfo(gameInfo);
        assertThat(Side.WHITE).isEqualTo(chessDao.loadTurn());
    }

    @Test
    void Empty_테스트() {
        assertThat(chessDao.checkEmpty()).isTrue();
    }

    @AfterEach
    void tearDown() {
        chessDao.deleteAllData();
    }
}
