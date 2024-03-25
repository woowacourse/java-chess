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

class KnightTest {
    @DisplayName("자신의 특징을 반환한다.")
    @ParameterizedTest
    @EnumSource
    void findCharacter(Team team) {
        assertThat(new Knight(team).character())
                .isEqualTo(new Character(team, Kind.KNIGHT));
    }

    @DisplayName("나이트는 날 일 자로 이동할 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"1,2", "2,1", "2,5", "1,4", "5,2", "4,1", "4,5", "5,4"})
    void knightIsMovable(int row, int column) {
        assertThat(new Knight(Team.WHITE).isMovable(new Movement(
                Position.of(3, 3),
                Position.of(row, column))))
                .isTrue();
    }

    @DisplayName("나이트는 날 일 자가 아닌 경우, 이동할 수 없다.")
    @Test
    void knightMoveOverDayHieroglyph() {
        assertThat(new Knight(Team.WHITE).isMovable(new Movement(
                Position.of(1, 1), Position.of(3, 3))))
                .isFalse();
    }

    @DisplayName("두 위치 사이의 나이트가 갈 수 있는 위치들을 반환한다.")
    @Test
    void betweenPosition() {
        assertThat(new Knight(Team.WHITE)
                .findBetweenPositions(new Movement(
                        Position.of(3, 3),
                        Position.of(2, 1))))
                .isEmpty();
    }
}
