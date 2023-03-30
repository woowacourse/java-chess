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

@DisplayName("Pawn 클래스")
class PawnTest {

    @Nested
    @DisplayName("of 메서드는")
    class of {
        @Nested
        @DisplayName("진영이 주어지면")
        class given_team {
            @Test
            @DisplayName("해당 진영의 Pawn을 8개 생성한다")
            void it_returns_pawns() {
                assertThat(Pawn.getPawnsOf(Side.BLACK)).hasSize(8);
            }
        }
    }

    @Nested
    @DisplayName("isMovable 메서드는")
    class isMovable {
        Pawn whitePawn = Pawn.getPawnsOf(Side.WHITE)
                             .get(0);
        Queen blackQueen = Queen.getQueenOf(Side.BLACK);
        Queen whiteQueen = Queen.getQueenOf(Side.WHITE);

        @Nested
        @DisplayName("움직인적 없는 Pawn에 대해")
        class context1 {
            Position initialPosition = Position.of(Rank.TWO, File.A);
            Position onePositionAhead = Position.of(Rank.FOUR, File.A);
            Position twoPositionAhead = Position.of(Rank.THREE, File.A);
            Position oneDiagonalPositionAhead = Position.of(Rank.THREE, File.B);


            @Test
            @DisplayName("한 칸 혹은 두 칸 앞에 기물이 없으면 true를 반환한다")
            void it_returns_true1() {
                assertAll(
                        () -> assertThat(whitePawn.isValidMove(initialPosition, onePositionAhead, EmptyPiece.getInstance())).isTrue(),
                        () -> assertThat(whitePawn.isValidMove(initialPosition, twoPositionAhead, EmptyPiece.getInstance())).isTrue()
                );
            }

            @Test
            @DisplayName("대각선 한 칸 앞에 적 기물이 있으면 true를 반환한다")
            void it_returns_true2() {
                assertThat(whitePawn.isValidMove(initialPosition, oneDiagonalPositionAhead, blackQueen)).isTrue();
            }

            @Test
            @DisplayName("이동 불가능한 위치라면 false를 반환한다")
            void it_returns_false1() {
                Position sameRankPosition = Position.of(Rank.TWO, File.B);
                Position backPosition = Position.of(Rank.ONE, File.A);

                assertThat(whitePawn.isValidMove(initialPosition, sameRankPosition, blackQueen)).isFalse();
                assertThat(whitePawn.isValidMove(initialPosition, backPosition, blackQueen)).isFalse();
            }

            @Test
            @DisplayName("한 칸 혹은 두 칸 앞에 기물이 있으면 false를 반환한다")
            void it_returns_false2() {
                assertThat(whitePawn.isValidMove(initialPosition, onePositionAhead, blackQueen)).isFalse();
                assertThat(whitePawn.isValidMove(initialPosition, twoPositionAhead, blackQueen)).isFalse();
            }

            @Test
            @DisplayName("대각선 한 칸 앞에 적 기물이 아니면 false를 반환한다")
            void it_returns_false3() {
                assertThat(whitePawn.isValidMove(initialPosition, oneDiagonalPositionAhead, whiteQueen)).isFalse();
                assertThat(whitePawn.isValidMove(initialPosition, oneDiagonalPositionAhead, EmptyPiece.getInstance())).isFalse();
            }
        }

        @Nested
        @DisplayName("이동한 적 있는 Pawn에 대하여")
        class context2 {
            Position fromPosition = Position.of(Rank.THREE, File.B);
            Position onePositionAhead = Position.of(Rank.FOUR, File.B);
            Position oneLeftDiagonalPositionAhead = Position.of(Rank.FOUR, File.A);
            Position oneRightDiagonalPositionAhead = Position.of(Rank.FOUR, File.C);
            Position twoSquaresAhead = Position.of(Rank.FIVE, File.B);
            Position sameRankPosition = Position.of(Rank.THREE, File.C);
            Position backPosition = Position.of(Rank.TWO, File.B);


            @Test
            @DisplayName("한 칸 앞에 기물이 없으면 true를 반환한다")
            void it_returns_movable1() {
                assertThat(whitePawn.isValidMove(fromPosition, onePositionAhead, EmptyPiece.getInstance())).isTrue();
            }

            @Test
            @DisplayName("대각선 한 칸 앞에 적 기물이 있으면 true를 반환한다")
            void it_returns_movable2() {
                assertThat(whitePawn.isValidMove(fromPosition, oneLeftDiagonalPositionAhead, blackQueen)).isTrue();
                assertThat(whitePawn.isValidMove(fromPosition, oneRightDiagonalPositionAhead, blackQueen)).isTrue();
            }

            @Test
            @DisplayName("이동 불가능한 위치라면 false를 반환한다")
            void it_returns_not_movable1() {
                assertThat(whitePawn.isValidMove(fromPosition, twoSquaresAhead, blackQueen)).isFalse();
                assertThat(whitePawn.isValidMove(fromPosition, sameRankPosition, blackQueen)).isFalse();
                assertThat(whitePawn.isValidMove(fromPosition, backPosition, blackQueen)).isFalse();
            }

            @Test
            @DisplayName("한 칸 앞에 기물이 있으면 false를 반환한다")
            void it_returns_not_movable2() {
                assertThat(whitePawn.isValidMove(fromPosition, onePositionAhead, blackQueen)).isFalse();
            }

            @Test
            @DisplayName("대각선 한 칸 앞에 적 기물이 아니면 false를 반환한다")
            void it_returns_not_movable3() {
                assertThat(whitePawn.isValidMove(fromPosition, oneLeftDiagonalPositionAhead, whiteQueen)).isFalse();
                assertThat(whitePawn.isValidMove(fromPosition, oneRightDiagonalPositionAhead, EmptyPiece.getInstance())).isFalse();
            }
        }
    }
}
