package domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import domain.piece.Camp;
import domain.piece.Piece;

class ChessBoardTest {

    @Test
    @DisplayName("체스판을 생성한다.")
    void createChessBoard() {
        assertDoesNotThrow(ChessBoard::new);
    }


    @Test
    @DisplayName("좌표를 입력받아 해당 square를 반환한다.")
    void toSquare() {
        ChessBoard chessBoard = new ChessBoard();

        Square square = chessBoard.toSquare(1, 1);

        assertThat(square).isEqualTo(Square.of(File.B, Rank.TWO));
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
            Piece piece = chessBoard.getBoard().get(Square.of(File.H, Rank.TWO));

            assertThat(piece.isPawn()).isTrue();
            assertThat(piece.isWhite()).isTrue();
        }

        @Test
        @DisplayName("나이트가 제대로 놓여있는지 확인한다.")
        void knightTest() {
            Piece piece = chessBoard.getBoard().get(Square.of(File.B, Rank.ONE));

            assertThat(piece.isKnight()).isTrue();
            assertThat(piece.isWhite()).isTrue();
        }

        @Test
        @DisplayName("룩이 제대로 놓여있는지 확인한다.")
        void rookTest() {
            Piece piece = chessBoard.getBoard().get(Square.of(File.A, Rank.ONE));

            assertThat(piece.isRook()).isTrue();
            assertThat(piece.isWhite()).isTrue();
        }

        @Test
        @DisplayName("비숍이 제대로 놓여있는지 확인한다.")
        void bishopTest() {
            Piece piece = chessBoard.getBoard().get(Square.of(File.C, Rank.ONE));

            assertThat(piece.isBishop()).isTrue();
            assertThat(piece.isWhite()).isTrue();
        }

        @Test
        @DisplayName("퀸이 제대로 놓여있는지 확인한다.")
        void queenTest() {
            Piece piece = chessBoard.getBoard().get(Square.of(File.D, Rank.ONE));

            assertThat(piece.isQueen()).isTrue();
            assertThat(piece.isWhite()).isTrue();
        }

        @Test
        @DisplayName("킹이 제대로 놓여있는지 확인한다.")
        void kingTest() {
            Piece piece = chessBoard.getBoard().get(Square.of(File.E, Rank.ONE));

            assertThat(piece.isKing()).isTrue();
            assertThat(piece.isWhite()).isTrue();
        }
    }

    @Test
    @DisplayName("기물을 음직인다.")
    void moveTest() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.initialize();
        Square currentSquare = Square.of(File.A, Rank.TWO);
        Square targetSquare = Square.of(File.A, Rank.FOUR);
        Piece piece = chessBoard.getBoard().get(currentSquare);
        chessBoard.move(currentSquare, targetSquare);
        assertThat(chessBoard.getBoard().get(targetSquare)).isEqualTo(piece);
    }

    @Test
    @DisplayName("board에 인자로 받은 캠프의 킹이 없으면 true를 반환한다.")
    void isCapturedKing() {
        ChessBoard chessBoard = new ChessBoard();

        boolean result = chessBoard.isCapturedKing(Camp.WHITE);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("board에 인자로 받은 캠프의 킹이 없으면 true를 반환한다.")
    void isCapturedKingFalse() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.initialize();

        boolean result = chessBoard.isCapturedKing(Camp.WHITE);

        assertThat(result).isFalse();
    }
}
