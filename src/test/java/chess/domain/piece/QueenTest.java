package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.Position;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class QueenTest {
    @DisplayName("자신의 특징을 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"BLACK,BLACK_QUEEN", "WHITE,WHITE_QUEEN"})
    void findCharacter(Team team, Character character) {
        assertThat(new Queen(team, true).findCharacter())
                .isEqualTo(character);
    }

    @DisplayName("퀸은 직선 혹은 대각선이 아닌 경우, 예외가 발생한다.")
    @Test
    void queenMoveOverLineAndDiagonalLine() {
        assertThatThrownBy(() -> new Queen(Team.WHITE, true)
                .betweenPositions(Position.of(1, 1), Position.of(2, 3)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 움직일 수 없습니다.");
    }

    @DisplayName("두 위치 사이의 퀸이 갈 수 있는 위치들을 반환한다.")
    @Test
    void betweenPositionDiagonal() {
        assertThat(new Queen(Team.WHITE, true)
                .betweenPositions(Position.of(4, 4), Position.of(7, 7)))
                .containsExactly(Position.of(5, 5), Position.of(6, 6));
    }

    @DisplayName("두 위치 사이의 퀸이 갈 수 있는 위치들을 반환한다.")
    @Test
    void betweenPositionDiagonalMinus() {
        assertThat(new Queen(Team.WHITE, true)
                .betweenPositions(Position.of(4, 4), Position.of(1, 1)))
                .containsExactly(Position.of(3, 3), Position.of(2, 2));
    }

    @DisplayName("두 위치 사이의 퀸이 갈 수 있는 위치들을 반환한다.")
    @Test
    void betweenPositionLine() {
        assertThat(new Queen(Team.WHITE, true)
                .betweenPositions(Position.of(4, 4), Position.of(4, 7)))
                .containsExactly(Position.of(4, 5), Position.of(4, 6));
    }

    @DisplayName("두 위치 사이의 퀸이 갈 수 있는 위치들을 반환한다.")
    @Test
    void betweenPositionLineMinus() {
        assertThat(new Queen(Team.WHITE, true)
                .betweenPositions(Position.of(4, 4), Position.of(1, 4)))
                .containsExactly(Position.of(3, 4), Position.of(2, 4));
    }
}