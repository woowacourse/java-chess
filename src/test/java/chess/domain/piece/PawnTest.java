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
import org.junit.jupiter.params.provider.EnumSource;

class PawnTest {
    @DisplayName("자신의 특징을 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"BLACK,BLACK_PAWN", "WHITE,WHITE_PAWN"})
    void findCharacter(Team team, Character character) {
        assertThat(new Pawn(Position.of(1, 1), team).findCharacter())
                .isEqualTo(character);
    }

    @DisplayName("흰색 폰은 시작 지점에 있는 경우, 앞으로 두칸 혹은 한칸 전진할 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"3,1", "4,1"})
    void startWhitePawnMove(int row, int column) {
        assertThatCode(() -> new Pawn(Position.of(2, 1), Team.WHITE)
                .move(Position.of(row, column)))
                .doesNotThrowAnyException();
    }

    @DisplayName("검은색 폰은 시작 지점에 있는 경우, 앞으로 두칸 혹은 한칸 전진할 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"6,1", "5,1"})
    void startBlackPawnMove(int row, int column) {
        assertThatCode(() -> new Pawn(Position.of(7, 1), Team.BLACK)
                .move(Position.of(row, column)))
                .doesNotThrowAnyException();
    }

    @DisplayName("흰색 폰은 시작 지점에 있는 경우, 앞으로 한칸 전진할 수 있다.")
    @Test
    void whitePawnMove() {
        assertThatCode(() -> new Pawn(Position.of(3, 1), Team.WHITE)
                .move(Position.of(4, 1)))
                .doesNotThrowAnyException();
    }

    @DisplayName("검은색 폰은 시작 지점에 있는 경우, 앞으로 한칸 전진할 수 있다.")
    @Test
    void blackPawnMove() {
        assertThatCode(() -> new Pawn(Position.of(6, 1), Team.BLACK)
                .move(Position.of(5, 1)))
                .doesNotThrowAnyException();
    }

    @DisplayName("흰색 폰은 시작 지점에 있는 경우, 2칸 초과시 예외가 발생한다.")
    @Test
    void startWhitePawnMoveOverTwo() {
        assertThatThrownBy(() -> new Pawn(Position.of(2, 1), Team.WHITE)
                .move(Position.of(5, 1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 움직일 수 없습니다.");
    }

    @DisplayName("검은색 폰은 시작 지점에 있는 경우, 2칸 초과시 예외가 발생한다.")
    @Test
    void startBlackPawnMoveOverTwo() {
        assertThatThrownBy(() -> new Pawn(Position.of(7, 1), Team.BLACK)
                .move(Position.of(4, 1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 움직일 수 없습니다.");
    }

    @DisplayName("흰색 폰은 1칸 초과시 예외가 발생한다.")
    @Test
    void whitePawnMoveOverTwo() {
        assertThatThrownBy(() -> new Pawn(Position.of(3, 1), Team.WHITE)
                .move(Position.of(5, 1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 움직일 수 없습니다.");
    }

    @DisplayName("검은색 폰은 1칸 초과시 예외가 발생한다.")
    @Test
    void blackPawnMoveOverTwo() {
        assertThatThrownBy(() -> new Pawn(Position.of(6, 1), Team.BLACK)
                .move(Position.of(4, 1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 움직일 수 없습니다.");
    }

    @DisplayName("흰색 폰은 옆으로 이동할 수 없다.")
    @ParameterizedTest
    @EnumSource
    void whitePawnMoveColumn(Team team) {
        assertThatThrownBy(() -> new Pawn(Position.of(7, 1), team)
                .move(Position.of(7, 2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 움직일 수 없습니다.");
    }

}