package chess.domain.pieces;

import static chess.domain.pieces.Piece.INVALID_TEAM;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.Team;
import chess.domain.math.Direction;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {

    @Test
    @DisplayName("룩의 팀을 검증한다.")
    void validateTeamTest_exception() {
        Team team = Team.NEUTRALITY;

        assertThatIllegalArgumentException().isThrownBy(
                () -> new Pawn(team)
        ).withMessage(INVALID_TEAM);
    }

    @Test
    @DisplayName("pawn은 black 팀일때 DOWN, DOWN_LEFT, DOWN_RIGHT 방향을 가진다.")
    void validateTeam_white(){
        Pawn pawn = new Pawn(Team.WHITE);
        Assertions.assertThat(pawn.hasDirection(Direction.DOWN)).isTrue();
        Assertions.assertThat(pawn.hasDirection(Direction.DOWN_LEFT)).isTrue();
        Assertions.assertThat(pawn.hasDirection(Direction.DOWN_RIGHT)).isTrue();
        Assertions.assertThat(pawn.hasDirection(Direction.UP)).isFalse();
        Assertions.assertThat(pawn.hasDirection(Direction.UP_LEFT)).isFalse();
        Assertions.assertThat(pawn.hasDirection(Direction.UP_RIGHT)).isFalse();
    }


    @Test
    @DisplayName("canMoveStep() 은 현재 위치와 목표 위치를 받아 1칸, 2칸 일때는 true를 반환한다.")
    void test_canMoveStep_true_one() {
        // given & when
        Pawn pawn = new Pawn(Team.WHITE);
        Position currentPosition = new Position(Rank.TWO, File.A);
        Position targetPosition = new Position(Rank.TREE, File.A);

        // then
        Assertions.assertThat(pawn.canMoveStep(currentPosition,targetPosition)).isTrue();
    }

    @Test
    @DisplayName("canMoveStep() 은 현재 위치와 목표 위치를 받아 1칸, 2칸 일때는 true를 반환한다.")
    void test_canMoveStep_true_two() {
        // given & when
        Pawn pawn = new Pawn(Team.WHITE);
        Position currentPosition = new Position(Rank.TWO, File.A);
        Position targetPosition = new Position(Rank.FOUR, File.A);

        // then
        Assertions.assertThat(pawn.canMoveStep(currentPosition,targetPosition)).isTrue();
    }

    @Test
    @DisplayName("canMoveStep() 은 현재 위치와 목표 위치를 받아 3칸 일때는 false를 반환한다.")
    void test_canMoveStep_false_two() {
        // given & when
        Pawn pawn = new Pawn(Team.WHITE);
        Position currentPosition = new Position(Rank.TWO, File.A);
        Position targetPosition = new Position(Rank.FIVE, File.A);

        // then
        Assertions.assertThat(pawn.canMoveStep(currentPosition,targetPosition)).isFalse();
    }

    @Test
    @DisplayName("isUpOrDown() direction을 받아 up, down direction일 경우 true를 반환하다.")
    void test_isUpOrDown_true() {
        // given & when
        Pawn pawn = new Pawn(Team.WHITE);

        // then
        Assertions.assertThat(pawn.isUpOrDown(Direction.DOWN)).isTrue();
    }

    @Test
    @DisplayName("isUpOrDown() direction을 받아 up,down direction이 아닐 경우 false를 반환하다.")
    void test_isUpOrDown_false() {
        // given & when
        Pawn pawn = new Pawn(Team.WHITE);

        // then
        Assertions.assertThat(pawn.isUpOrDown(Direction.UP_RIGHT)).isFalse();
    }
}
