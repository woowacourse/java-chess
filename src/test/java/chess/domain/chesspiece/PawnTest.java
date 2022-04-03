package chess.domain.chesspiece;

import static chess.domain.chesspiece.Color.BLACK;
import static chess.domain.chesspiece.Color.WHITE;
import static chess.domain.chesspiece.Pawn.from;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnTest {

    @ParameterizedTest
    @DisplayName("초기 위치에서 이동 할 수 있는 위치라면 예외를 던지지 않는다.")
    @CsvSource(value = {"WHITE:b2:b3", "WHITE:b2:b4", "BLACK:a7:a6", "BLACK:a7:a5"}, delimiter = ':')
    void canMove(Color color, String fromValue, String toValue) {
        // given
        final Position from = Position.from(fromValue);
        final Position to = Position.from(toValue);
        final ChessPiece pawn = from(color);

        // then
        assertThatCode(() -> pawn.checkMovablePosition(from, to, null))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("폰의 직진 목적지에 같은색의 기물이 있으면 예외를 발생시킵니다.")
    void checkMovablePosition_straight_sameColor() {
        // given
        final Position from = Position.from("d5");
        final Position to = Position.from("d6");
        final ChessPiece pawn = from(WHITE);

        // then
        assertThatThrownBy(() -> pawn.checkMovablePosition(from, to, Pawn.from(WHITE)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은색 기물입니다.");
    }

    @Test
    @DisplayName("폰의 직진 목적지에 다른색의 기물이 있으면 예외를 발생시킵니다.")
    void checkMovablePosition_straight_notSameColor() {
        // given
        final Position from = Position.from("d5");
        final Position to = Position.from("d6");
        final ChessPiece pawn = from(WHITE);

        // then
        assertThatThrownBy(() -> pawn.checkMovablePosition(from, to, Pawn.from(BLACK)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 대각선 이동으로만 적을 잡을 수 있습니다.");
    }

    @ParameterizedTest
    @DisplayName("이동 할 수 없는 위치로 이동하면 예외를 던진다.")
    @CsvSource(value = {"BLACK:d6", "BLACK:e6", "BLACK:e5", "BLACK:c5", "BLACK:c6",
            "WHITE:e5", "WHITE:e4", "WHITE:d4", "WHITE:c4", "WHITE:c5"}, delimiter = ':')
    void canMove_exception(final Color color, final String target) {
        // given
        final Position from = Position.from("d5");
        final Position to = Position.from(target);
        final ChessPiece pawn = from(color);

        // then
        assertThatThrownBy(() -> pawn.checkMovablePosition(from, to, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 기물이 갈 수 없는 위치입니다.");
    }

    @ParameterizedTest
    @DisplayName("이동 할 수 없는 위치로 이동하면 예외를 던진다.")
    @CsvSource(value = {"WHITE:a2:b2", "WHITE:a2:b1", "WHITE:a2:a1",
            "BLACK:a7:a8", "BLACK:a7:b8", "BLACK:a7:b7"}, delimiter = ':')
    void canMove_exception2(final Color color, final String fromValue, final String target) {
        // given
        final Position from = Position.from(fromValue);
        final Position to = Position.from(target);
        final ChessPiece pawn = from(color);

        // then
        assertThatThrownBy(() -> pawn.checkMovablePosition(from, to, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 기물이 갈 수 없는 위치입니다.");
    }

    @ParameterizedTest
    @DisplayName("처음 위치에서 2칸 이상 이동하면 예외를 던진다.")
    @CsvSource(value = {"BLACK:d7:d4", "WHITE:d2:d5"}, delimiter = ':')
    void canMove_initFile_exception(final Color color, final String from, final String to) {
        // given
        final ChessPiece pawn = from(color);

        // then
        assertThatThrownBy(() -> pawn.checkMovablePosition(Position.from(from), Position.from(to), null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 기물이 갈 수 없는 위치입니다.");
    }

    @ParameterizedTest
    @DisplayName("대각선이 다른 색 기물이면 예외를 던지지 않는다.")
    @CsvSource(value = {"BLACK:h5:g4", "WHITE:h4:g5", "BLACK:d5:e4", "BLACK:d5:c4", "WHITE:d5:c6",
            "WHITE:d5:e6"}, delimiter = ':')
    void checkMove_cross(final Color color, final String fromInput, final String target) {
        // given
        final Position from = Position.from(fromInput);
        final Position to = Position.from(target);
        final Pawn pawn = from(color);

        // then
        assertThatCode(() -> pawn.checkMovablePosition(from, to, Pawn.from(color.toOpposite())))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("대각선이 빈 칸이면 예외를 던진다.")
    @CsvSource(value = {"BLACK:e4", "BLACK:c4", "WHITE:c6", "WHITE:e6"}, delimiter = ':')
    void checkCrossMove(final Color color, final String target) {
        // given
        final Position from = Position.from("d5");
        final Position to = Position.from(target);
        final Pawn pawn = from(color);

        // then
        assertThatThrownBy(() -> pawn.checkMovablePosition(from, to, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 상대 기물이 존재할 때만 대각선으로 이동할 수 있습니다.");
    }

    @ParameterizedTest
    @DisplayName("대각선에 같은색 기물이 존재하면 예외를 던진다.")
    @CsvSource(value = {"BLACK:e4", "BLACK:c4", "WHITE:c6", "WHITE:e6"}, delimiter = ':')
    void checkCrossMove_sameColor(final Color color, final String target) {
        // given
        final Position from = Position.from("d5");
        final Position to = Position.from(target);
        final Pawn pawn = from(color);

        // then
        assertThatThrownBy(() -> pawn.checkMovablePosition(from, to, Pawn.from(color)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은색 기물입니다.");
    }

    @ParameterizedTest
    @DisplayName("대각선으로 이동 할 수 없는 위치라면 예외를 던진다.")
    @CsvSource(value = {"BLACK:f4", "BLACK:b4", "WHITE:b6", "WHITE:f6"}, delimiter = ':')
    void checkCrossMove_exception(final Color color, final String target) {
        // given
        final Position from = Position.from("d5");
        final Position to = Position.from(target);
        final Pawn pawn = from(color);

        // then
        assertThatThrownBy(() -> pawn.checkMovablePosition(from, to, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 기물이 갈 수 없는 위치입니다.");
    }
}
