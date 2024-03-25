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

class KingTest {
    @DisplayName("자신의 특징을 반환한다.")
    @ParameterizedTest
    @EnumSource
    void findCharacter(Team team) {
        assertThat(new King(team).character())
                .isEqualTo(new Character(team, Kind.KING));
    }

    @DisplayName("킹은 한칸 내 전범위로 이동할 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"1,1", "1,2", "1,3", "2,1", "2,3", "3,1", "3,2", "3,3"})
    void kingIsMovable(int row, int column) {
        assertThat(new King(Team.WHITE).isMovable(new Movement(
                Position.of(2, 2),
                Position.of(row, column))))
                .isTrue();
    }

    @DisplayName("킹은 한칸 초과하여 이동할 수 없다.")
    @Test
    void kingIsNotMovable() {
        assertThat(new King(Team.WHITE).isMovable(new Movement(
                Position.of(1, 1),
                Position.of(3, 3))))
                .isFalse();
    }

    @DisplayName("두 위치 사이의 킹이 갈 수 있는 위치들을 반환한다.")
    @Test
    void betweenPosition() {
        assertThat(new King(Team.WHITE)
                .findBetweenPositions(new Movement(
                        Position.of(3, 3),
                        Position.of(2, 2))))
                .isEmpty();
    }
}
