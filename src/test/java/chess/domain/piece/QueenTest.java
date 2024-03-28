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

class QueenTest {

    /*
     * * * * * * * * 8    o * * * o * * * 8
     * * * * * * * * 7    * o * * o * * o 7
     * * * * * * * * 6    * * o * o * o * 6
     * * * * * * * * 5    * * * o o o * * 5
     * * * * q * * * 4 -> o o o o q o o o 4
     * * * * * * * * 3    * * * o o o * * 3
     * * * * * * * * 2    * * o * o * o * 2
     * * * * * * * * 1    * o * * o * * o 1
     a b c d e f g h      a b c d e f g h
     */
    @DisplayName("이동 가능한 모든 위치를 반환한다_기물이 존재하지 않는 경우")
    @Test
    void movableSquareAllDirection() {
        Queen queen = new Queen(Color.WHITE, Square.of(E, FOUR));
        Set<Piece> existPieces = Set.of(queen);
        Set<Square> squares = queen.findLegalMoves(existPieces);
        assertThat(squares)
                .containsExactlyInAnyOrder(
                        Square.of(E, ONE), Square.of(E, TWO),
                        Square.of(E, THREE), Square.of(E, FIVE),
                        Square.of(E, SIX), Square.of(E, SEVEN),
                        Square.of(E, EIGHT), Square.of(A, FOUR),
                        Square.of(B, FOUR), Square.of(C, FOUR),
                        Square.of(D, FOUR), Square.of(F, FOUR),
                        Square.of(G, FOUR), Square.of(H, FOUR),

                        Square.of(A, EIGHT), Square.of(B, ONE),
                        Square.of(B, SEVEN), Square.of(C, TWO),
                        Square.of(C, SIX), Square.of(D, THREE),
                        Square.of(D, FIVE), Square.of(F, FIVE),
                        Square.of(F, THREE), Square.of(G, SIX),
                        Square.of(G, TWO), Square.of(H, SEVEN),
                        Square.of(H, ONE));
    }

    /*
     * * * * * * * * 8    o * * * x * * * 8
     * * * * * * * * 7    * o * * x * * x 7
     * * * * p * p * 6    * * o * x * x * 6
     * * * * * * * * 5    * * * o o o * * 5
     p * * * q * * * 4 -> x o o o q o o o 4
     * * * p * * * * 3    * * * x o o * * 3
     * * * * * * p * 2    * * x * o * x * 2
     * * * * * * * * 1    * x * * o * * x 1
     a b c d e f g h      a b c d e f g h
     */
    @DisplayName("이동 가능한 모든 위치를 반환한다_같은 편 기물이 존재하는 경우")
    @Test
    void movableSquareExistAlly() {
        Queen queen = new Queen(Color.WHITE, Square.of(E, FOUR));
        Set<Piece> existPieces = Set.of(
                queen,
                new WhitePawn(Square.of(E, SIX)),
                new WhitePawn(Square.of(G, SIX)),
                new WhitePawn(Square.of(A, FOUR)),
                new WhitePawn(Square.of(D, THREE)),
                new WhitePawn(Square.of(G, TWO))
        );
        Set<Square> squares = queen.findLegalMoves(existPieces);
        assertThat(squares)
                .containsExactlyInAnyOrder(
                        Square.of(E, ONE), Square.of(E, TWO),
                        Square.of(E, THREE), Square.of(E, FIVE),
                        Square.of(B, FOUR), Square.of(C, FOUR),
                        Square.of(D, FOUR), Square.of(F, FOUR),
                        Square.of(G, FOUR), Square.of(H, FOUR),

                        Square.of(A, EIGHT), Square.of(B, SEVEN),
                        Square.of(C, SIX), Square.of(D, FIVE),
                        Square.of(F, FIVE), Square.of(F, THREE));
    }

    /*
     * * * * * * * * 8    o * * * x * * * 8
     * * * * * * * * 7    * o * * x * * x 7
     * * * * P * * * 6    * * o * o * x * 6
     * * * * * p * * 5    * * * o o x * * 5
     P * * * q * * * 4 -> o o o o q o o o 4
     * * * * * * * * 3    * * * o o o * * 3
     * * * * P * * * 2    * * o * o * o * 2
     * * * * * * * * 1    * o * * x * * o 1
     a b c d e f g h      a b c d e f g h
     */
    @DisplayName("이동 가능한 모든 위치를 반환한다_적의 기물이 존재하는 경우")
    @Test
    void movableSquareExistEnemy() {
        Queen queen = new Queen(Color.WHITE, Square.of(E, FOUR));
        Set<Piece> existPieces = Set.of(
                queen,
                new BlackPawn(Square.of(E, SIX)),
                new BlackPawn(Square.of(E, TWO)),
                new BlackPawn(Square.of(A, FOUR)),
                new WhitePawn(Square.of(F, FIVE))
        );
        Set<Square> squares = queen.findLegalMoves(existPieces);
        assertThat(squares)
                .containsExactlyInAnyOrder(
                        Square.of(E, TWO), Square.of(E, THREE),
                        Square.of(E, FIVE), Square.of(E, SIX),
                        Square.of(A, FOUR), Square.of(B, FOUR),
                        Square.of(C, FOUR), Square.of(D, FOUR),
                        Square.of(F, FOUR), Square.of(G, FOUR),
                        Square.of(H, FOUR),

                        Square.of(A, EIGHT), Square.of(B, ONE),
                        Square.of(B, SEVEN), Square.of(C, TWO),
                        Square.of(C, SIX), Square.of(D, THREE),
                        Square.of(D, FIVE), Square.of(F, THREE),
                        Square.of(G, TWO), Square.of(H, ONE));
    }
}
