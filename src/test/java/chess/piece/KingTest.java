package chess.piece;

import chess.chessboard.File;
import chess.chessboard.Position;
import chess.chessboard.Rank;
import chess.chessboard.Side;
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
            Position source = Position.of(Rank.FOUR, File.D);
            Position movablePosition1 = Position.of(Rank.FIVE, File.D);
            Position movablePosition2 = Position.of(Rank.FIVE, File.E);
            Position movablePosition3 = Position.of(Rank.FOUR, File.C);
            Position movablePosition4 = Position.of(Rank.THREE, File.D);
            Position movablePosition5 = Position.of(Rank.THREE, File.E);
            Queen whiteQueen = Queen.getQueenOf(Side.WHITE);
            Queen blackQueen = Queen.getQueenOf(Side.BLACK);

            @Test
            @DisplayName("갈 수 있고 해당 위치의 기물이 아군 기물이 아닌 경우 true를 반환한다")
            void it_returns_movable() {
                assertAll(
                        () -> assertThat(whiteKing.isMovable(source, movablePosition1, blackQueen)).isTrue(),
                        () -> assertThat(whiteKing.isMovable(source, movablePosition2, EmptyPiece.getInstance())).isTrue(),
                        () -> assertThat(whiteKing.isMovable(source, movablePosition3, blackQueen)).isTrue(),
                        () -> assertThat(whiteKing.isMovable(source, movablePosition4, EmptyPiece.getInstance())).isTrue(),
                        () -> assertThat(whiteKing.isMovable(source, movablePosition5, blackQueen)).isTrue()
                );
            }

            @Test
            @DisplayName("갈 수 있고 해당 위치의 기물이 같은 진영인 경우 false를 반환한다")
            void it_returns_not_movable1() {
                assertThat(whiteKing.isMovable(source, movablePosition1, whiteQueen)).isFalse();
            }

            @Test
            @DisplayName("갈 수 없는 경우 false를 반환한다")
            void it_returns_not_movable2() {
                Position unablePosition1 = Position.of(Rank.FOUR, File.F);
                Position unablePosition2 = Position.of(Rank.ONE, File.D);
                assertThat(whiteKing.isMovable(source, unablePosition1, blackQueen)).isFalse();
                assertThat(whiteKing.isMovable(source, unablePosition2, EmptyPiece.getInstance())).isFalse();
            }
        }
    }
}
