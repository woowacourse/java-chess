package chess.domain.piece;

import static chess.domain.attribute.File.D;
import static chess.domain.attribute.File.E;
import static chess.domain.attribute.File.F;
import static chess.domain.attribute.Rank.FIVE;
import static chess.domain.attribute.Rank.FOUR;
import static chess.domain.attribute.Rank.SEVEN;
import static chess.domain.attribute.Rank.SIX;
import static chess.domain.attribute.Rank.THREE;
import static chess.domain.attribute.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PawnTest {

    @DisplayName("폰이 흑인 경우")
    @Nested
    class BlackPawnTest {

        /*
        * * * * * * * * 8    * * * * * * * * 8
        * * * * P * * * 7    * * * * P * * * 7
        * * * * * * * * 6    * * * * o * * * 6
        * * * * * * * * 5    * * * * o * * * 5
        * * * * * * * * 4 -> * * * * * * * * 4
        * * * * * * * * 3    * * * * * * * * 3
        * * * * * * * * 2    * * * * * * * * 2
        * * * * * * * * 1    * * * * * * * * 1
        a b c d e f g h      a b c d e f g h
        */
        @DisplayName("rank7에 위치한 경우 아래로 2칸 또는 1칸 갈 수 있다.")
        @Test
        void moveIfPositionedRank7() {
            Pawn blackPawn = new chess.domain.piece.BlackPawn(Square.of(E, SEVEN));
            Set<Piece> existPieces = Set.of(blackPawn);
            Set<Square> squares = blackPawn.findLegalMoves(existPieces);
            assertThat(squares)
                    .containsExactlyInAnyOrder(
                            Square.of(E, SIX),
                            Square.of(E, FIVE));
        }

        /*
        * * * * * * * * 8    * * * * * * * * 8
        * * * * * * * * 7    * * * * * * * * 7
        * * * * P * * * 6    * * * * P * * * 6
        * * * * * * * * 5    * * * * o * * * 5
        * * * * * * * * 4 -> * * * * * * * * 4
        * * * * * * * * 3    * * * * * * * * 3
        * * * * * * * * 2    * * * * * * * * 2
        * * * * * * * * 1    * * * * * * * * 1
        a b c d e f g h      a b c d e f g h
        */
        @DisplayName("rank7에 위치하지 않는다면 이동은 아래로 1칸만 이동할 수 있다.")
        @Test
        void cannotMoveIfNotPositionedRank7() {
            Pawn blackPawn = new BlackPawn(Square.of(E, SIX));
            Set<Piece> existPieces = Set.of(blackPawn);
            Set<Square> squares = blackPawn.findLegalMoves(existPieces);
            assertThat(squares)
                    .containsExactlyInAnyOrder(Square.of(E, FIVE));
        }

        /*
        * * * * * * * * 8    * * * * * * * * 8
        * * * * * * * * 7    * * * * * * * * 7
        * * * * P * * * 6    * * * * P * * * 6
        * * * * N * * * 5    * * * * x * * * 5
        * * * * * * * * 4 -> * * * * * * * * 4
        * * * * * * * * 3    * * * * * * * * 3
        * * * * * * * * 2    * * * * * * * * 2
        * * * * * * * * 1    * * * * * * * * 1
        a b c d e f g h      a b c d e f g h
        */
        @DisplayName("앞에 기물이 존재하면 이동할 수 없다.")
        @Test
        void cannotMoveIfExistFront() {
            Pawn blackPawn = new BlackPawn(Square.of(E, SIX));
            Set<Piece> existPieces = Set.of(
                    blackPawn,
                    new Knight(Color.BLACK, Square.of(E, FIVE))
            );
            Set<Square> squares = blackPawn.findLegalMoves(existPieces);
            assertThat(squares).isEmpty();
        }

        /*
        * * * * * * * * 8    * * * * * * * * 8
        * * * * * * * * 7    * * * * * * * * 7
        * * * * P * * * 6    * * * * P * * * 6
        * * * p * * * * 5    * * * o o * * * 5
        * * * * * * * * 4 -> * * * * * * * * 4
        * * * * * * * * 3    * * * * * * * * 3
        * * * * * * * * 2    * * * * * * * * 2
        * * * * * * * * 1    * * * * * * * * 1
        a b c d e f g h      a b c d e f g h
        */
        @DisplayName("대각선에 백 기물이 존재하면 이동(공격)할 수 있다.")
        @Test
        void moveIfWhiteExistDiagonal() {
            Pawn blackPawn = new BlackPawn(Square.of(E, SIX));
            Set<Piece> existPieces = Set.of(
                    blackPawn,
                    new WhitePawn(Square.of(D, FIVE))
            );
            Set<Square> squares = blackPawn.findLegalMoves(existPieces);
            assertThat(squares)
                    .containsExactlyInAnyOrder(
                            Square.of(E, FIVE),
                            Square.of(D, FIVE));
        }

        /*
        * * * * * * * * 8    * * * * * * * * 8
        * * * * * * * * 7    * * * * * * * * 7
        * * * * P * * * 6    * * * * P * * * 6
        * * * P * * * * 5    * * * x o * * * 5
        * * * * * * * * 4 -> * * * * * * * * 4
        * * * * * * * * 3    * * * * * * * * 3
        * * * * * * * * 2    * * * * p * * * 2
        * * * * * * * * 1    * * * * * * * * 1
        a b c d e f g h      a b c d e f g h
        */
        @DisplayName("대각선에 흑 기물이 있으면 공격할 수 없다.")
        @Test
        void cannotMoveIfBlackExistDiagonal() {
            Pawn blackPawn = new BlackPawn(Square.of(E, SIX));
            Set<Piece> existPieces = Set.of(
                    blackPawn,
                    new BlackPawn(Square.of(D, FIVE))
            );
            Set<Square> squares = blackPawn.findLegalMoves(existPieces);
            assertThat(squares)
                    .containsExactlyInAnyOrder(Square.of(E, FIVE));
        }

        /*
        * * * * * * * * 8    * * * * * * * * 8
        * * * * P * * * 7    * * * * P * * * 7
        * * * * * p * * 6    * * * * o o * * 6
        * * * * * * * * 5    * * * * o * * * 5
        * * * * * * * * 4 -> * * * * * * * * 4
        * * * * * * * * 3    * * * * * * * * 3
        * * * * * * * * 2    * * * * * * * * 2
        * * * * * * * * 1    * * * * * * * * 1
        a b c d e f g h      a b c d e f g h
        */

        @DisplayName("대각선에 백 기물이 있으면 이동할 수 있다.")
        @Test
        void canMoveIfWhiteExistDiagonal() {
            Pawn blackPawn = new BlackPawn(Square.of(E, SEVEN));
            Set<Piece> existPieces = Set.of(
                    blackPawn,
                    new WhitePawn(Square.of(F, SIX))
            );
            Set<Square> squares = blackPawn.findLegalMoves(existPieces);
            assertThat(squares)
                    .containsExactlyInAnyOrder(
                            Square.of(E, FIVE),
                            Square.of(E, SIX),
                            Square.of(F, SIX));
        }
    }

    @DisplayName("폰이 백인 경우")
    @Nested
    class WhitePawnTest {

        /*
        * * * * * * * * 8    * * * * * * * * 8
        * * * * * * * * 7    * * * * * * * * 7
        * * * * * * * * 6    * * * * * * * * 6
        * * * * * * * * 5    * * * * * * * * 5
        * * * * * * * * 4 -> * * * * o * * * 4
        * * * * * * * * 3    * * * * o * * * 3
        * * * * p * * * 2    * * * * p * * * 2
        * * * * * * * * 1    * * * * * * * * 1
        a b c d e f g h      a b c d e f g h
        */
        @DisplayName("rank2에 위치한 경우 아래로 2칸 또는 1칸 갈 수 있다.")
        @Test
        void moveIfPositionedRank2() {
            Pawn whitePawn = new WhitePawn(Square.of(E, TWO));
            Set<Piece> existPieces = Set.of(whitePawn);
            Set<Square> squares = whitePawn.findLegalMoves(existPieces);
            assertThat(squares)
                    .containsExactlyInAnyOrder(
                            Square.of(E, THREE),
                            Square.of(E, FOUR));
        }

        /*
        * * * * * * * * 8    * * * * * * * * 8
        * * * * * * * * 7    * * * * * * * * 7
        * * * * * * * * 6    * * * * * * * * 6
        * * * * * * * * 5    * * * * * * * * 5
        * * * * * * * * 4 -> * * * * o * * * 4
        * * * * p * * * 3    * * * * p * * * 3
        * * * * * * * * 2    * * * * * * * * 2
        * * * * * * * * 1    * * * * * * * * 1
        a b c d e f g h      a b c d e f g h
        */
        @DisplayName("rank2에 위치하지 않는다면 이동은 아래로 1칸만 이동할 수 있다.")
        @Test
        void cannotMoveIfNotPositionedRank2() {
            Pawn whitePawn = new WhitePawn(Square.of(E, THREE));
            Set<Piece> existPieces = Set.of(whitePawn);
            Set<Square> squares = whitePawn.findLegalMoves(existPieces);
            assertThat(squares)
                    .containsExactlyInAnyOrder(Square.of(E, FOUR));
        }

        /*
        * * * * * * * * 8    * * * * * * * * 8
        * * * * * * * * 7    * * * * * * * * 7
        * * * * * * * * 6    * * * * * * * * 6
        * * * * * * * * 5    * * * * * * * * 5
        * * * * * * * * 4 -> * * * * * * * * 4
        * * * * n * * * 3    * * * * x * * * 3
        * * * * p * * * 2    * * * * p * * * 2
        * * * * * * * * 1    * * * * * * * * 1
        a b c d e f g h      a b c d e f g h
        */
        @DisplayName("앞에 백 기물이 존재하면 이동할 수 없다.")
        @Test
        void cannotMoveIfAllyExistFront() {
            Pawn whitePawn = new WhitePawn(Square.of(E, TWO));
            Set<Piece> existPieces = Set.of(
                    whitePawn,
                    new Knight(Color.WHITE, Square.of(E, THREE))
            );
            Set<Square> squares = whitePawn.findLegalMoves(existPieces);
            assertThat(squares).isEmpty();
        }

        /*
        * * * * * * * * 8    * * * * * * * * 8
        * * * * * * * * 7    * * * * * * * * 7
        * * * * * * * * 6    * * * * * * * * 6
        * * * * * * * * 5    * * * * * * * * 5
        * * * * * * * * 4 -> * * * * * * * * 4
        * * * * N * * * 3    * * * * x * * * 3
        * * * * p * * * 2    * * * * p * * * 2
        * * * * * * * * 1    * * * * * * * * 1
        a b c d e f g h      a b c d e f g h
        */
        @DisplayName("앞에 흑 기물이 존재하면 이동할 수 없다.")
        @Test
        void cannotMoveIfEnemyExistFront() {
            Pawn whitePawn = new WhitePawn(Square.of(E, TWO));
            Set<Piece> existPieces = Set.of(
                    whitePawn,
                    new Knight(Color.BLACK, Square.of(E, THREE))
            );
            Set<Square> squares = whitePawn.findLegalMoves(existPieces);
            assertThat(squares).isEmpty();
        }

        /*
        * * * * * * * * 8    * * * * * * * * 8
        * * * * * * * * 7    * * * * * * * * 7
        * * * * * * * * 6    * * * * * * * * 6
        * * * * * * * * 5    * * * * * * * * 5
        * * * * * * * * 4 -> * * * * o * * * 4
        * * * * * P * * 3    * * * x o o * * 3
        * * * * p * * * 2    * * * * p * * * 2
        * * * * * * * * 1    * * * * * * * * 1
        a b c d e f g h      a b c d e f g h
        */
        @DisplayName("대각선에 흑 기물이 존재하면 이동(공격)할 수 있다.")
        @Test
        void moveIfBlackExistDiagonal() {
            Pawn whitePawn = new WhitePawn(Square.of(E, TWO));
            Set<Piece> existPieces = Set.of(
                    whitePawn,
                    new BlackPawn(Square.of(F, THREE))
            );
            Set<Square> squares = whitePawn.findLegalMoves(existPieces);
            assertThat(squares)
                    .containsExactlyInAnyOrder(
                            Square.of(E, FOUR),
                            Square.of(E, THREE),
                            Square.of(F, THREE)
                    );
        }

        /*
        * * * * * * * * 8    * * * * * * * * 8
        * * * * * * * * 7    * * * * * * * * 7
        * * * * * * * * 6    * * * * * * * * 6
        * * * * * * * * 5    * * * * * * * * 5
        * * * * * * * * 4 -> * * * * o * * * 4
        * * * * * p * * 3    * * * * o x * * 3
        * * * * p * * * 2    * * * * p * * * 2
        * * * * * * * * 1    * * * * * * * * 1
        a b c d e f g h      a b c d e f g h
        */
        @DisplayName("대각선에 백 기물이 있으면 공격할 수 없다.")
        @Test
        void cannotMoveIfWhiteExistDiagonal() {
            Pawn whitePawn = new WhitePawn(Square.of(E, TWO));
            Set<Piece> existPieces = Set.of(
                    whitePawn,
                    new WhitePawn(Square.of(F, THREE))
            );
            Set<Square> squares = whitePawn.findLegalMoves(existPieces);
            assertThat(squares)
                    .containsExactlyInAnyOrder(
                            Square.of(E, FOUR),
                            Square.of(E, THREE)
                    );
        }
    }
}
