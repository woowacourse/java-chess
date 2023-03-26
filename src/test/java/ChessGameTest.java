import domain.board.ChessAlignmentMock;
import domain.piece.King;
import domain.piece.Team;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static domain.position.PositionFixture.D4;

class ChessGameTest {

    @DisplayName("승자를 구할 수 있다.")
    @Test
    void blackWin() {
        //given
        final ChessGame chessGame = ChessGame.initGame(
                ChessAlignmentMock.testStrategy(Map.of(D4, new King(Team.BLACK))));

        //when

        //then
        Assertions.assertThat(chessGame.getWinner()).isEqualTo(Team.BLACK);
    }

    @DisplayName("승자를 구할 수 있다.")
    @Test
    void whiteWin() {
        //given
        final ChessGame chessGame = ChessGame.initGame(
                ChessAlignmentMock.testStrategy(Map.of(D4, new King(Team.WHITE))));

        //when

        //then
        Assertions.assertThat(chessGame.getWinner()).isEqualTo(Team.WHITE);
    }
}
