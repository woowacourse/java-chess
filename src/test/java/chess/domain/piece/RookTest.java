package chess.domain.piece;

import static chess.domain.attribute.File.A;
import static chess.domain.attribute.File.B;
import static chess.domain.attribute.File.C;
import static chess.domain.attribute.File.D;
import static chess.domain.attribute.File.E;
import static chess.domain.attribute.File.F;
import static chess.domain.attribute.File.G;
import static chess.domain.attribute.File.H;
import static chess.domain.attribute.Rank.EIGHT;
import static chess.domain.attribute.Rank.FIVE;
import static chess.domain.attribute.Rank.FOUR;
import static chess.domain.attribute.Rank.ONE;
import static chess.domain.attribute.Rank.SEVEN;
import static chess.domain.attribute.Rank.SIX;
import static chess.domain.attribute.Rank.THREE;
import static chess.domain.attribute.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    /*
     * * * * * * * * 8    * * o * * * * * 8
     * * * * * * * * 7    * * o * * * * * 7
     * * * * * * * * 6    * * o * * * * * 6
     * * * * * * * * 5    * * o * * * * * 5
     * * r * * * * * 4 -> o o r o o o o o 4
     * * * * * * * * 3    * * o * * * * * 3
     * * * * * * * * 2    * * o * * * * * 2
     * * * * * * * * 1    * * o * * * * * 1
     a b c d e f g h      a b c d e f g h
     */
    @DisplayName("이동 가능한 모든 위치를 반환한다_기물이 존재하지 않는 경우")
    @Test
    void movableSquareAllDirection() {
        Rook rook = new Rook(Color.WHITE, Square.of(C, FOUR));
        Set<Piece> existPieces = Set.of(rook);
        Set<Square> squares = rook.findLegalMoves(existPieces);
        assertThat(squares)
                .containsExactlyInAnyOrder(
                        Square.of(C, ONE),
                        Square.of(C, TWO),
                        Square.of(C, THREE),
                        Square.of(C, FIVE),
                        Square.of(C, SIX),
                        Square.of(C, SEVEN),
                        Square.of(C, EIGHT),
                        Square.of(A, FOUR),
                        Square.of(B, FOUR),
                        Square.of(D, FOUR),
                        Square.of(E, FOUR),
                        Square.of(F, FOUR),
                        Square.of(G, FOUR),
                        Square.of(H, FOUR));
    }

    /*
     * * * * * * * * 8    * * x * * * * * 8
     * * * * * * * * 7    * * x * * * * * 7
     * * * * * * * * 6    * * x * * * * * 6
     * * p * * * * * 5    * * x * * * * * 5
     * * r * * * p * 4 -> o o r o o o x x 4
     * * * * * * * * 3    * * o * * * * * 3
     * * * * * * * * 2    * * o * * * * * 2
     * * * * * * * * 1    * * o * * * * * 1
     a b c d e f g h      a b c d e f g h
     */
    @DisplayName("이동 가능한 모든 위치를 반환한다_같은 편 기물이 존재하는 경우")
    @Test
    void movableSquareExistAllyPieces() {
        Rook rook = new Rook(Color.WHITE, Square.of(C, FOUR));
        Set<Piece> existPieces = Set.of(
                rook,
                new WhitePawn(Square.of(C, FIVE)),
                new WhitePawn(Square.of(G, FOUR))
        );
        Set<Square> squares = rook.findLegalMoves(existPieces);
        assertThat(squares)
                .containsExactlyInAnyOrder(
                        Square.of(C, ONE),
                        Square.of(C, TWO),
                        Square.of(C, THREE),
                        Square.of(A, FOUR),
                        Square.of(B, FOUR),
                        Square.of(D, FOUR),
                        Square.of(E, FOUR),
                        Square.of(F, FOUR));
    }

    /*
     * * * * * * * * 8    * * x * * * * * 8
     * * * * * * * * 7    * * x * * * * * 7
     * * * * p * * * 6    * * x * * * * * 6
     * * P * * * * * 5    * * o * * * * * 5
     * * r * * p * * 4 -> o o r o o x x x 4
     * * * * * * * * 3    * * o * * * * * 3
     * * * * * * * * 2    * * o * * * * * 2
     * * P * * * * * 1    * * o * * * * * 1
     a b c d e f g h      a b c d e f g h
     */
    @DisplayName("이동 가능한 모든 위치를 반환한다_반대 편 기물이 존재하는 경우")
    @Test
    void movableSquareExistEnemyPieces() {
        Rook rook = new Rook(Color.WHITE, Square.of(C, FOUR));
        Set<Piece> existPieces = Set.of(
                rook,
                new WhitePawn(Square.of(F, FOUR)),
                new WhitePawn(Square.of(E, SIX)),
                new BlackPawn(Square.of(C, FIVE)),
                new BlackPawn(Square.of(C, ONE))
        );
        Set<Square> squares = rook.findLegalMoves(existPieces);
        assertThat(squares)
                .containsExactlyInAnyOrder(
                        Square.of(C, ONE),
                        Square.of(C, TWO),
                        Square.of(C, THREE),
                        Square.of(C, FIVE),
                        Square.of(A, FOUR),
                        Square.of(B, FOUR),
                        Square.of(D, FOUR),
                        Square.of(E, FOUR));
    }
}
