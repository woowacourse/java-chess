package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.Movement;
import chess.domain.Position;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Team;
import chess.exception.ImpossibleMoveException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RookTest {
    @DisplayName("자신의 특징을 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"BLACK,BLACK_ROOK", "WHITE,WHITE_ROOK"})
    void findCharacter(Team team, Character character) {
        assertThat(new Rook(team).findCharacter())
                .isEqualTo(character);
    }

    @DisplayName("룩은 직선이 아닌 경우, 예외가 발생한다.")
    @Test
    void rookMoveOverLine() {
        assertThatThrownBy(() -> new Rook(Team.WHITE)
                .findBetweenPositions(new Movement(
                        Position.of(1, 1),
                        Position.of(2, 2))))
                .isInstanceOf(ImpossibleMoveException.class)
                .hasMessage("해당 위치로 움직일 수 없습니다.");
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
