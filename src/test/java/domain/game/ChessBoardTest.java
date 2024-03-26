package domain.game;

import static fixture.PositionFixture.*;
import static org.assertj.core.api.Assertions.*;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.piecerole.WhitePawn;
import domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardTest {

    @DisplayName("source에 위치한 piece를 target으로 이동한다.")
    @Test
    void movePieceToTarget() {
        Position sourcePosition = createB2();
        Position targetPosition = createB3();

        ChessBoard chessBoard = new ChessBoard();

        chessBoard.move(sourcePosition, targetPosition);

        Piece findPiece = chessBoard.findPieceByPosition(targetPosition);
        assertThat(findPiece).isEqualTo(new Piece(new WhitePawn(), Color.WHITE));
    }

    @DisplayName("source에 piece가 없다면 에러를 반환한다.")
    @Test
    void movePieceIfSourceHasNotPiece() {
        Position sourcePosition = createB3();
        Position targetPosition = createB4();

        ChessBoard chessBoard = new ChessBoard();

        assertThatThrownBy(() -> chessBoard.checkRoute(sourcePosition, targetPosition, Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("옮기고자 하는 위치에 같은 진영의 Piece가 있다면 에러를 반환한다.")
    @Test
    void hasSameColorPiece() {
        Position sourcePosition = createB2();
        Position targetPosition = createC2();

        ChessBoard chessBoard = new ChessBoard();

        assertThatThrownBy(() -> chessBoard.checkRoute(sourcePosition, targetPosition, Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("같은 위치로의 이동이라면 에러를 반환한다.")
    @Test
    void moveToSamePosition() {
        Position sourcePosition = createB1();
        Position targetPosition = createB1();

        ChessBoard chessBoard = new ChessBoard();

        assertThatThrownBy(() -> chessBoard.checkRoute(sourcePosition, targetPosition, Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("앞에 다른 진영의 기물이 있는 경우 폰이 이동하지 못한다.")
    @Test
    void movePawnWhenFrontPositionHasOtherPiece() {
        Position whiteSourcePosition = createB2();
        Position whiteTargetPosition = createB4();

        Position blackSourcePosition = createB7();
        Position balckTargetPosition = createB5();

        ChessBoard chessBoard = new ChessBoard();
        chessBoard.move(whiteSourcePosition, whiteTargetPosition);
        chessBoard.move(blackSourcePosition, balckTargetPosition);

        assertThatThrownBy(() -> chessBoard.checkRoute(whiteTargetPosition, balckTargetPosition, Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("대각선에 다른 진영의 기물이 있는 경우 폰이 이동할 수 있다.")
    @Test
    void movePawnWhenDiagonalPositionHasOtherPiece() {
        Position whiteSourcePosition = createB2();
        Position whiteTargetPosition = createB4();

        Position blackSourcePosition = createC7();
        Position balckTargetPosition = createC5();

        ChessBoard chessBoard = new ChessBoard();
        chessBoard.move(whiteSourcePosition, whiteTargetPosition);
        chessBoard.move(blackSourcePosition, balckTargetPosition);

        assertThatCode(() -> chessBoard.checkRoute(whiteTargetPosition, balckTargetPosition, Color.WHITE))
                .doesNotThrowAnyException();
    }

    @DisplayName("나이트를 제외한 기물은 이동하는 경로에 기물이 있으면 이동하지 못한다.")
    @Test
    void isOverlappedPath() {
        Position sourcePosition = createA1();
        Position targetPosition = createA3();

        ChessBoard chessBoard = new ChessBoard();

        assertThatThrownBy(() -> chessBoard.checkRoute(sourcePosition, targetPosition, Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("나이트는 이동하는 경로에 기물이 있어도 이동할 수 있다.")
    @Test
    void knightCanJump() {
        Position sourcePosition = createB1();
        Position targetPosition = createC3();

        ChessBoard chessBoard = new ChessBoard();
        assertThatCode(() -> chessBoard.checkRoute(sourcePosition, targetPosition, Color.WHITE))
                .doesNotThrowAnyException();
    }
}
