package domain.chessboard;

import domain.coordinate.PositionFactory;
import domain.piece.Color;
import domain.piece.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

public class ChessBoardTest {

    @Test
    @DisplayName("체스판을 생성한다.")
    void givenChessBoard_thenSize64() {
        final int sum = ChessBoard.generate()
                .getChessBoard()
                .stream()
                .map(Rank::getRank)
                .mapToInt(Collection::size)
                .sum();

        assertThat(sum).isEqualTo(64);
    }

    /*
    RNBQKBNR
    PPPPPPPP
    ........
    ........
    ........
    ........
    pppppppp
    rnbqkbnr
    */
    @Test
    @DisplayName("초기 상태에는 양쪽 모두 38점이다.")
    void scoreIsThirtyEight() {
        // Given
        ChessBoard chessBoard = ChessBoard.generate();

        // When
        double blackScore = chessBoard.calculateColorScore(Color.BLACK);
        double whiteScore = chessBoard.calculateColorScore(WHITE);

        // Then
        assertThat(blackScore).isEqualTo(38);
        assertThat(whiteScore).isEqualTo(38);
    }

    /*
    RNBQKBNR
    PPPPPPPP
    ........
    ........
    p.......
    p.......
    pppppppp
    rnbqkbnr
     */
    @Test
    @DisplayName("pawn이 세로로 위치해있으면 0.5점으로 판별된다, 즉, 39점이다. (WHITE)")
    void scoreIsThirtyNine() {
        // Given
        ChessBoard chessBoard = ChessBoard.generate();
        chessBoard.findSquare(PositionFactory.createPosition("a3"))
                .bePiece(new Square(new Pawn(WHITE)));
        chessBoard.findSquare(PositionFactory.createPosition("a4"))
                .bePiece(new Square(new Pawn(WHITE)));

        // When&Then
        assertThat(chessBoard.calculateColorScore(WHITE)).isEqualTo(38.5);
    }

    /*
    RNBQKBNR
    PPPPPPPP
    ........
    ........
    ........
    ........
    pppppppp
    rnbqkbnr
    */
    @Test
    @DisplayName("둘다 King 이 있는 경우")
    void allExistKing() {
        // Given
        ChessBoard chessBoard = ChessBoard.generate();

        // When&Then
        assertThat(chessBoard.isNotTwoKing()).isFalse();
    }

    /*
    RNBQ.BNR
    PPPPPPPP
    ........
    ........
    ........
    ........
    pppppppp
    rnbqkbnr
    */
    @Test
    @DisplayName("black 이 king 이 없는 경우")
    void blackNotExistKing() {
        // Given
        ChessBoard chessBoard = ChessBoard.generate();
        chessBoard.findSquare(PositionFactory.createPosition("e8")).beEmpty();

        // When&Then
        assertThat(chessBoard.isNotTwoKing()).isTrue();
        assertThat(chessBoard.isExistKingThisColor(Color.BLACK)).isFalse();
    }

    /*
    RNBQKBNR
    PPPPPPPP
    ........
    ........
    ........
    ........
    pppppppp
    rnbq.bnr
    */
    @Test
    @DisplayName("White 가 king 이 없는 경우")
    void whiteNotExistKing() {
        // Given
        ChessBoard chessBoard = ChessBoard.generate();
        chessBoard.findSquare(PositionFactory.createPosition("e1")).beEmpty();

        // When&Then
        assertThat(chessBoard.isNotTwoKing()).isTrue();
        assertThat(chessBoard.isExistKingThisColor(Color.WHITE)).isFalse();
    }

}
