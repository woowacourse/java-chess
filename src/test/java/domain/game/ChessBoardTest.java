package domain.game;

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
        Position sourcePosition = PositionFixture.generateB2Position();
        Position targetPosition = PositionFixture.generateB3Position();

        ChessBoard chessBoard = ChessBoardGenerator.generate();

        chessBoard.move(sourcePosition, targetPosition);

        Piece findPiece = chessBoard.findPieceBySquare(targetPosition);
        Assertions.assertThat(findPiece).isEqualTo(new Piece(Pawn.from(Color.WHITE), Color.WHITE));
    }

    @DisplayName("source에 piece가 없다면 에러를 반환한다.")
    @Test
    void movePieceIfSourceHasNotPiece() {
        Position sourcePosition = PositionFixture.generateB1Position();
        Position targetPosition = PositionFixture.generateC2Position();

        ChessBoard chessBoard = ChessBoardGenerator.generate();

        Assertions.assertThatThrownBy(() -> chessBoard.move(sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("옮기고자 하는 위치에 같은 진영의 Piece가 있다면 에러를 반환한다.")
    @Test
    void hasSameColorPiece() {
        Position sourcePosition = PositionFixture.generateB1Position();
        Position targetPosition = PositionFixture.generateC2Position();

        Piece sourcePiece = new Piece(King.from(), Color.BLACK);
        Piece targetPiece = new Piece(Queen.from(), Color.BLACK);

        ChessBoard chessBoard = ChessBoardGenerator.generate();
        chessBoard.add(sourcePosition, sourcePiece);
        chessBoard.add(targetPosition, targetPiece);

        Assertions.assertThatThrownBy(() -> chessBoard.move(sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("같은 위치로의 이동이라면 에러를 반환한다.")
    @Test
    void moveToSameSquare() {
        Position sourcePosition = PositionFixture.generateB1Position();
        Position targetPosition = PositionFixture.generateB1Position();

        Piece sourcePiece = new Piece(King.from(), Color.BLACK);
        ChessBoard chessBoard = ChessBoardGenerator.generate();
        chessBoard.add(sourcePosition, sourcePiece);

        Assertions.assertThatThrownBy(() -> chessBoard.move(sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("앞에 다른 진영의 기물이 있는 경우 폰이 이동하지 못한다.")
    @Test
    void movePawnWhenFrontSquareHasOtherPiece() {
        Position sourcePosition = PositionFixture.generateB1Position();
        Position targetPosition = PositionFixture.generateB2Position();

        Piece sourcePiece = new Piece(Pawn.from(Color.WHITE), Color.WHITE);
        Piece targetPiece = new Piece(Pawn.from(Color.BLACK), Color.BLACK);

        ChessBoard chessBoard = ChessBoardGenerator.generate();
        chessBoard.add(sourcePosition, sourcePiece);
        chessBoard.add(targetPosition, targetPiece);

        Assertions.assertThatThrownBy(() -> chessBoard.move(sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("대각선에 다른 진영의 기물이 있는 경우 폰이 이동할 수 있다.")
    @Test
    void movePawnWhenDiagonalSquareHasOtherPiece() {
        Position sourcePosition = PositionFixture.generateB1Position();
        Position targetPosition = PositionFixture.generateC2Position();

        Piece sourcePiece = new Piece(Pawn.from(Color.WHITE), Color.WHITE);
        Piece targetPiece = new Piece(Pawn.from(Color.BLACK), Color.BLACK);

        ChessBoard chessBoard = ChessBoardGenerator.generate();
        chessBoard.add(sourcePosition, sourcePiece);
        chessBoard.add(targetPosition, targetPiece);

        chessBoard.move(sourcePosition, targetPosition);

        Piece findPiece = chessBoard.findPieceBySquare(targetPosition);

        Assertions.assertThat(findPiece).isEqualTo(sourcePiece);
    }

    @DisplayName("나이트를 제외한 기물은 이동하는 경로에 기물이 있으면 이동하지 못한다.")
    @Test
    void isOverlappedPath() {
        Position sourcePosition = PositionFixture.generateB1Position();
        Position targetPosition = PositionFixture.generateB7Position();
        Position blockPosition = PositionFixture.generateB2Position();

        Piece sourcePiece = new Piece(Rook.from(), Color.WHITE);
        Piece targetPiece = new Piece(Rook.from(), Color.WHITE);
        Piece blockPiece = new Piece(Pawn.from(Color.BLACK), Color.BLACK);

        ChessBoard chessBoard = ChessBoardGenerator.generate();
        chessBoard.add(sourcePosition, sourcePiece);
        chessBoard.add(targetPosition, targetPiece);
        chessBoard.add(blockPosition, blockPiece);

        Assertions.assertThatThrownBy(() -> chessBoard.move(sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("나이트는 이동하는 경로에 기물이 있어도 이동할 수 있다.")
    @Test
    void knightCanJump() {
        Position sourcePosition = PositionFixture.generateB1Position();
        Position targetPosition = PositionFixture.generateC3Position();
        Position blockPosition = PositionFixture.generateB2Position();

        Piece sourcePiece = new Piece(Knight.from(), Color.WHITE);
        Piece targetPiece = new Piece(Knight.from(), Color.WHITE);
        Piece blockPiece = new Piece(Pawn.from(Color.BLACK), Color.BLACK);

        ChessBoard chessBoard = ChessBoardGenerator.generate();
        chessBoard.add(sourcePosition, sourcePiece);
        chessBoard.add(targetPosition, targetPiece);
        chessBoard.add(blockPosition, blockPiece);

        Assertions.assertThat(chessBoard.findPieceBySquare(targetPosition)).isEqualTo(sourcePiece);
    }
}
