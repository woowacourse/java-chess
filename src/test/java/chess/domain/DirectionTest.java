package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.piece.File;
import chess.domain.piece.Position;
import chess.domain.piece.Rank;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DirectionTest {

    @DisplayName("목표위치와 비교했을 때 기준위치의 방향이 오른쪽을 포함함을 알려준다.")
    @Test
    void ofRIGHT() {
        // given
        Position sourcePosition = new Position(File.C, Rank.THREE);
        Position targetPosition = new Position(File.B, Rank.THREE);

        // when
        List<Direction> directions = Direction.of(sourcePosition, targetPosition);

        // then
        assertThat(directions).contains(Direction.RIGHT);
    }

    @DisplayName("목표위치와 비교했을 때 기준위치의 방향이 왼쪽을 포함함을 알려준다.")
    @Test
    void ofLEFT() {
        // given
        Position sourcePosition = new Position(File.C, Rank.THREE);
        Position targetPosition = new Position(File.D, Rank.THREE);

        // when
        List<Direction> directions = Direction.of(sourcePosition, targetPosition);

        // then
        assertThat(directions).contains(Direction.LEFT);
    }

    @DisplayName("목표위치와 비교했을 때 기준위치의 방향이 위를 포함함을 알려준다.")
    @Test
    void ofUP() {
        // given
        Position sourcePosition = new Position(File.C, Rank.THREE);
        Position targetPosition = new Position(File.C, Rank.TWO);

        // when
        List<Direction> directions = Direction.of(sourcePosition, targetPosition);

        // then
        assertThat(directions).contains(Direction.UP);
    }

    @DisplayName("목표위치와 비교했을 때 기준위치의 방향이 아래를 포함함을 알려준다.")
    @Test
    void ofDOWN() {
        // given
        Position sourcePosition = new Position(File.C, Rank.THREE);
        Position targetPosition = new Position(File.C, Rank.FOUR);

        // when
        List<Direction> directions = Direction.of(sourcePosition, targetPosition);

        // then
        assertThat(directions).contains(Direction.DOWN);
    }

    @DisplayName("목표위치와 비교했을 때 기준위치의 방향이 오른쪽위를 포함함을 알려준다.")
    @Test
    void ofRIGHT_UP() {
        // given
        Position sourcePosition = new Position(File.C, Rank.THREE);
        Position targetPosition = new Position(File.B, Rank.ONE);

        // when
        List<Direction> directions = Direction.of(sourcePosition, targetPosition);

        // then
        assertThat(directions).contains(Direction.RIGHT_UP);
    }

    @DisplayName("목표위치와 비교했을 때 기준위치의 방향이 오른쪽아래를 포함함을 알려준다.")
    @Test
    void ofRIGHT_DOWN() {
        // given
        Position sourcePosition = new Position(File.C, Rank.THREE);
        Position targetPosition = new Position(File.B, Rank.FOUR);

        // when
        List<Direction> directions = Direction.of(sourcePosition, targetPosition);

        // then
        assertThat(directions).contains(Direction.RIGHT_DOWN);
    }

    @DisplayName("목표위치와 비교했을 때 기준위치의 방향이 왼쪽위를 포함함을 알려준다.")
    @Test
    void ofLEFT_UP() {
        // given
        Position sourcePosition = new Position(File.C, Rank.THREE);
        Position targetPosition = new Position(File.D, Rank.TWO);

        // when
        List<Direction> directions = Direction.of(sourcePosition, targetPosition);

        // then
        assertThat(directions).contains(Direction.LEFT_UP);
    }

    @DisplayName("목표위치와 비교했을 때 기준위치의 방향이 왼쪽아래를 포함함을 알려준다.")
    @Test
    void ofLEFT_DOWN() {
        // given
        Position sourcePosition = new Position(File.C, Rank.THREE);
        Position targetPosition = new Position(File.D, Rank.FOUR);

        // when
        List<Direction> directions = Direction.of(sourcePosition, targetPosition);

        // then
        assertThat(directions).contains(Direction.LEFT_DOWN);
    }
}
