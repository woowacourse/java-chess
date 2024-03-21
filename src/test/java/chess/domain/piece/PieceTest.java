package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PieceTest {

    class ExamplePiece extends Piece {

        protected ExamplePiece(Team team) {
            super(team, List.of());
        }
    }

    @ParameterizedTest
    @CsvSource({"BLACK, true", "WHITE, false"})
    @DisplayName("해당 팀이 검정 팀인지 확인한다.")
    void isBlackTeamTest(Team team, boolean expected) {
        ExamplePiece examplePiece = new ExamplePiece(team);

        assertThat(examplePiece.isBlackTeam()).isEqualTo(expected);
    }
}
