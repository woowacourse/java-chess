package chessgame;

import static chessgame.PointFixture.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chessgame.domain.Board;
import chessgame.domain.Team;

class BoardTest {
    @Test
    @DisplayName("모든 좌표값을 가진 보드를 생성한다")
    void Should_NotThrowException_When_ConstructBord() {
        assertDoesNotThrow(Board::new);
    }

    @Test
    @DisplayName("소스와 타겟좌표가 같을시 오류가 난다.")
    void Should_ThrowException_When_SourceIsSameWithTarget() {
        Board board = new Board();

        Assertions.assertThatThrownBy(() -> board.checkSource(A1, A1, Team.WHITE))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("소스와 타켓 좌표가 달라야합니다.");
    }

    @Test
    @DisplayName("소스에 기물이 있는지 확인하다.")
    void Should_True_When_SourcePointHasPiece() {
        Board board = new Board();

        assertDoesNotThrow(() -> board.checkSource(A1, A2, Team.WHITE));
    }

    @Test
    @DisplayName("타겟에 기물이 있는지 확인하다.")
    void Should_True_When_TargetPointHasPiece() {
        Board board = new Board();

        assertThat(board.checkTarget(F7, Team.WHITE)).isTrue();
    }

    @Test
    @DisplayName("기물의 이동 경로가 다른 기물에 의해 막혀있는지 확인")
    void Should_False_When_RouteBlockedByPiece() {
        Board board = new Board();

        Assertions.assertThatThrownBy(() -> board.checkRoute(A1, A8))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("기물을 건너 뛸 수 없습니다.");
    }
}
