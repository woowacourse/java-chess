package chess.domain.piece;

import static chess.domain.attribute.File.D;
import static chess.domain.attribute.File.E;
import static chess.domain.attribute.File.F;
import static chess.domain.attribute.File.G;
import static chess.domain.attribute.Rank.EIGHT;
import static chess.domain.attribute.Rank.ONE;
import static chess.domain.attribute.Rank.SEVEN;
import static chess.domain.attribute.Rank.THREE;
import static chess.domain.attribute.Rank.TWO;
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
                new WhitePawn(Square.of(F, THREE)),
                new WhitePawn(Square.of(F, TWO)),
                new WhitePawn(Square.of(E, ONE))
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
                new BlackPawn(Square.of(D, THREE)),
                new BlackPawn(Square.of(E, THREE)),
                new WhitePawn(Square.of(F, TWO))
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

    /*
     * * * * * K * * 8    * * * * o K o * 8
     * * * * * * * * 7    * * * * o o o * 7
     * * * * * * * * 6    * * * * * * * * 6
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
        King king = new King(Color.BLACK, Square.of(F, EIGHT));
        Set<Piece> existPieces = Set.of(king);
        Set<Square> squares = king.findLegalMoves(existPieces);
        assertThat(squares)
                .containsExactlyInAnyOrder(
                        Square.of(E, EIGHT),
                        Square.of(E, SEVEN),
                        Square.of(F, SEVEN),
                        Square.of(G, EIGHT),
                        Square.of(G, SEVEN));
    }
}
