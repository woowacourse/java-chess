package chess.domain.piece;

import static chess.constants.TestConstants.PARAMETERIZED_TEST_NAME;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PawnTest {

    @Test
    @DisplayName("폰은 1점이다.")
    void getPoint() {
        Piece pawn = new Pawn(Color.BLACK);
        assertThat(pawn.getPoint()).isEqualTo(1);
    }

    @Nested
    @DisplayName("canMove 메서드 ")
    class CanMoveByColor {

        @ParameterizedTest(name = PARAMETERIZED_TEST_NAME)
        @CsvSource(value = {"d5, true", "c5, true", "e5, true", "d6, false", "f6, false"})
        @DisplayName("화이트 폰은 전진 방향과 대각선 전진 방향으로 움직일 수 있다.")
        void canMoveWhite(String position, boolean expected) {
            Piece piece = new Pawn(Color.WHITE);
            Position fromPosition = Position.of("d4");
            Position toPosition = Position.of(position);

            assertThat(piece.canMove(fromPosition, toPosition)).isEqualTo(expected);
        }


        @ParameterizedTest(name = PARAMETERIZED_TEST_NAME)
        @CsvSource(value = {"d3, true", "d4, true", "c3, true", "e3, true"})
        @DisplayName("처음 움직이는 화이트 폰은 두칸 더 전진할 수 있다.")
        void canMoveWhiteFirstMove(String position, boolean expected) {
            Piece piece = new Pawn(Color.WHITE);
            Position fromPosition = Position.of("d2");
            Position toPosition = Position.of(position);

            assertThat(piece.canMove(fromPosition, toPosition)).isEqualTo(expected);
        }

        @ParameterizedTest(name = PARAMETERIZED_TEST_NAME)
        @CsvSource(value = {"d3, true", "c3, true", "e3, true", "d2, false"})
        @DisplayName("블랙 폰은 전진 방향과 대각선 전진 방향으로 움직일 수 있다.")
        void canMoveBlack(String position, boolean expected) {
            Piece piece = new Pawn(Color.BLACK);
            Position fromPosition = Position.of("d4");
            Position toPosition = Position.of(position);

            assertThat(piece.canMove(fromPosition, toPosition)).isEqualTo(expected);
        }

        @ParameterizedTest(name = PARAMETERIZED_TEST_NAME)
        @CsvSource(value = {"d6, true", "d5, true", "c6, true", "e6, true"})
        @DisplayName("처음 움직이는 블랙 폰은 전진 방향과 대각선 전진 방향으로 움직일 수 있다.")
        void canMoveBlackFirstMove(String position, boolean expected) {
            Piece piece = new Pawn(Color.BLACK);
            Position fromPosition = Position.of("d7");
            Position toPosition = Position.of(position);

            assertThat(piece.canMove(fromPosition, toPosition)).isEqualTo(expected);
        }
    }

}
