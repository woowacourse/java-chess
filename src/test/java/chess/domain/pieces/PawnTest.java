package chess.domain.pieces;

import chess.domain.pieces.component.Team;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.Direction;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PawnTest {

    @Test
    @DisplayName("폰의 팀을 검증한다.")
    void validateTeamTest_exception() {
        Team team = Team.NEUTRALITY;

        assertThatThrownBy(() -> new Pawn(team));
    }

    @ParameterizedTest(name = "input : {0}")
    @DisplayName("pawn은 black 팀일때 DOWN, DOWN_LEFT, DOWN_RIGHT 방향을 가진다.")
    @CsvSource(value = {"UP:true","UP_LEFT:true","UP_RIGHT:true","DOWN:false","DOWN_LEFT:false","DOWN_RIGHT:false"},delimiter = ':')
    void validateTeam_white(String direction, boolean is){
        Pawn pawn = new Pawn(Team.WHITE);
        Assertions.assertThat(pawn.hasDirection(Direction.valueOf(direction))).isEqualTo(is);
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
