package chess.domain.pieces;

import chess.domain.pieces.component.Team;
import chess.domain.board.File;
import chess.domain.board.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KingTest {

    @Test
    @DisplayName("킹의 팀을 검증한다.")
    void validateTeamTest_exception() {
        Team team = Team.NEUTRALITY;

        assertThatThrownBy(() -> new King(team));
    }

    @Test
    @DisplayName("canMoveStep()은 현재 위치와 목표 위치를 받아 step이 1이면 true를 반환한다.")
    void canMoveStep_test_true() {
        King king = new King(Team.BLACK);
        Position currentPosition = new Position(Rank.TWO, File.A);
        Position targetPosition = new Position(Rank.TREE, File.A);

        Assertions.assertThat(king.canMoveStep(currentPosition, targetPosition)).isTrue();
    }

    @Test
    @DisplayName("canMoveStep()은 현재 위치와 목표 위치를 받아 step이 1이 아니면 false를 반환한다.")
    void canMoveStep_test_false() {
        King king = new King(Team.BLACK);
        Position currentPosition = new Position(Rank.TWO, File.A);
        Position targetPosition = new Position(Rank.FIVE, File.A);

        Assertions.assertThat(king.canMoveStep(currentPosition, targetPosition)).isFalse();
    }
}
