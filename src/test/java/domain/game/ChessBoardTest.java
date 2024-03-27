package domain.game;

import static fixture.PositionFixture.b1;
import static fixture.PositionFixture.b2;
import static fixture.PositionFixture.b3;
import static fixture.PositionFixture.b4;
import static fixture.PositionFixture.b5;
import static fixture.PositionFixture.b7;
import static fixture.PositionFixture.c3;
import static fixture.PositionFixture.c4;
import static fixture.PositionFixture.c5;
import static fixture.PositionFixture.c7;
import static fixture.PositionFixture.d1;
import static fixture.PositionFixture.d2;
import static fixture.PositionFixture.d3;
import static fixture.PositionFixture.d5;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.piecerole.Knight;
import domain.piece.piecerole.WhitePawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardTest {

    @DisplayName("(b,2)에 위치한 piece를 (b,3)로 이동한다.")
    @Test
    void movePieceToTarget() {
        ChessBoard chessBoard = ChessBoardGenerator.generate();

        chessBoard.move(b2(), b3());

        Piece piece = chessBoard.findPieceByPosition(b3());
        assertThat(piece).isEqualTo(new Piece(WhitePawn.create(), Color.WHITE));
    }

    @DisplayName("source에 piece가 없다면 에러를 반환한다.")
    @Test
    void movePieceIfSourceHasNotPiece() {
        ChessBoard chessBoard = ChessBoardGenerator.generate();

        assertThatThrownBy(() -> chessBoard.move(c3(), c4()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("옮기고자 하는 위치에 같은 진영의 Piece가 있다면 에러를 반환한다.")
    @Test
    void hasSameColorPiece() {
        ChessBoard chessBoard = ChessBoardGenerator.generate();
        chessBoard.move(d2(), d3());

        assertThatThrownBy(() -> chessBoard.move(d1(), d3()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("같은 위치로의 이동이라면 에러를 반환한다.")
    @Test
    void moveToSameSquare() {
        ChessBoard chessBoard = ChessBoardGenerator.generate();

        assertThatThrownBy(() -> chessBoard.move(b1(), b1()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("앞에 다른 진영의 기물이 있는 경우 폰이 이동하지 못한다.")
    @Test
    void movePawnWhenFrontSquareHasOtherPiece() {
        ChessBoard chessBoard = ChessBoardGenerator.generate();
        chessBoard.move(b2(), b4());
        chessBoard.move(b7(), b5());

        assertThatThrownBy(() -> chessBoard.move(b4(), b5()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("대각선에 다른 진영의 기물이 있는 경우 폰이 이동할 수 있다.")
    @Test
    void movePawnWhenDiagonalSquareHasOtherPiece() {
        ChessBoard chessBoard = ChessBoardGenerator.generate();
        chessBoard.move(b2(), b4());
        chessBoard.move(c7(), c5());
        chessBoard.move(b4(), c5());

        Piece piece = chessBoard.findPieceByPosition(c5());
        assertThat(piece).isEqualTo(new Piece(WhitePawn.create(), Color.WHITE));
    }

    @DisplayName("나이트를 제외한 기물은 이동하는 경로에 기물이 있으면 이동하지 못한다.")
    @Test
    void isOverlappedPath() {
        ChessBoard chessBoard = ChessBoardGenerator.generate();
        chessBoard.move(d2(), d3());

        assertThatThrownBy(() -> chessBoard.move(d1(), d5()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("나이트는 이동하는 경로에 기물이 있어도 이동할 수 있다.")
    @Test
    void knightCanJump() {
        ChessBoard chessBoard = ChessBoardGenerator.generate();
        chessBoard.move(b2(), b3());
        chessBoard.move(b7(), b5());
        chessBoard.move(b1(), c3());

        assertThat(chessBoard.findPieceByPosition(c3())).isEqualTo(new Piece(Knight.create(), Color.WHITE));
    }
}
