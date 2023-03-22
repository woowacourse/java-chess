package chess.domain.piece;

import chess.domain.Team;
import chess.domain.position.RelativePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KnightTest {

    @ParameterizedTest
    @DisplayName("나이트는 팀과 관계 없이 모든 방향의 L자 모양으로 이동할 수 있다.")
    @CsvSource({"1,2", "2,1", "2,-1", "1,-2", "-1,-2", "-2,-1", "-2,1", "-1,2"})
    void knightCanMoveEveryDirectionTest(int x, int y) {
        Knight whiteKnight = Knight.from(Team.WHITE);
        Knight blackKnight = Knight.from(Team.BLACK);
        RelativePosition relativePosition = new RelativePosition(x, y);

        assertTrue(whiteKnight.isMobile(relativePosition, new EmptyPiece()));
        assertTrue(blackKnight.isMobile(relativePosition, new EmptyPiece()));
    }

    @Test
    @DisplayName("이동하려는 위치에 있는 말의 팀 색깔이 같으면 예외처리한다.")
    void sameTeamTest() {
        Pawn whitePawn = Pawn.from(Team.WHITE);

        assertThatThrownBy(() -> whitePawn.isMobile(new RelativePosition(0, 1), Pawn.from(Team.WHITE))).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동하고자 하는 자리에 같은 팀이 존재합니다.");
    }
}
