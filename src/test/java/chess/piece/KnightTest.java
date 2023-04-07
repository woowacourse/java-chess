package chess.piece;

import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Knight;
import chess.domain.piece.Queen;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Knight 클래스")
public class KnightTest {

    @Nested
    @DisplayName("of 메서드는")
    class of {
        @Nested
        @DisplayName("진영이 주어지면")
        class context {
            @Test
            @DisplayName("해당 진영의 Knight를 2개 생성한다")
            void it_returns_knights() {
                assertThat(Knight.getKnightsOf(Color.BLACK)).hasSize(2);
            }
        }
    }

    @Nested
    @DisplayName("isMovable 메서드는")
    class isMovable {
        Knight whiteKnight = Knight.getKnightsOf(Color.WHITE)
                                   .get(0);
        Position fromPosition = Position.of(Rank.FOUR, File.D);
        Position movablePosition1 = Position.of(Rank.TWO, File.C);
        Position movablePosition2 = Position.of(Rank.FIVE, File.F);
        Position unMovablePosition = Position.of(Rank.TWO, File.B);
        Knight whiteKnight2 = Knight.getKnightsOf(Color.WHITE)
                                    .get(1);
        Queen blackQueen = Queen.getQueenOf(Color.BLACK);

        @Nested
        @DisplayName("갈 수 있는 위치에")
        class context1 {
            @Nested
            @DisplayName("아군 기물이 존재하면")
            class context2 {
                @Test
                @DisplayName("false를 반환한다")
                void it_returns_false() {
                    assertThat(whiteKnight.isValidMove(fromPosition, movablePosition1, whiteKnight2)).isFalse();
                }
            }

            @Nested
            @DisplayName("아군 기물이 존재하지 않으면")
            class context3 {
                @Test
                @DisplayName("true를 반환한다")
                void it_returns_true() {
                    assertAll(
                            () -> assertThat(whiteKnight.isValidMove(fromPosition, movablePosition1, blackQueen)).isTrue(),
                            () -> assertThat(whiteKnight.isValidMove(fromPosition, movablePosition2, blackQueen)).isTrue(),
                            () -> assertThat(whiteKnight.isValidMove(fromPosition, movablePosition1, EmptyPiece.getInstance())).isTrue()
                    );
                }
            }
        }

        @Nested
        @DisplayName("갈 수 없는 위치라면")
        class context4 {
            @Test
            @DisplayName("false를 반환한다")
            void it_returns_false() {
                assertThat(whiteKnight.isValidMove(fromPosition, unMovablePosition, blackQueen)).isFalse();
            }
        }
    }
}
