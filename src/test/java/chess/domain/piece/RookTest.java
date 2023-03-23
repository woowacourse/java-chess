package chess.domain.piece;

import chess.domain.position.RelativePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RookTest {

    @ParameterizedTest
    @DisplayName("룩은 팀과 관계 없이 십자가 방향으로 거리 제한 없이 이동할 수 있다.")
    @CsvSource({"0,1", "1,0", "0,-1", "-1,0", "0,100", "100,0", "0,-100", "-100,0"})
    void rookCanMoveCrossDirectionTest(int x, int y) {
        Rook whiteRook = Rook.from(Team.WHITE);
        Rook blackRook = Rook.from(Team.BLACK);
        RelativePosition relativePosition = new RelativePosition(x, y);

        assertTrue(whiteRook.isMobile(relativePosition, new EmptyPiece()));
        assertTrue(blackRook.isMobile(relativePosition, new EmptyPiece()));
    }

    @ParameterizedTest
    @DisplayName("룩은 팀과 관계 없이 대각선으로 이동할 수 없다.")
    @CsvSource({"1,1", "1,-1", "-1,-1", "-1,1"})
    void rookCannotMoveDiagonalDirectionTest(int x, int y) {
        Rook whiteRook = Rook.from(Team.WHITE);
        Rook blackRook = Rook.from(Team.BLACK);
        RelativePosition relativePosition = new RelativePosition(x, y);

        assertThatThrownBy(() -> whiteRook.isMobile(relativePosition, new EmptyPiece()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 방향으로 이동할 수 없는 말입니다.");
        assertThatThrownBy(() -> blackRook.isMobile(relativePosition, new EmptyPiece()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 방향으로 이동할 수 없는 말입니다.");
    }

    @Test
    @DisplayName("이동하려는 위치에 있는 말의 팀 색깔이 같으면 예외처리한다.")
    void sameTeamTest() {
        Rook whiteRook = Rook.from(Team.WHITE);

        assertThatThrownBy(() -> whiteRook.isMobile(new RelativePosition(0, 4), Pawn.from(Team.WHITE))).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동하고자 하는 자리에 같은 팀이 존재합니다.");
    }
}
