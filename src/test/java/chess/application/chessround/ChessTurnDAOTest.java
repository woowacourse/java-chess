package chess.application.chessround;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChessTurnDAOTest {
    private ChessTurnDAO chessTurnDAO = ChessTurnDAO.getInstance();

    @Test
    void updatePlayerTurn() {
        boolean isWhiteTurn = chessTurnDAO.getPlayerTurn();

        chessTurnDAO.updatePlayerTurn(!isWhiteTurn);

        assertThat(chessTurnDAO.getPlayerTurn()).isEqualTo(!isWhiteTurn);

        chessTurnDAO.updatePlayerTurn(isWhiteTurn);
    }
}