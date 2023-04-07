package chess.piece;

import chess.domain.piece.Color;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Bishop 클래스")
public class BishopTest {

    @Nested
    @DisplayName("of 메서드는")
    class of {
        @Nested
        @DisplayName("진영이 주어지면")
        class given_team {
            @Test
            @DisplayName("해당 진영의 Bishop을 2개 생성한다")
            void it_returns_bishops() {
                assertThat(Bishop.getBishopsOf(Color.BLACK)).hasSize(2);
            }
        }
    }

    @Nested
    @DisplayName("isMovable 메서드는")
    class isMovable {
        Bishop whiteBishop = Bishop.getBishopsOf(Color.WHITE)
                                   .get(0);
        Position fromPosition = Position.of(Rank.FOUR, File.D);
        Position movablePosition1 = Position.of(Rank.ONE, File.G);
        Position movablePosition2 = Position.of(Rank.SIX, File.B);
        Position unMovablePosition = Position.of(Rank.THREE, File.B);
        Bishop whiteBishop2 = Bishop.getBishopsOf(Color.WHITE)
                                    .get(1);
        Queen blackQueen = Queen.getQueenOf(Color.BLACK);

        @Nested
        @DisplayName("이동 가능한 위치에 ")
        class context1 {
            @Nested
            @DisplayName("아군 기물이 있지 않다면")
            class context2 {
                @Test
                @DisplayName("true를 반환한다")
                void it_returns_true() {
                    assertAll(
                            () -> assertThat(whiteBishop.isValidMove(fromPosition, movablePosition1, blackQueen)).isTrue(),
                            () -> assertThat(whiteBishop.isValidMove(fromPosition, movablePosition2, blackQueen)).isTrue(),
                            () -> assertThat(whiteBishop.isValidMove(fromPosition, movablePosition1, EmptyPiece.getInstance())).isTrue()
                    );
                }
            }

            @Nested
            @DisplayName("아군 기물이 있다면")
            class context3 {
                @Test
                @DisplayName("false를 반환한다")
                void it_returns_false() {
                    assertThat(whiteBishop.isValidMove(fromPosition, movablePosition1, whiteBishop2)).isFalse();
                }
            }
        }

        @Nested
        @DisplayName("갈 수 없는 위치라면")
        class context4 {
            @Test
            @DisplayName("false를 반환한다")
            void it_returns_false() {
                assertThat(whiteBishop.isValidMove(fromPosition, unMovablePosition, blackQueen)).isFalse();
            }
        }
    }
}
