package domain.board;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import domain.PieceNameConverter;
import domain.piece.Piece;

class ChessBoardTest {

    @Test
    @DisplayName("체스판을 생성한다.")
    void createChessBoard() {
        assertDoesNotThrow(ChessBoard::new);
    }

    @Test
    @DisplayName("기물을 음직인다.")
    void moveTest() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.initialize();
        Square currentSquare = new Square(File.A, Rank.TWO);
        Square targetSquare = new Square(File.A, Rank.FOUR);
        Piece piece = chessBoard.getBoard().get(currentSquare);
        chessBoard.move(currentSquare, targetSquare);
        assertThat(chessBoard.getBoard().get(targetSquare)).isEqualTo(piece);
    }

    @Nested
    @DisplayName("기물이 정상적으로 놓여지는지 확인한다.")
    class InitializeTest {
        ChessBoard chessBoard;

        @BeforeEach
        void setUp() {
            chessBoard = new ChessBoard();
            chessBoard.initialize();
        }

        @Test
        @DisplayName("폰이 제대로 놓여있는지 확인한다.")
        void pawnTest() {
            Piece piece = chessBoard.getBoard().get(new Square(File.H, Rank.TWO));

            assertThat(PieceNameConverter.convert(piece)).isEqualTo("p");
            assertThat(piece.isWhite()).isTrue();
        }

        @Test
        @DisplayName("나이트가 제대로 놓여있는지 확인한다.")
        void knightTest() {
            Piece piece = chessBoard.getBoard().get(new Square(File.B, Rank.ONE));

            assertThat(PieceNameConverter.convert(piece)).isEqualTo("n");
            assertThat(piece.isWhite()).isTrue();
        }

        @Test
        @DisplayName("룩이 제대로 놓여있는지 확인한다.")
        void rookTest() {
            Piece piece = chessBoard.getBoard().get(new Square(File.A, Rank.ONE));

            assertThat(PieceNameConverter.convert(piece)).isEqualTo("r");
            assertThat(piece.isWhite()).isTrue();
        }

        @Test
        @DisplayName("비숍이 제대로 놓여있는지 확인한다.")
        void bishopTest() {
            Piece piece = chessBoard.getBoard().get(new Square(File.C, Rank.ONE));

            assertThat(PieceNameConverter.convert(piece)).isEqualTo("b");
            assertThat(piece.isWhite()).isTrue();
        }

        @Test
        @DisplayName("퀸이 제대로 놓여있는지 확인한다.")
        void queenTest() {
            Piece piece = chessBoard.getBoard().get(new Square(File.D, Rank.ONE));

            assertThat(PieceNameConverter.convert(piece)).isEqualTo("q");
            assertThat(piece.isWhite()).isTrue();
        }

        @Test
        @DisplayName("킹이 제대로 놓여있는지 확인한다.")
        void kingTest() {
            Piece piece = chessBoard.getBoard().get(new Square(File.E, Rank.ONE));

            assertThat(PieceNameConverter.convert(piece)).isEqualTo("k");
            assertThat(piece.isWhite()).isTrue();
        }
    }

}
