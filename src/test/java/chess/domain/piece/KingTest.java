package chess.domain.piece;

import static chess.domain.attribute.File.*;
import static chess.domain.attribute.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {

    /*
     * * * * * * * * 8    * * * * * * * * 8
     * * * * * * * * 7    * * * * * * * * 7
     * * * * * * * * 6    * * * * * * * * 6
     * * * * * * * * 5    * * * * * * * * 5
     * * * * * * * * 4 -> * * * * * * * * 4
     * * * * * * * * 3    * * * o o o * * 3
     * * * * k * * * 2    * * * o k o * * 2
     * * * * * * * * 1    * * * o o o * * 1
     a b c d e f g h      a b c d e f g h
     */
    @DisplayName("이동 가능한 모든 위치를 반환한다_기물이 존재하지 않는 경우")
    @Test
    void movableSquareAllDirection() {
        King king = new King(Color.WHITE, Square.of(E, TWO));
        Set<Piece> existPieces = Set.of(king);
        Set<Square> squares = king.findLegalMoves(existPieces);
        assertThat(squares)
                .containsExactlyInAnyOrder(
                        Square.of(D, THREE),
                        Square.of(E, THREE),
                        Square.of(F, THREE),
                        Square.of(D, TWO),
                        Square.of(F, TWO),
                        Square.of(D, ONE),
                        Square.of(E, ONE),
                        Square.of(F, ONE));
    }

    /*
     * * * * * * * * 8    * * * * * * * * 8
     * * * * * * * * 7    * * * * * * * * 7
     * * * * * * * * 6    * * * * * * * * 6
     * * * * * * * * 5    * * * * * * * * 5
     * * * * * * * * 4 -> * * * * * * * * 4
     * * * * * p * * 3    * * * o o x * * 3
     * * * * k p * * 2    * * * o k x * * 2
     * * * * p * * * 1    * * * o x o * * 1
     a b c d e f g h      a b c d e f g h
     */
    @DisplayName("이동 가능한 모든 위치를 반환한다_같은 색 기물이 존재하는 경우")
    @Test
    void movableSquareAllyExist() {
        King king = new King(Color.WHITE, Square.of(E, TWO));
        Set<Piece> existPieces = Set.of(
                king,
                new Pawn(Color.WHITE, Square.of(F, THREE)),
                new Pawn(Color.WHITE, Square.of(F, TWO)),
                new Pawn(Color.WHITE, Square.of(E, ONE))
        );
        Set<Square> squares = king.findLegalMoves(existPieces);
        assertThat(squares)
                .containsExactlyInAnyOrder(
                        Square.of(D, THREE),
                        Square.of(E, THREE),
                        Square.of(D, TWO),
                        Square.of(D, ONE),
                        Square.of(F, ONE));
    }

    /*
     * * * * * * * * 8    * * * * * * * * 8
     * * * * * * * * 7    * * * * * * * * 7
     * * * * * * * * 6    * * * * * * * * 6
     * * * * * * * * 5    * * * * * * * * 5
     * * * * * * * * 4 -> * * * * * * * * 4
     * * * P P o * * 3    * * * o o o * * 3
     * * * o k p * * 2    * * * o k x * * 2
     * * * o o o * * 1    * * * o o o * * 1
     a b c d e f g h      a b c d e f g h
     */
    @DisplayName("이동 가능한 모든 위치를 반환한다_다른 색 기물이 존재하는 경우")
    @Test
    void movableSquareEnemyExist() {
        King king = new King(Color.WHITE, Square.of(E, TWO));
        Set<Piece> existPieces = Set.of(
                king,
                new Pawn(Color.BLACK, Square.of(D, THREE)),
                new Pawn(Color.BLACK, Square.of(E, THREE)),
                new Pawn(Color.WHITE, Square.of(F, TWO))
        );
        Set<Square> squares = king.findLegalMoves(existPieces);
        assertThat(squares)
                .containsExactlyInAnyOrder(
                        Square.of(D, THREE),
                        Square.of(E, THREE),
                        Square.of(F, THREE),
                        Square.of(D, TWO),
                        Square.of(D, ONE),
                        Square.of(E, ONE),
                        Square.of(F, ONE));
    }
}
