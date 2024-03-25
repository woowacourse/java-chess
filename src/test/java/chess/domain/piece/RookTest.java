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

class RookTest {
    @DisplayName("자신의 특징을 반환한다.")
    @ParameterizedTest
    @EnumSource
    void findCharacter(Team team) {
        assertThat(new Rook(team).character())
                .isEqualTo(new Character(team, Kind.ROOK));
    }

    @DisplayName("룩은 직선으로 움직일 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"4,1", "4,2", "4,3", "4,5", "4,6", "4,7", "4,8",
            "1,4", "2,4", "3,4", "5,4", "6,4", "7,4", "8,4"})
    void queenIsMovable(int row, int column) {
        assertThat(new Rook(Team.WHITE)
                .isMovable(new Movement(
                        Position.of(4, 4),
                        Position.of(row, column))))
                .isTrue();
    }

    @DisplayName("룩은 직선이 아닌 경우, 움직일 수 없다.")
    @Test
    void rookMoveOverLine() {
        assertThat(new Rook(Team.WHITE)
                .isMovable(new Movement(
                        Position.of(1, 1),
                        Position.of(2, 2))))
                .isFalse();
    }

    @DisplayName("두 위치 사이의 룩이 갈 수 있는 위치들을 반환한다.")
    @Test
    void betweenPosition() {
        assertThat(new Rook(Team.WHITE)
                .findBetweenPositions(new Movement(
                        Position.of(4, 4),
                        Position.of(4, 7))))
                .containsExactly(Position.of(4, 5), Position.of(4, 6));
    }

    @DisplayName("두 위치 사이의 룩이 갈 수 있는 위치들을 반환한다.")
    @Test
    void betweenPositionMinus() {
        assertThat(new Rook(Team.WHITE)
                .findBetweenPositions(new Movement(
                        Position.of(4, 4),
                        Position.of(1, 4))))
                .containsExactly(Position.of(3, 4), Position.of(2, 4));
    }
}
