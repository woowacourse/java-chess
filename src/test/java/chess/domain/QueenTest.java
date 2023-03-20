package chess.domain;

import chess.domain.chesspiece.Bishop;
import chess.domain.chesspiece.EmptyPiece;
import chess.domain.chesspiece.Queen;
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
                Queen queen = Queen.of(Side.BLACK);
                assertThat(queen.getSide()).isEqualTo("BLACK");
            }
        }
    }

    @Nested
    @DisplayName("isMovable 메서드는")
    class isMovable {
        @Nested
        @DisplayName("자신의 위치와 이동하려는 위치, 해당 위치에 존재하는 기물이 주어지면")
        class given_another_piece {
            Queen whiteQueen = Queen.of(Side.WHITE);
            Square from = Square.of(Rank.FOUR, File.D);
            Square movableSquare1 = Square.of(Rank.ONE, File.G);
            Square movableSquare2 = Square.of(Rank.FOUR, File.H);
            Square unable = Square.of(Rank.THREE, File.B);
            Bishop whiteBishop = Bishop.of(Side.WHITE)
                    .get(0);
            Queen blackQueen = Queen.of(Side.BLACK);

            @Test
            @DisplayName("갈 수 있고 해당 위치의 기물이 아군 기물이 아닌 경우 true를 반환한다")
            void it_returns_movable() {
                assertAll(
                        () -> assertThat(whiteQueen.isMovable(from, movableSquare1, blackQueen)).isTrue(),
                        () -> assertThat(whiteQueen.isMovable(from, movableSquare2, blackQueen)).isTrue(),
                        () -> assertThat(whiteQueen.isMovable(from, movableSquare1, EmptyPiece.getInstance())).isTrue()
                );
            }

            @Test
            @DisplayName("갈 수 있고 해당 위치의 기물이 같은 진영인 경우 false를 반환한다")
            void it_returns_not_movable1() {
                assertThat(whiteQueen.isMovable(from, movableSquare1, whiteBishop)).isFalse();
            }

            @Test
            @DisplayName("갈 수 없고 해당 위치의 기물이 상대 진영인 경우 false를 반환한다")
            void it_returns_not_movable2() {
                assertThat(whiteQueen.isMovable(from, unable, blackQueen)).isFalse();
            }
        }
    }
}
