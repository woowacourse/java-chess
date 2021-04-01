package chess.dao;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.chessgame.ChessGame;
import chess.domain.chessgame.GameState;
import chess.domain.chessgame.Turn;
import chess.domain.piece.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ChessDAOTest {
    private ChessDAO chessDAO;
    private final String gameId = "new_game1";
    private final ChessGame game = new ChessGame(gameId, new Board(), new Turn(Team.WHITE), new GameState());

    @BeforeEach
    public void setup() {
        chessDAO = new ChessDAO();
    }

    @Test
    @DisplayName("db 연결 기능")
    public void connection() {
        Connection con = chessDAO.getConnection();
        assertNotNull(con);
    }

    @Test
    @DisplayName("게임 추가 기능")
    void addGame() throws SQLException {
        chessDAO.addGame("new_game1", new ChessGame());
    }

    @Test
    @DisplayName("게임 가져오는 기능")
    void findGame() throws SQLException {
        assertThat(chessDAO.findGameById(gameId)).isEqualTo(game);
    }
}