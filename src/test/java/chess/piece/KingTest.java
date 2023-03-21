package chess.piece;

import chess.chessboard.File;
import chess.chessboard.Rank;
import chess.chessboard.Side;
import chess.chessboard.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("King 클래스")
public class KingTest {

    @Nested
    @DisplayName("of 메서드는")
    class of {
        @Nested
        @DisplayName("진영이 주어지면")
        class given_team {
            @Test
            @DisplayName("해당 진영의 King을 1개 생성한다")
            void it_returns_king() {
                King king = King.getKingOf(Side.BLACK);
                assertThat(king.getSide()).isEqualTo(Side.BLACK);
            }
        }
    }

    @Nested
    @DisplayName("isMovable 메서드는")
    class isMovable {
        @Nested
        @DisplayName("자신의 위치와 이동하려는 위치, 해당 위치에 존재하는 기물이 주어지면")
        class given_another_piece {
            King whiteKing = King.getKingOf(Side.WHITE);
            Square from = Square.of(Rank.FOUR, File.D);
            Square movableSquare1 = Square.of(Rank.FIVE, File.D);
            Square movableSquare2 = Square.of(Rank.FIVE, File.E);
            Square movableSquare3 = Square.of(Rank.FOUR, File.C);
            Square movableSquare4 = Square.of(Rank.THREE, File.D);
            Square movableSquare5 = Square.of(Rank.THREE, File.E);
            Queen whiteQueen = Queen.getQueenOf(Side.WHITE);
            Queen blackQueen = Queen.getQueenOf(Side.BLACK);

            @Test
            @DisplayName("갈 수 있고 해당 위치의 기물이 아군 기물이 아닌 경우 true를 반환한다")
            void it_returns_movable() {
                assertAll(
                        () -> assertThat(whiteKing.isMovable(from, movableSquare1, blackQueen)).isTrue(),
                        () -> assertThat(whiteKing.isMovable(from, movableSquare2, EmptyPiece.getInstance())).isTrue(),
                        () -> assertThat(whiteKing.isMovable(from, movableSquare3, blackQueen)).isTrue(),
                        () -> assertThat(whiteKing.isMovable(from, movableSquare4, EmptyPiece.getInstance())).isTrue(),
                        () -> assertThat(whiteKing.isMovable(from, movableSquare5, blackQueen)).isTrue()
                );
            }

            @Test
            @DisplayName("갈 수 있고 해당 위치의 기물이 같은 진영인 경우 false를 반환한다")
            void it_returns_not_movable1() {
                assertThat(whiteKing.isMovable(from, movableSquare1, whiteQueen)).isFalse();
            }

            @Test
            @DisplayName("갈 수 없는 경우 false를 반환한다")
            void it_returns_not_movable2() {
                Square unableSquare1 = Square.of(Rank.FOUR, File.F);
                Square unableSquare2 = Square.of(Rank.ONE, File.D);
                assertThat(whiteKing.isMovable(from, unableSquare1, blackQueen)).isFalse();
                assertThat(whiteKing.isMovable(from, unableSquare2, EmptyPiece.getInstance())).isFalse();
            }
        }
    }
}
