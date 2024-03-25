package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Movement;
import chess.domain.Position;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

class QueenTest {
    @DisplayName("자신의 특징을 반환한다.")
    @ParameterizedTest
    @EnumSource
    void findCharacter(Team team) {
        assertThat(new Queen(team).character())
                .isEqualTo(new Character(team, Kind.QUEEN));
    }

    @DisplayName("퀸은 직선 혹은 대각선으로 움직일 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"1,1", "2,2", "3,3", "5,5", "6,6", "7,7", "8,8", "1,7", "2,6", "3,5", "5,3", "6,2", "7,1",
            "4,1", "4,2", "4,3", "4,5", "4,6", "4,7", "4,8", "1,4", "2,4", "3,4", "5,4", "6,4", "7,4", "8,4"})
    void queenIsMovable(int row, int column) {
        assertThat(new Queen(Team.WHITE)
                .isMovable(new Movement(
                        Position.of(4, 4),
                        Position.of(row, column))))
                .isTrue();
    }

    @DisplayName("퀸은 직선 혹은 대각선이 아닌 경우, 움직일 수 없다.")
    @Test
    void queenIsNotMovable() {
        assertThat(new Queen(Team.WHITE)
                .isMovable(new Movement(
                        Position.of(1, 1),
                        Position.of(2, 3))))
                .isFalse();
    }

    @DisplayName("두 위치 사이의 퀸이 갈 수 있는 위치들을 반환한다.")
    @Test
    void betweenPositionDiagonal() {
        assertThat(new Queen(Team.WHITE)
                .findBetweenPositions(new Movement(
                        Position.of(4, 4),
                        Position.of(7, 7))))
                .containsExactly(Position.of(5, 5), Position.of(6, 6));
    }

    @DisplayName("두 위치 사이의 퀸이 갈 수 있는 위치들을 반환한다.")
    @Test
    void betweenPositionDiagonalMinus() {
        assertThat(new Queen(Team.WHITE)
                .findBetweenPositions(new Movement(
                        Position.of(4, 4),
                        Position.of(1, 1))))
                .containsExactly(Position.of(3, 3), Position.of(2, 2));
    }

    @DisplayName("두 위치 사이의 퀸이 갈 수 있는 위치들을 반환한다.")
    @Test
    void betweenPositionLine() {
        assertThat(new Queen(Team.WHITE)
                .findBetweenPositions(new Movement(
                        Position.of(4, 4),
                        Position.of(4, 7))))
                .containsExactly(Position.of(4, 5), Position.of(4, 6));
    }

    @DisplayName("두 위치 사이의 퀸이 갈 수 있는 위치들을 반환한다.")
    @Test
    void betweenPositionLineMinus() {
        assertThat(new Queen(Team.WHITE)
                .findBetweenPositions(new Movement(
                        Position.of(4, 4),
                        Position.of(1, 4))))
                .containsExactly(Position.of(3, 4), Position.of(2, 4));
    }
}
