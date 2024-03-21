package domain.game;

import domain.chessboard.Square;
import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.piecerole.King;
import domain.piece.piecerole.Knight;
import domain.piece.piecerole.Pawn;
import domain.piece.piecerole.Queen;
import domain.piece.piecerole.Rook;
import domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardTest {

    @DisplayName("source에 위치한 piece를 target으로 이동한다.")
    @Test
    void movePieceToTarget() {
        Position sourcePosition = PositionFixture.generateB1Position();
        Position targetPosition = PositionFixture.generateB2Position();
        Square sourceSquare = SquareFixture.generateSquare(sourcePosition);
        Square targetSquare = SquareFixture.generateSquare(targetPosition);

        ChessBoard chessBoard = new ChessBoard();

        Piece piece = new Piece(new King(), Color.BLACK);
        chessBoard.add(sourceSquare, piece);

        chessBoard.move(sourceSquare, targetSquare);

        Piece findPiece = chessBoard.findPieceBySquare(targetSquare);
        Assertions.assertThat(findPiece).isEqualTo(piece);
    }

    @DisplayName("source에 piece가 없다면 에러를 반환한다.")
    @Test
    void movePieceIfSourceHasNotPiece() {
        Position sourcePosition = PositionFixture.generateB1Position();
        Position targetPosition = PositionFixture.generateC2Position();
        Square sourceSquare = SquareFixture.generateSquare(sourcePosition);
        Square targetSquare = SquareFixture.generateSquare(targetPosition);

        ChessBoard chessBoard = new ChessBoard();

        Assertions.assertThatThrownBy(() -> chessBoard.move(sourceSquare, targetSquare))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("옮기고자 하는 위치에 같은 진영의 Piece가 있다면 에러를 반환한다.")
    @Test
    void hasSameColorPiece() {
        Position sourcePosition = PositionFixture.generateB1Position();
        Position targetPosition = PositionFixture.generateC2Position();
        Square sourceSquare = SquareFixture.generateSquare(sourcePosition);
        Square targetSquare = SquareFixture.generateSquare(targetPosition);

        Piece sourcePiece = new Piece(new King(), Color.BLACK);
        Piece targetPiece = new Piece(new Queen(), Color.BLACK);

        ChessBoard chessBoard = new ChessBoard();
        chessBoard.add(sourceSquare, sourcePiece);
        chessBoard.add(targetSquare, targetPiece);

        Assertions.assertThatThrownBy(() -> chessBoard.move(sourceSquare, targetSquare))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("같은 위치로의 이동이라면 에러를 반환한다.")
    @Test
    void moveToSameSquare() {
        Position sourcePosition = PositionFixture.generateB1Position();
        Position targetPosition = PositionFixture.generateB1Position();
        Square sourceSquare = SquareFixture.generateSquare(sourcePosition);
        Square targetSquare = SquareFixture.generateSquare(targetPosition);

        Piece sourcePiece = new Piece(new King(), Color.BLACK);
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.add(sourceSquare, sourcePiece);

        Assertions.assertThatThrownBy(() -> chessBoard.move(sourceSquare, targetSquare))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("앞에 다른 진영의 기물이 있는 경우 폰이 이동하지 못한다.")
    @Test
    void movePawnWhenFrontSquareHasOtherPiece() {
        Position sourcePosition = PositionFixture.generateB1Position();
        Position targetPosition = PositionFixture.generateB2Position();
        Square sourceSquare = SquareFixture.generateSquare(sourcePosition);
        Square targetSquare = SquareFixture.generateSquare(targetPosition);

        Piece sourcePiece = new Piece(new Pawn(Color.WHITE), Color.WHITE);
        Piece targetPiece = new Piece(new Pawn(Color.BLACK), Color.BLACK);

        ChessBoard chessBoard = new ChessBoard();
        chessBoard.add(sourceSquare, sourcePiece);
        chessBoard.add(targetSquare, targetPiece);

        Assertions.assertThatThrownBy(() -> chessBoard.move(sourceSquare, targetSquare))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("대각선에 다른 진영의 기물이 있는 경우 폰이 이동할 수 있다.")
    @Test
    void movePawnWhenDiagonalSquareHasOtherPiece() {
        Position sourcePosition = PositionFixture.generateB1Position();
        Position targetPosition = PositionFixture.generateC2Position();
        Square sourceSquare = SquareFixture.generateSquare(sourcePosition);
        Square targetSquare = SquareFixture.generateSquare(targetPosition);

        Piece sourcePiece = new Piece(new Pawn(Color.WHITE), Color.WHITE);
        Piece targetPiece = new Piece(new Pawn(Color.BLACK), Color.BLACK);

        ChessBoard chessBoard = new ChessBoard();
        chessBoard.add(sourceSquare, sourcePiece);
        chessBoard.add(targetSquare, targetPiece);

        chessBoard.move(sourceSquare, targetSquare);

        Piece findPiece = chessBoard.findPieceBySquare(targetSquare);

        Assertions.assertThat(findPiece).isEqualTo(sourcePiece);
    }

    @DisplayName("나이트를 제외한 기물은 이동하는 경로에 기물이 있으면 이동하지 못한다.")
    @Test
    void isOverlappedPath() {
        Position sourcePosition = PositionFixture.generateB1Position();
        Position targetPosition = PositionFixture.generateB7Position();
        Position blockPosition = PositionFixture.generateB2Position();

        Square sourceSquare = SquareFixture.generateSquare(sourcePosition);
        Square targetSquare = SquareFixture.generateSquare(targetPosition);
        Square blockSquare = SquareFixture.generateSquare(blockPosition);

        Piece sourcePiece = new Piece(new Rook(), Color.WHITE);
        Piece targetPiece = new Piece(new Rook(), Color.WHITE);
        Piece blockPiece =  new Piece(new Pawn(Color.BLACK), Color.BLACK);

        ChessBoard chessBoard = new ChessBoard();
        chessBoard.add(sourceSquare, sourcePiece);
        chessBoard.add(targetSquare, targetPiece);
        chessBoard.add(blockSquare, blockPiece);

        Assertions.assertThatThrownBy(() -> chessBoard.move(sourceSquare, targetSquare))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("나이트는 이동하는 경로에 기물이 있어도 이동할 수 있다.")
    @Test
    void knightCanJump() {
        Position sourcePosition = PositionFixture.generateB1Position();
        Position targetPosition = PositionFixture.generateC3Position();
        Position blockPosition = PositionFixture.generateB2Position();

        Square sourceSquare = SquareFixture.generateSquare(sourcePosition);
        Square targetSquare = SquareFixture.generateSquare(targetPosition);
        Square blockSquare = SquareFixture.generateSquare(blockPosition);

        Piece sourcePiece = new Piece(new Knight(), Color.WHITE);
        Piece targetPiece = new Piece(new Knight(), Color.WHITE);
        Piece blockPiece = new Piece(new Pawn(Color.BLACK), Color.BLACK);

        ChessBoard chessBoard = new ChessBoard();
        chessBoard.add(sourceSquare, sourcePiece);
        chessBoard.add(targetSquare, targetPiece);
        chessBoard.add(blockSquare, blockPiece);

        Assertions.assertThat(chessBoard.findPieceBySquare(targetSquare)).isEqualTo(sourcePiece);
    }
}
