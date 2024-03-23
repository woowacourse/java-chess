package chess.domain.movement;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BlackPawnFirstMovementTest {

    @Test
    @DisplayName("이동 가능한지 확인한다.")
    void isMovableTest() {
        Position start = new Position(File.D, Rank.SEVEN);
        Position end = new Position(File.D, Rank.FIVE);
        BlackPawnFirstMovement blackPawnFirstMovement = new BlackPawnFirstMovement();

        assertThat(blackPawnFirstMovement.isMovable(start, end, false)).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"C, SEVEN", "D, SIX", "D, FOUR", "D, EIGHT"})
    @DisplayName("이동 가능한지 확인한다.")
    void isMovableTest_false(File file, Rank rank) {
        Position start = new Position(File.D, Rank.SEVEN);
        Position end = new Position(file, rank);
        BlackPawnFirstMovement blackPawnFirstMovement = new BlackPawnFirstMovement();
        boolean isEnemyExistAtEnd = false;

        assertThat(blackPawnFirstMovement.isMovable(start, end, isEnemyExistAtEnd)).isFalse();
    }

    @ParameterizedTest
    @CsvSource({"C, FIVE", "D, SIX", "D, EIGHT"})
    @DisplayName("초기 위치가 아닐 경우, 이동 불가능하다.")
    void isMovableTest_notMatchInitialPosition(File file, Rank rank) {
        Position start = new Position(file, rank);
        Position end = start.moveToSouth().moveToSouth();
        BlackPawnFirstMovement blackPawnFirstMovement = new BlackPawnFirstMovement();
        boolean isEnemyExistAtEnd = false;

        assertThat(blackPawnFirstMovement.isMovable(start, end, isEnemyExistAtEnd)).isFalse();
    }


    @Test
    @DisplayName("이동 경로를 알 수 있다.")
    void findPathTest() {
        Position start = new Position(File.D, Rank.SEVEN);
        Position middle = new Position(File.D, Rank.SIX);
        Position end = new Position(File.D, Rank.FIVE);
        BlackPawnFirstMovement blackPawnFirstMovement = new BlackPawnFirstMovement();
        boolean isEnemyExistAtEnd = false;

        assertThat(blackPawnFirstMovement.findPath(start, end, isEnemyExistAtEnd))
                .containsExactly(middle, end);
    }
}
