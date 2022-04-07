package chess.domain.piece;

import static chess.constants.TestConstants.PARAMETERIZED_TEST_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PieceTest {

    @Nested
    @DisplayName("생성자는 ")
    class Constructor {

        @Test
        @DisplayName("폰을 생성한다.")
        void constructPawn() {
            assertThatCode(() -> new Pawn(Color.BLACK))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("룩을 생성한다.")
        void constructRook() {
            assertThatCode(() -> new Rook(Color.BLACK))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("나이트를 생성한다.")
        void constructKnight() {
            assertThatCode(() -> new Knight(Color.BLACK))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("비숍을 생성한다.")
        void constructBishop() {
            assertThatCode(() -> new Bishop(Color.BLACK))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("퀸을 생성한다.")
        void constructQueen() {
            assertThatCode(() -> new Queen(Color.BLACK))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("킹을 생성한다.")
        void constructKing() {
            assertThatCode(() -> new King(Color.BLACK))
                    .doesNotThrowAnyException();
        }

        @ParameterizedTest(name = PARAMETERIZED_TEST_NAME)
        @CsvSource(value = {"BLACK, true", "WHITE, false"})
        @DisplayName("말끼리 서로 같은 편인지 확인한다.")
        void isSameColor(Color color, boolean expected) {
            Rook rook = new Rook(Color.BLACK);
            King king = new King(color);
            assertThat(rook.isSameColor(king)).isEqualTo(expected);
        }
    }

    @ParameterizedTest(name = PARAMETERIZED_TEST_NAME)
    @CsvSource(value = {"WHITE, p", "BLACK, P"})
    @DisplayName("색깔에 맞는 symbol을 반환한다.")
    void getSymbol(Color color, String expected) {
        Piece piece = new Pawn(color);
        assertThat(piece.getSymbol()).isEqualTo(expected);
    }

    @Test
    @DisplayName("색이 Empty 확인한다.")
    void isEmpty() {
        Piece piece = EmptyPiece.getInstance();
        assertThat(piece.isEmpty()).isTrue();
    }
}
