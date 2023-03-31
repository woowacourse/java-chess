package chess.dao;

import chess.domain.game.ChessGame;
import chess.domain.game.Running;
import chess.domain.piece.Piece;
import chess.domain.piece.property.Color;
import chess.domain.piece.property.Kind;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ChessGameDaoTest {

    private static final Position A2 = new Position(File.A, Rank.TWO);
    private static final Position A4 = new Position(File.A, Rank.FOUR);

    private final ChessTestDao dao = new ChessTestDao();
    private final Connection connection = dao.connection();

    @BeforeEach
    void before() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {

        }
    }

    @AfterEach
    void after() {
        try {
            connection.rollback();
            connection.close();
        } catch (SQLException e) {

        }
    }

    @Test
    @DisplayName("auto increment로 게임을 생성한다")
    void createGameTest() {
        //given
        Long gameId = dao.createGame();
        Long gameIdIncreased = dao.createGame();

        //when
        long increased = gameIdIncreased - gameId;

        //then
        assertThat(increased).isEqualTo(1L);
    }

    @Test
    @DisplayName("게임을 저장한다.")
    void saveGameTest() {
        //given
        final var chessGame = new ChessGame();
        Long gameId = dao.createGame();
        chessGame.startGame();
        chessGame.setGameId(gameId);
        dao.saveGame(chessGame);

        //when
        Running gameById = dao.findGameById(gameId);
        Map<Position, Piece> board = gameById.getBoard();
        Piece piece = board.get(A2);
        Kind kind = piece.getKind();
        Color color = piece.getColor();

        //then
        Assertions.assertAll(
                () -> assertThat(kind).isEqualTo(Kind.PAWN),
                () -> assertThat(color).isEqualTo(Color.WHITE)
        );
    }

    @Test
    @DisplayName("기물 움직임과 턴을 업데이트한다.")
    void updateGameTest() {
        //given
        final var chessGame = new ChessGame();
        Long gameId = dao.createGame();
        chessGame.startGame();
        chessGame.setGameId(gameId);
        dao.saveGame(chessGame);

        chessGame.playTurn(A2, A4);
        dao.updateGame(chessGame, A2, A4);
        Running gameById = dao.findGameById(gameId);

        //when
        Color turn = gameById.getTurn();
        Map<Position, Piece> board = gameById.getBoard();
        Kind kind = board.get(A4).getKind();
        Color color = board.get(A4).getColor();

        //then
        Assertions.assertAll(
                () -> assertThat(kind).isEqualTo(Kind.PAWN),
                () -> assertThat(color).isEqualTo(Color.WHITE),
                () -> assertThat(turn).isEqualTo(Color.BLACK)
        );
    }
}
