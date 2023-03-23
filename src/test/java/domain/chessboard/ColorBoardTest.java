package domain.chessboard;

import domain.coordinate.PositionFactory;
import domain.piece.Color;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.PieceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ColorBoardTest {

    private ChessBoard chessBoard;

    @BeforeEach
    void beforeEach() {
        chessBoard = ChessBoard.generate();
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
        ColorBoard blackBoard = new ColorBoard(Color.BLACK, chessBoard);
        ColorBoard whiteBoard = new ColorBoard(Color.WHITE, chessBoard);

        // When
        double blackScore = blackBoard.calculateScore();
        double whiteScore = whiteBoard.calculateScore();

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
        chessBoard.findSquare(PositionFactory.createPosition("a3"))
                .bePiece(new Square(new Pawn(Color.WHITE)));
        chessBoard.findSquare(PositionFactory.createPosition("a4"))
                .bePiece(new Square(new Pawn(Color.WHITE)));

        // When
        ColorBoard whiteBoard = new ColorBoard(Color.WHITE, chessBoard);

        // Then
        assertThat(whiteBoard.calculateScore()).isEqualTo(38.5);
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
        ColorBoard blackBoard = new ColorBoard(Color.BLACK, chessBoard);
        ColorBoard whiteBoard = new ColorBoard(Color.WHITE, chessBoard);

        // When&Then
        assertThat(blackBoard.countKing()).isEqualTo(1);
        assertThat(whiteBoard.countKing()).isEqualTo(1);
        assertThat(blackBoard.isExistKing()).isTrue();
        assertThat(whiteBoard.isExistKing()).isTrue();
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
        chessBoard.findSquare(PositionFactory.createPosition("e8")).beEmpty();
        ColorBoard blackBoard = new ColorBoard(Color.BLACK, chessBoard);
        ColorBoard whiteBoard = new ColorBoard(Color.WHITE, chessBoard);

        // When&Then
        assertThat(blackBoard.countKing()).isEqualTo(0);
        assertThat(whiteBoard.countKing()).isEqualTo(1);
        assertThat(blackBoard.isExistKing()).isFalse();
        assertThat(whiteBoard.isExistKing()).isTrue();
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
        chessBoard.findSquare(PositionFactory.createPosition("e1")).beEmpty();
        ColorBoard blackBoard = new ColorBoard(Color.BLACK, chessBoard);
        ColorBoard whiteBoard = new ColorBoard(Color.WHITE, chessBoard);

        // When&Then
        assertThat(blackBoard.countKing()).isEqualTo(1);
        assertThat(whiteBoard.countKing()).isEqualTo(0);
        assertThat(blackBoard.isExistKing()).isTrue();
        assertThat(whiteBoard.isExistKing()).isFalse();
    }

}
