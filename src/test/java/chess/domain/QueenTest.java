package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Queen 클래스")
public class QueenTest {

    @Nested
    @DisplayName("of 메서드는")
    class of {
        @Nested
        @DisplayName("진영이 주어지면")
        class given_team {
            @Test
            @DisplayName("해당 진영의 Queen을 1개 생성한다")
            void it_returns_queen() {
                Queen queen = Queen.getQueenOf(Side.BLACK);
                assertThat(queen.getSide()).isEqualTo("BLACK");
            }
        }
    }

    @Nested
    @DisplayName("isMovable 메서드는")
    class isMovable {
        Queen whiteQueen = Queen.getQueenOf(Side.WHITE);
        Square fromSquare = Square.of(Rank.FOUR, File.D);
        Square movableSquare1 = Square.of(Rank.ONE, File.G);
        Square movableSquare2 = Square.of(Rank.FOUR, File.H);
        Square unMovableSquare = Square.of(Rank.THREE, File.B);
        Bishop whiteBishop = Bishop.getBishopsOf(Side.WHITE)
                                   .get(0);
        Queen blackQueen = Queen.getQueenOf(Side.BLACK);

        @Nested
        @DisplayName("이동할 수 있는 위치에 아군 기물이 있지 않다면")
        class context1 {
            @Test
            @DisplayName("true를 반환한다")
            void it_returns_true() {
                assertAll(
                        () -> assertThat(whiteQueen.isMovable(fromSquare, movableSquare1, blackQueen)).isTrue(),
                        () -> assertThat(whiteQueen.isMovable(fromSquare, movableSquare2, blackQueen)).isTrue(),
                        () -> assertThat(whiteQueen.isMovable(fromSquare, movableSquare1, EmptyPiece.getInstance())).isTrue()
                );
            }
        }

        @Nested
        @DisplayName("이동할 수 있는 위치에 아군 기물이 있다면")
        class context2 {
            @Test
            @DisplayName("false를 반환한다")
            void it_returns_false() {
                assertThat(whiteQueen.isMovable(fromSquare, movableSquare1, whiteBishop)).isFalse();
            }
        }

        @Nested
        @DisplayName("이동할 수 없는 위치라면")
        class context3 {
            @Test
            @DisplayName("false를 반환한다")
            void it_returns_false() {
                assertThat(whiteQueen.isMovable(fromSquare, unMovableSquare, blackQueen)).isFalse();
            }
        }
    }
}
