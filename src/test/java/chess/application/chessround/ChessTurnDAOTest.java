package chess.application.chessround;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChessTurnDAOTest {
    private ChessTurnDAO chessTurnDAO = ChessTurnDAO.getInstance();

    @Test
    void Turn을_변경하고_조회하는_테스트() {
        boolean isWhiteTurn = chessTurnDAO.getPlayerTurn();

        chessTurnDAO.updatePlayerTurn(!isWhiteTurn);

        assertThat(chessTurnDAO.getPlayerTurn()).isEqualTo(!isWhiteTurn);

        chessTurnDAO.updatePlayerTurn(isWhiteTurn);
    }
}