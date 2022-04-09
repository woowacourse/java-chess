package chess.dao;

import chess.domain.piece.property.Team;
import chess.domain.position.Position;
import java.sql.SQLException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MovementDAOTest {

    @BeforeEach
    void before() throws SQLException {
        ChessGameDAO chessGameDAO = new ChessGameDAO();
        ChessGame chessGame = ChessGame.initChessGame();
        chessGame.setName("zero");
        String gameId = chessGameDAO.addGame(chessGame);

        org.assertj.core.api.Assertions.assertThat(gameId).isNotNull();
    }

    @Test
    @DisplayName("움직임 확인")
    void checkMovement() throws SQLException {
        ChessGame chessGame = ChessGame.initChessGame();
        MovementDAO movementDAO = new MovementDAO();
        Movement movement = new Movement(
                Position.of("B2"),
                Position.of("B3")
        );
        movement.setGameId("1");
        movement.setTeam(Team.BLACK);
        chessGame.execute(movement);
        Assertions.assertDoesNotThrow(() -> movementDAO.addMoveCommand(movement));
    }
}
