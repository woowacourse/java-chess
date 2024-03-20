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

class KingTest {
    @DisplayName("자신의 특징을 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"BLACK,BLACK_KING", "WHITE,WHITE_KING"})
    void findCharacter(Team team, Character character) {
        assertThat(new King(team, true).findCharacter())
                .isEqualTo(character);
    }

    @DisplayName("킹은 한칸 내 전 방향으로 움직일 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"4,3", "3,3", "3,4", "3,5", "4,5", "5,5", "5,4", "5,3"})
    void kingMove(int row, int column) {
        assertThatCode(() -> new King(Team.WHITE, true)
                .validateMovable(Position.of(4, 4), Position.of(row, column)))
                .doesNotThrowAnyException();
    }

    @DisplayName("킹은 한칸 초과하여 움직인 경우, 예외가 발생한다.")
    @Test
    void kingMoveOverOne() {
        assertThatThrownBy(() -> new King(Team.WHITE, true)
                .validateMovable(Position.of(1, 1), Position.of(3, 3)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 움직일 수 없습니다.");
    }
}