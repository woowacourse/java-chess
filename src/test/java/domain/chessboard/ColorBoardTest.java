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
        // Given (상황)
        ColorBoard blackBoard = new ColorBoard(Color.BLACK, chessBoard);
        ColorBoard whiteBoard = new ColorBoard(Color.WHITE, chessBoard);

        // When (비즈니스 로직)
        double blackScore = blackBoard.calculateScore();
        double whiteScore = whiteBoard.calculateScore();

        // Then (검증)
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
        // Given (상황)
        chessBoard.findSquare(PositionFactory.createPosition("a3"))
                .bePiece(new Square(new Pawn(Color.WHITE)));
        chessBoard.findSquare(PositionFactory.createPosition("a4"))
                .bePiece(new Square(new Pawn(Color.WHITE)));

        // When (비즈니스 로직)
        ColorBoard whiteBoard = new ColorBoard(Color.WHITE, chessBoard);

        // Then (검증)
        assertThat(whiteBoard.calculateScore()).isEqualTo(38.5);
    }

}
