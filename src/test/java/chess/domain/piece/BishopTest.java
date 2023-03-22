package chess.domain.piece;

import chess.domain.Team;
import chess.domain.position.RelativePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BishopTest {

    @ParameterizedTest
    @DisplayName("비숍은 팀과 관계 없이 대각선으로 거리 제한 없이 이동할 수 있다.")
    @CsvSource({"1,1", "1,-1", "-1,-1", "-1,1", "100,100", "100,-100", "-100,-100", "-100,100"})
    void bishopCanMoveDiagonalDirectionTest(int x, int y) {
        Bishop whiteBishop = Bishop.from(Team.WHITE);
        Bishop blackBishop = Bishop.from(Team.BLACK);
        RelativePosition relativePosition = new RelativePosition(x, y);

        assertTrue(whiteBishop.isMobile(relativePosition, new EmptyPiece()));
        assertTrue(blackBishop.isMobile(relativePosition, new EmptyPiece()));
    }

    @ParameterizedTest
    @DisplayName("비숍은 팀과 관계 없이 십자가 방향으로 이동할 수 없다.")
    @CsvSource({"0,1", "1,0", "0,-1", "-1,0"})
    void bishopCannotMoveCrossDirectionTest(int x, int y) {
        Bishop whiteBishop = Bishop.from(Team.WHITE);
        Bishop blackBishop = Bishop.from(Team.BLACK);
        RelativePosition relativePosition = new RelativePosition(x, y);

        assertThatThrownBy(() -> whiteBishop.isMobile(relativePosition, new EmptyPiece()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 방향으로 이동할 수 없는 말입니다.");
        assertThatThrownBy(() -> blackBishop.isMobile(relativePosition, new EmptyPiece()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 방향으로 이동할 수 없는 말입니다.");
    }

    @Test
    @DisplayName("이동하려는 위치에 있는 말의 팀 색깔이 같으면 예외처리한다.")
    void sameTeamTest() {
        Bishop whiteBishop = Bishop.from(Team.WHITE);

        assertThatThrownBy(() -> whiteBishop.isMobile(new RelativePosition(0, 1), Bishop.from(Team.WHITE)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동하고자 하는 자리에 같은 팀이 존재합니다.");
    }

}
