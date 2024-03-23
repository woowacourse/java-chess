package domain.game;

import static fixture.PositionFixture.*;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.piecerole.King;
import domain.piece.piecerole.Knight;
import domain.piece.piecerole.Pawn;
import domain.piece.piecerole.Queen;
import domain.piece.piecerole.Rook;
import domain.position.Position;
import fixture.PositionFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardTest {

    @DisplayName("source에 위치한 piece를 target으로 이동한다.")
    @Test
    void movePieceToTarget() {
        Position sourcePosition = createB1();
        Position targetPosition = createB2();

        ChessBoard chessBoard = new ChessBoard();

        Piece piece = new Piece(new King(), Color.BLACK);
        chessBoard.add(sourcePosition, piece);

        chessBoard.move(sourcePosition, targetPosition);

        Piece findPiece = chessBoard.findPieceByPosition(targetPosition);
        Assertions.assertThat(findPiece).isEqualTo(piece);
    }

    @DisplayName("source에 piece가 없다면 에러를 반환한다.")
    @Test
    void movePieceIfSourceHasNotPiece() {
        Position sourcePosition = createB1();
        Position targetPosition = createC2();

        ChessBoard chessBoard = new ChessBoard();

        Assertions.assertThatThrownBy(() -> chessBoard.checkRoute(sourcePosition, targetPosition))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("옮기고자 하는 위치에 같은 진영의 Piece가 있다면 에러를 반환한다.")
    @Test
    void hasSameColorPiece() {
        Position sourcePosition = createB1();
        Position targetPosition = createC2();

        Piece sourcePiece = new Piece(new King(), Color.BLACK);
        Piece targetPiece = new Piece(new Queen(), Color.BLACK);

        ChessBoard chessBoard = new ChessBoard();
        chessBoard.add(sourcePosition, sourcePiece);
        chessBoard.add(targetPosition, targetPiece);

        Assertions.assertThatThrownBy(() -> chessBoard.checkRoute(sourcePosition, targetPosition))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("같은 위치로의 이동이라면 에러를 반환한다.")
    @Test
    void moveToSamePosition() {
        Position sourcePosition = createB1();
        Position targetPosition = createB1();

        Piece sourcePiece = new Piece(new King(), Color.BLACK);
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.add(sourcePosition, sourcePiece);

        Assertions.assertThatThrownBy(() -> chessBoard.checkRoute(sourcePosition, targetPosition))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("앞에 다른 진영의 기물이 있는 경우 폰이 이동하지 못한다.")
    @Test
    void movePawnWhenFrontPositionHasOtherPiece() {
        Position sourcePosition = createB1();
        Position targetPosition = createB2();

        Piece sourcePiece = new Piece(new Pawn(Color.WHITE), Color.WHITE);
        Piece targetPiece = new Piece(new Pawn(Color.BLACK), Color.BLACK);

        ChessBoard chessBoard = new ChessBoard();
        chessBoard.add(sourcePosition, sourcePiece);
        chessBoard.add(targetPosition, targetPiece);

        Assertions.assertThatThrownBy(() -> chessBoard.checkRoute(sourcePosition, targetPosition))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("대각선에 다른 진영의 기물이 있는 경우 폰이 이동할 수 있다.")
    @Test
    void movePawnWhenDiagonalPositionHasOtherPiece() {
        Position sourcePosition = createB1();
        Position targetPosition = createC2();

        Piece sourcePiece = new Piece(new Pawn(Color.WHITE), Color.WHITE);
        Piece targetPiece = new Piece(new Pawn(Color.BLACK), Color.BLACK);

        ChessBoard chessBoard = new ChessBoard();
        chessBoard.add(sourcePosition, sourcePiece);
        chessBoard.add(targetPosition, targetPiece);

        chessBoard.move(sourcePosition, targetPosition);

        Piece findPiece = chessBoard.findPieceByPosition(targetPosition);

        Assertions.assertThat(findPiece).isEqualTo(sourcePiece);
    }

    @DisplayName("나이트를 제외한 기물은 이동하는 경로에 기물이 있으면 이동하지 못한다.")
    @Test
    void isOverlappedPath() {
        Position sourcePosition = createB1();
        Position targetPosition = createB7();
        Position blockPosition = createB2();

        Piece sourcePiece = new Piece(new Rook(), Color.WHITE);
        Piece targetPiece = new Piece(new Rook(), Color.WHITE);
        Piece blockPiece = new Piece(new Pawn(Color.BLACK), Color.BLACK);

        ChessBoard chessBoard = new ChessBoard();
        chessBoard.add(sourcePosition, sourcePiece);
        chessBoard.add(targetPosition, targetPiece);
        chessBoard.add(blockPosition, blockPiece);

        Assertions.assertThatThrownBy(() -> chessBoard.checkRoute(sourcePosition, targetPosition))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("나이트는 이동하는 경로에 기물이 있어도 이동할 수 있다.")
    @Test
    void knightCanJump() {
        Position sourcePosition = createB1();
        Position targetPosition = createC3();
        Position blockPosition = createB2();

        Piece sourcePiece = new Piece(new Knight(), Color.WHITE);
        Piece targetPiece = new Piece(new Knight(), Color.WHITE);
        Piece blockPiece = new Piece(new Pawn(Color.BLACK), Color.BLACK);

        ChessBoard chessBoard = new ChessBoard();
        chessBoard.add(sourcePosition, sourcePiece);
        chessBoard.add(targetPosition, targetPiece);
        chessBoard.add(blockPosition, blockPiece);

        Assertions.assertThat(chessBoard.findPieceByPosition(targetPosition)).isEqualTo(sourcePiece);
    }
}
