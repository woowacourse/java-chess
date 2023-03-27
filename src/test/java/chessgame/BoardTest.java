package chessgame;

import static chessgame.domain.point.PointFixture.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chessgame.domain.Board;
import chessgame.domain.Team;
import chessgame.domain.point.Points;

class BoardTest {
    private Board board;

    @BeforeEach
    void initializeBoard() {
        board = new Board();
    }

    @Test
    @DisplayName("모든 좌표값을 가진 보드를 생성한다")
    void Should_NotThrowException_When_ConstructBord() {
        assertDoesNotThrow(() -> new Board());
    }

    @Test
    @DisplayName("소스와 타겟좌표가 같을시 오류가 난다.")
    void Should_ThrowException_When_SourceIsSameWithTarget() {
        Assertions.assertThatThrownBy(() -> board.move(new Points(List.of(A1, A1)), Team.WHITE))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("소스와 타켓 좌표가 달라야합니다.");
    }

    @Test
    @DisplayName("자기팀 기물은 잡을 수 없다")
    void Should_CantAttack_When_SameTeam() {
        Assertions.assertThatThrownBy(() -> board.move(new Points(List.of(A1, A2)), Team.WHITE))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("자기팀 기물을 잡을 수 없습니다.");
    }

    @Test
    @DisplayName("기물의 이동 경로가 다른 기물에 의해 막혀있는지 확인")
    void Should_False_When_RouteBlockedByPiece() {
        Assertions.assertThatThrownBy(() -> board.move(new Points(List.of(A1, A8)), Team.WHITE))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("룩은 기물을 건너 뛸 수 없습니다.");
    }
}
