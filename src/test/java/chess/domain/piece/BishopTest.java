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

class BishopTest {
    @DisplayName("자신의 특징을 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"BLACK,BLACK_BISHOP", "WHITE,WHITE_BISHOP"})
    void findCharacter(Team team, Character character) {
        assertThat(new Bishop(Position.of(1, 1), team).findCharacter())
                .isEqualTo(character);
    }

    @DisplayName("비숍은 대각선으로는 움직일 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"5,5", "3,5", "5,3", "3,3"})
    void bishopMove(int row, int column) {
        assertThatCode(() -> new Bishop(Position.of(4, 4), Team.WHITE)
                .move(Position.of(row, column)))
                .doesNotThrowAnyException();
    }

    @DisplayName("비숍은 대각선이 아닌 경우, 예외가 발생한다.")
    @Test
    void bishopMoveOverDiagonalLine() {
        assertThatThrownBy(() -> new Bishop(Position.of(1, 3), Team.WHITE)
                .move(Position.of(1, 4)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 움직일 수 없습니다.");
    }
}