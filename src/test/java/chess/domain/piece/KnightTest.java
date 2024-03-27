package chess.domain.piece;

import static chess.domain.attribute.Color.BLACK;
import static chess.domain.attribute.Color.WHITE;
import static chess.domain.attribute.File.C;
import static chess.domain.attribute.File.D;
import static chess.domain.attribute.File.E;
import static chess.domain.attribute.File.F;
import static chess.domain.attribute.File.G;
import static chess.domain.attribute.File.H;
import static chess.domain.attribute.Rank.EIGHT;
import static chess.domain.attribute.Rank.FIVE;
import static chess.domain.attribute.Rank.FOUR;
import static chess.domain.attribute.Rank.SEVEN;
import static chess.domain.attribute.Rank.SIX;
import static chess.domain.attribute.Rank.THREE;
import static chess.domain.attribute.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.attribute.Square;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

    /*
     * * * * * * * * 8    * * * * * * * * 8
     * * * * * * * * 7    * * * * * * * * 7
     * * * * * * * * 6    * * * o * o * * 6
     * * * * * * * * 5    * * o * * * o * 5
     * * * * n * * * 4 -> * * * * n * * * 4
     * * * * * * * * 3    * * o * * * o * 3
     * * * * * * * * 2    * * * o * o * * 2
     * * * * * * * * 1    * * * * * * * * 1
     a b c d e f g h      a b c d e f g h
     */
    @DisplayName("이동 가능한 모든 위치를 반환한다_기물이 존재하지 않는 경우")
    @Test
    void movableSquareAllDirection() {
        Knight knight = new Knight(WHITE, Square.of(E, FOUR));
        Set<Piece> existPieces = Set.of(knight);
        Set<Square> squares = knight.findLegalMoves(existPieces);
        assertThat(squares)
                .containsExactlyInAnyOrder(
                        Square.of(D, SIX),
                        Square.of(F, SIX),
                        Square.of(C, FIVE),
                        Square.of(G, FIVE),
                        Square.of(C, THREE),
                        Square.of(G, THREE),
                        Square.of(D, TWO),
                        Square.of(F, TWO));
    }

    /*
     * * * * * * * * 8    * * * * * * * * 8
     * * * * * * * * 7    * * * * * * * * 7
     * * * * * p * * 6    * * * o * x * * 6
     * * * * * * * * 5    * * o * * * o * 5
     * * * * n * * * 4 -> * * * * n * * * 4
     * * * * * * p * 3    * * o * * * x * 3
     * * * * * * * * 2    * * * o * o * * 2
     * * * * * * * * 1    * * * * * * * * 1
     a b c d e f g h      a b c d e f g h
     */
    @DisplayName("이동 가능한 모든 위치를 반환한다_같은 편의 기물이 존재하는 경우")
    @Test
    void movableSquare() {
        Knight knight = new Knight(WHITE, Square.of(E, FOUR));
        Set<Piece> existPieces = Set.of(
                knight,
                new Pawn(WHITE, Square.of(F, SIX)),
                new Pawn(WHITE, Square.of(G, THREE))
        );
        Set<Square> squares = knight.findLegalMoves(existPieces);
        assertThat(squares)
                .containsExactlyInAnyOrder(
                        Square.of(D, SIX),
                        Square.of(C, FIVE),
                        Square.of(G, FIVE),
                        Square.of(C, THREE),
                        Square.of(D, TWO),
                        Square.of(F, TWO));
    }

    /*
     * * * * * * * * 8    * * * * * * * * 8
     * * * * * * * * 7    * * * * * * * * 7
     * * * p * P * * 6    * * * x * o * * 6
     * * * * * * * * 5    * * o * * * o * 5
     * * * * n * * * 4 -> * * * * n * * * 4
     * * * * * * p * 3    * * o * * * x * 3
     * * * * * * * * 2    * * * o * o * * 2
     * * * * * * * * 1    * * * * * * * * 1
     a b c d e f g h      a b c d e f g h
     */
    @DisplayName("이동 가능한 모든 위치를 반환한다_다른 편의 기물이 존재하는 경우")
    @Test
    void movableSquareEnemyExist() {
        Knight knight = new Knight(WHITE, Square.of(E, FOUR));
        Set<Piece> existPieces = Set.of(
                knight,
                new Pawn(WHITE, Square.of(D, SIX)),
                new Pawn(WHITE, Square.of(G, THREE)),
                new Pawn(BLACK, Square.of(F, SIX))
        );
        Set<Square> squares = knight.findLegalMoves(existPieces);
        assertThat(squares)
                .containsExactlyInAnyOrder(
                        Square.of(F, SIX),
                        Square.of(C, FIVE),
                        Square.of(G, FIVE),
                        Square.of(C, THREE),
                        Square.of(D, TWO),
                        Square.of(F, TWO));
    }

    /*
     * * * * * * * * 8    * * * * * * * * 8
     * * * * * * * * 7    * * * * * * * * 7
     * * * * * * * * 6    * * * o * o * * 6
     * * * p p p * * 5    * * o * * * o * 5
     * * * p n p * * 4 -> * * * * n * * * 4
     * * * p p p * * 3    * * o * * * o * 3
     * * * * * * * * 2    * * * o * o * * 2
     * * * * * * * * 1    * * * * * * * * 1
     a b c d e f g h      a b c d e f g h
     */
    @DisplayName("이동하는 중에 기물이 존재해도 건너뛸 수 있다.")
    @Test
    void movableSquareCanPassOver() {
        Knight knight = new Knight(WHITE, Square.of(E, FOUR));
        Set<Piece> existPieces = Set.of(
                knight,
                new Pawn(WHITE, Square.of(D, FIVE)),
                new Pawn(WHITE, Square.of(E, FIVE)),
                new Pawn(WHITE, Square.of(F, FIVE)),
                new Pawn(WHITE, Square.of(D, FOUR)),
                new Pawn(WHITE, Square.of(F, FOUR)),
                new Pawn(WHITE, Square.of(D, THREE)),
                new Pawn(WHITE, Square.of(E, THREE)),
                new Pawn(WHITE, Square.of(F, THREE))
        );
        Set<Square> squares = knight.findLegalMoves(existPieces);
        assertThat(squares)
                .containsExactlyInAnyOrder(
                        Square.of(D, SIX),
                        Square.of(F, SIX),
                        Square.of(C, FIVE),
                        Square.of(G, FIVE),
                        Square.of(C, THREE),
                        Square.of(G, THREE),
                        Square.of(D, TWO),
                        Square.of(F, TWO));
    }

    /*
     * * * * * * * * 8    * * * * * * N * 8
     * * * * * * * * 7    * * * * o * * * 7
     * * * * * * * * 6    * * * * * o * o 6
     * * * * * * * * 5    * * * * * * * * 5
     * * * * * * * * 4 -> * * * * * * * * 4
     * * * * * * * * 3    * * * * * * * * 3
     * * * * * * * * 2    * * * * * * * * 2
     * * * * * * * * 1    * * * * * * * * 1
     a b c d e f g h      a b c d e f g h
     */
    @DisplayName("이동하는 경로가 칸에 없다면 저장하지 않는다.")
    @Test
    void movableSquareExceptNotSquare() {
        Knight knight = new Knight(BLACK, Square.of(G, EIGHT));
        Set<Piece> existPieces = Set.of(knight);
        Set<Square> squares = knight.findLegalMoves(existPieces);
        assertThat(squares)
                .containsExactlyInAnyOrder(
                        Square.of(H, SIX),
                        Square.of(F, SIX),
                        Square.of(E, SEVEN));
    }
}
