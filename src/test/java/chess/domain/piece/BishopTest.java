package chess.domain.piece;

import static chess.domain.attribute.Color.WHITE;
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

import chess.domain.attribute.Square;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    /*
     * * * * * * * * 8    o * * * * * * * 8
     * * * * * * * * 7    * o * * * * * o 7
     * * * * * * * * 6    * * o * * * o * 6
     * * * * * * * * 5    * * * o * o * * 5
     * * * * b * * * 4 -> * * * * b * * * 4
     * * * * * * * * 3    * * * o * o * * 3
     * * * * * * * * 2    * * o * * * o * 2
     * * * * * * * * 1    * o * * * * * o 1
     a b c d e f g h      a b c d e f g h
     */
    @DisplayName("이동 가능한 모든 위치를 반환한다_기물이 존재하지 않는 경우")
    @Test
    void movableSquareAllDirection() {
        Bishop bishop = new Bishop(WHITE, Square.of(E, FOUR));
        Set<Piece> existPieces = Set.of(bishop);
        Set<Square> squares = bishop.findLegalMoves(existPieces);
        assertThat(squares)
                .containsExactlyInAnyOrder(
                        Square.of(B, ONE),
                        Square.of(C, TWO),
                        Square.of(D, THREE),
                        Square.of(F, FIVE),
                        Square.of(G, SIX),
                        Square.of(H, SEVEN),
                        Square.of(A, EIGHT),
                        Square.of(B, SEVEN),
                        Square.of(C, SIX),
                        Square.of(D, FIVE),
                        Square.of(F, THREE),
                        Square.of(G, TWO),
                        Square.of(H, ONE));
    }

    /*
     * * * * * * * * 8    o * * * * * * * 8
     * * * * * * * * 7    * o * * * * * x 7
     * * * * * * p * 6    * * o * * * x * 6
     * * * * * * * * 5    * * * o * o * * 5
     * * * * b * * * 4 -> * * * * b * * * 4
     * * * * * * * * 3    * * * o * o * * 3
     * * * * * * * * 2    * * o * * * o * 2
     * * * * * * * * 1    * o * * * * * o 1
     a b c d e f g h      a b c d e f g h
     */
    @DisplayName("이동 가능한 모든 위치를 반환한다_같은 색 기물이 존재하는 경우")
    @Test
    void movableSquareExistAlly() {
        Bishop bishop = new Bishop(WHITE, Square.of(E, FOUR));
        Set<Piece> existPieces = Set.of(
                bishop,
                new WhitePawn(Square.of(G, SIX))
        );
        Set<Square> squares = bishop.findLegalMoves(existPieces);
        assertThat(squares)
                .containsExactlyInAnyOrder(
                        Square.of(B, ONE),
                        Square.of(C, TWO),
                        Square.of(D, THREE),
                        Square.of(F, FIVE),
                        Square.of(A, EIGHT),
                        Square.of(B, SEVEN),
                        Square.of(C, SIX),
                        Square.of(D, FIVE),
                        Square.of(F, THREE),
                        Square.of(G, TWO),
                        Square.of(H, ONE));
    }

    /*
     * * * * * * * * 8    o * * * * * * * 8
     * * * * * * * * 7    * o * * * * * x 7
     * * * * * * P * 6    * * o * * * o * 6
     * * * * * * * * 5    * * * o * o * * 5
     * * * * b * * * 4 -> * * * * b * * * 4
     * * * * * * * * 3    * * * o * o * * 3
     * * * * * * * * 2    * * o * * * o * 2
     * * * * * * * * 1    * o * * * * * o 1
     a b c d e f g h      a b c d e f g h
     */
    @DisplayName("이동 가능한 모든 위치를 반환한다_반대편 기물이 존재하는 경우")
    @Test
    void movableSquareExistEnemy() {
        Bishop bishop = new Bishop(WHITE, Square.of(E, FOUR));
        Set<Piece> existPieces = Set.of(
                bishop,
                new BlackPawn(Square.of(G, SIX))
        );
        Set<Square> squares = bishop.findLegalMoves(existPieces);
        assertThat(squares)
                .containsExactlyInAnyOrder(
                        Square.of(B, ONE),
                        Square.of(C, TWO),
                        Square.of(D, THREE),
                        Square.of(F, FIVE),
                        Square.of(G, SIX),
                        Square.of(A, EIGHT),
                        Square.of(B, SEVEN),
                        Square.of(C, SIX),
                        Square.of(D, FIVE),
                        Square.of(F, THREE),
                        Square.of(G, TWO),
                        Square.of(H, ONE));
    }
}
