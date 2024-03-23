package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Team;
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
        ExamplePiece piece = new ExamplePiece(team);

        assertThat(piece.isBlackTeam()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"BLACK, true", "WHITE, false"})
    @DisplayName("기물이 해당 팀인지 확인한다.")
    void isSameTeamTest_whenOnePiece(Team pieceTeam, boolean expected) {
        ExamplePiece piece = new ExamplePiece(pieceTeam);
        Team team = Team.BLACK;

        assertThat(piece.isSameTeam(team)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"BLACK, true", "WHITE, false"})
    @DisplayName("두 기물이 같은 팀인지 확인한다.")
    void isSameTeamTest_whenTwoPieces(Team team, boolean expected) {
        ExamplePiece one = new ExamplePiece(Team.BLACK);
        ExamplePiece other = new ExamplePiece(team);

        assertThat(one.isSameTeam(other)).isEqualTo(expected);
    }
}
