package domain.game;

import domain.chessboard.Square;
import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.PieceRole;
import domain.piece.PieceType;
import domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceMoverTest {

    @DisplayName("source에 위치한 piece를 target으로 이동한다.")
    @Test
    void movePieceToTarget() {
        Position sourcePosition = PositionFixture.generateSourcePosition();
        Position targetPosition = PositionFixture.generateTargetPosition();
        Square sourceSquare = SquareFixture.generateSquare(sourcePosition);
        Square targetSquare = SquareFixture.generateSquare(targetPosition);

        PieceMover pieceMover = new PieceMover();

        Piece piece = new Piece(new PieceType(PieceRole.KING, Color.BLACK), sourcePosition);
        pieceMover.add(sourceSquare, piece);

        pieceMover.move(sourceSquare, targetSquare);

        Piece findPiece = pieceMover.findPieceBySquare(targetSquare);
        Assertions.assertThat(findPiece).isEqualTo(piece);
    }

    @DisplayName("source에 piece가 없다면 에러를 반환한다.")
    @Test
    void movePieceIfSourceHasNotPiece() {
        Position sourcePosition = PositionFixture.generateSourcePosition();
        Position targetPosition = PositionFixture.generateTargetPosition();
        Square sourceSquare = SquareFixture.generateSquare(sourcePosition);
        Square targetSquare = SquareFixture.generateSquare(targetPosition);

        PieceMover pieceMover = new PieceMover();

        Assertions.assertThatThrownBy(() -> pieceMover.move(sourceSquare, targetSquare))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("옮기고자 하는 위치에 같은 진영의 Piece가 있다면 에러를 반환한다.")
    @Test
    void hasSameColorPiece() {
        Position sourcePosition = PositionFixture.generateSourcePosition();
        Position targetPosition = PositionFixture.generateTargetPosition();
        Square sourceSquare = SquareFixture.generateSquare(sourcePosition);
        Square targetSquare = SquareFixture.generateSquare(targetPosition);

        Piece sourcePiece = new Piece(new PieceType(PieceRole.KING, Color.BLACK), sourcePosition);
        Piece targetPiece = new Piece(new PieceType(PieceRole.QUEEN, Color.BLACK), targetPosition);

        PieceMover pieceMover = new PieceMover();
        pieceMover.add(sourceSquare, sourcePiece);
        pieceMover.add(targetSquare, targetPiece);

        Assertions.assertThatThrownBy(() -> pieceMover.move(sourceSquare, targetSquare))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("같은 위치로의 이동이라면 에러를 반환한다.")
    @Test
    void moveToSameSquare() {
        Position sourcePosition = PositionFixture.generateSourcePosition();
        Position targetPosition = PositionFixture.generateSourcePosition();
        Square sourceSquare = SquareFixture.generateSquare(sourcePosition);
        Square targetSquare = SquareFixture.generateSquare(targetPosition);

        Piece sourcePiece = new Piece(new PieceType(PieceRole.KING, Color.BLACK), sourcePosition);

        PieceMover pieceMover = new PieceMover();
        pieceMover.add(sourceSquare, sourcePiece);

        Assertions.assertThatThrownBy(() -> pieceMover.move(sourceSquare, targetSquare))
                .isInstanceOf(IllegalStateException.class);
    }

}
