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

class RookTest {
    @DisplayName("자신의 특징을 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"BLACK,BLACK_ROOK", "WHITE,WHITE_ROOK"})
    void findCharacter(Team team, Character character) {
        assertThat(new Rook(team, true).findCharacter())
                .isEqualTo(character);
    }

    @DisplayName("룩은 직선으로는 움직일 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"1,3", "4,1"})
    void rookMove(int row, int column) {
        assertThatCode(() -> new Rook(Team.WHITE, true)
                .validateMovable(Position.of(1, 1), Position.of(row, column)))
                .doesNotThrowAnyException();
    }

    @DisplayName("룩은 직선이 아닌 경우, 예외가 발생한다.")
    @Test
    void rookMoveOverLine() {
        assertThatThrownBy(() -> new Rook(Team.WHITE, true)
                .validateMovable(Position.of(1, 1), Position.of(2, 2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 움직일 수 없습니다.");
    }
}