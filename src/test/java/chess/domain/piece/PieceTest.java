package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {
    @DisplayName("기물의 팀이 흑팀인지 확인할 수 있다")
    @Test
    void should_CheckPieceIsBlackTeam() {
        King blackPiece = new King(Team.BLACK);
        King whitePiece = new King(Team.WHITE);

        assertAll(
                () -> assertThat(blackPiece.isBlackTeam()).isTrue(),
                () -> assertThat(whitePiece.isBlackTeam()).isFalse()
        );
    }

    @DisplayName("기물간 서로 같은 팀인지 다른 팀인지 확인할 수 있다")
    @Test
    void should_ComparePieceTeam() {
        King blackPiece = new King(Team.BLACK);
        King whitePiece = new King(Team.WHITE);

        assertAll(
                () -> assertThat(blackPiece.isOtherTeam(Team.WHITE)).isTrue(),
                () -> assertThat(whitePiece.isOtherTeam(Team.WHITE)).isFalse()
        );
    }
}
