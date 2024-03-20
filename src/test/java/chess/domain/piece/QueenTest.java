package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
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

    @DisplayName("퀸은 직선 및 대각선으로 움직일 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"4,2", "2,2", "2,4", "2,6", "4,6", "6,6", "6,4", "6,2"})
    void queenMove(int row, int column) {
        assertThatCode(() -> new Queen(Team.WHITE, true)
                .validateMovable(Position.of(4, 4), Position.of(row, column)))
                .doesNotThrowAnyException();
    }

    @DisplayName("퀸은 직선 혹은 대각선이 아닌 경우, 예외가 발생한다.")
    @Test
    void rookMoveOverLineAndDiagonalLine() {
        assertThatThrownBy(() -> new Queen(Team.WHITE, true)
                .validateMovable(Position.of(1, 1), Position.of(2, 3)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 움직일 수 없습니다.");
    }
}