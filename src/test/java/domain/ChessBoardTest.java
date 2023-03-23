package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import domain.piece.Rook;
import domain.piece.Pawn;
import domain.piece.Piece;

class ChessBoardTest {

    @Test
    @DisplayName("폰은 적이 없을 때 대각선으로 갈 수 없다.")
    void pawn_validate() {
        ChessBoard chessBoard = new ChessBoard();
        Square initPawnPosition = Square.of(3, 2);
        Square destination = Square.of(4, 3);

        assertThatThrownBy(() -> chessBoard.move(initPawnPosition, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("대각선으로 갈 수 없습니다.");
    }

    @Test
    @DisplayName("해당 위치에 있는 기물을 찾는다.")
    void findPiece() {
        ChessBoard chessBoard = new ChessBoard();
        Piece piece = chessBoard.find(Square.of(1, 1));

        assertThat(piece).isInstanceOf(Rook.class);
    }

    // TODO : a2로 수정해서 전체 테스트하면 실패하는 이유는??
    @Test
    @DisplayName("폰을 움직인다.")
    void movePawn() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.move(Square.of("b2"), Square.of("b4"));

        assertThat(chessBoard.find(Square.of("b4"))).isInstanceOf(Pawn.class);
        assertThat(chessBoard.find(Square.of("b2"))).isNull();
    }

    @Test
    @DisplayName("폰을 두번째 턴에 두칸 움직일 수 없다")
    void invalidMovePawn() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.move(Square.of("a2"), Square.of("a4"));

        assertThatThrownBy(() -> chessBoard.move(Square.of("a4"), Square.of("a6")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("기물이 이동하는 경로에 기물이 존재하는 경우 움직일 수 없다.")
    @CsvSource(value = {"a1,a4", "b1,d2", "c1,e3", "d1,d4", "e1,f2"})
    void InvalidMovePiece(String src, String dest) {
        ChessBoard chessBoard = new ChessBoard();

        assertThatThrownBy(() -> chessBoard.move(Square.of(src), Square.of(dest)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("기물에 막혀 있어 갈 수 없습니다!");
    }
}
