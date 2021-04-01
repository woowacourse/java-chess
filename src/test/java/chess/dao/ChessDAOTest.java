package chess.dao;

import chess.domain.board.Board;
import chess.domain.chessgame.ChessGame;
import chess.domain.chessgame.GameState;
import chess.domain.chessgame.Turn;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ChessDAOTest {
    private ChessDAO chessDAO;
    private final String gameId = "new_game";
    private final ChessGame game = new ChessGame(new Board(), new Turn(Team.WHITE), new GameState());

    @BeforeEach
    public void setup() {
        chessDAO = new ChessDAO();
    }

    @Test
    @DisplayName("db 연결 기능")
    @Order(1)
    public void connection() {
        Connection con = chessDAO.getConnection();
        assertNotNull(con);
    }

    @Test
    @DisplayName("게임 추가 기능")
    @Order(2)
    void addGame() throws SQLException {
        chessDAO.addGame(gameId, new ChessGame());
    }

    @Test
    @DisplayName("게임 가져오는 기능")
    @Order(3)
    void findGame() throws SQLException {
        assertThat(chessDAO.findGameById(gameId).boardForDAO()).isEqualTo(new ChessGame().boardForDAO());
        assertThat(chessDAO.findGameById(gameId).turnForDAO()).isEqualTo(game.turnForDAO());
    }

    @Test
    @DisplayName("게임 상태 업데이트 기능")
    @Order(4)
    void updateGame() throws SQLException {
        game.move(new Position("b2"), new Position("b3"));
        chessDAO.updateGame(gameId, game);
        assertThat(chessDAO.findGameById(gameId).boardForDAO()).isEqualTo(game.boardForDAO());
        assertThat(chessDAO.findGameById(gameId).turnForDAO()).isEqualTo(game.turnForDAO());
    }

    @Test
    @DisplayName("게임 삭제 기능")
    @Order(5)
    void deleteGame() throws SQLException {
        chessDAO.deleteGameById(gameId);
        assertNull(chessDAO.findGameById(gameId));
    }
}