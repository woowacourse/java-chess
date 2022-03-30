package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.piece.fixedmovablepiece.King;
import chess.domain.piece.fixedmovablepiece.Knight;
import chess.domain.piece.pawn.BlackPawn;
import chess.domain.piece.straightmovablepiece.Bishop;
import chess.domain.piece.straightmovablepiece.Queen;
import chess.domain.piece.straightmovablepiece.Rook;
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
        @DisplayName("흑색 폰을 생성한다.")
        void constructPawn() {
            assertThatCode(BlackPawn::new)
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

        @ParameterizedTest
        @CsvSource(value = {"BLACK, true", "WHITE, false"})
        @DisplayName("말끼리 서로 같은 편인지 확인한다.")
        void isSameColor(Color color, boolean expected) {
            Rook rook = new Rook(Color.BLACK);
            King king = new King(color);

            assertThat(rook.isSameColor(king)).isEqualTo(expected);
        }
    }
}
