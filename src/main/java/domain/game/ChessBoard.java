package domain.game;

import domain.piece.Color;
import domain.piece.Piece;
import domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    private final Map<Square, Piece> pieceBySquare;

    public ChessBoard() {
        this.pieceBySquare = new HashMap<>();
    }

    public void add(Square square, Piece piece) {
        pieceBySquare.put(square, piece);
    }

    public void move(Square sourceSquare, Square targetSquare) {
        commonMoveValidate(sourceSquare, targetSquare);

        Piece findPiece = pieceBySquare.get(sourceSquare);
        pawnMoveValidate(sourceSquare, targetSquare, findPiece);
        moveValidateExceptKnight(sourceSquare, targetSquare, findPiece);

        if (findPiece.canMove(sourceSquare.position(), targetSquare.position())) {
            update(sourceSquare, targetSquare, findPiece);
        }
    }

    private void commonMoveValidate(Square sourceSquare, Square targetSquare) {
        if (!pieceBySquare.containsKey(sourceSquare)) {
            throw new IllegalStateException("해당 위치에 Piece가 존재하지 않습니다.");
        }
        if (hasSameColorPiece(sourceSquare, targetSquare)) {
            throw new IllegalStateException("같은 진영의 기물이 있는 곳으로 옮길 수 없습니다.");
        }
        if (sourceSquare.equals(targetSquare)) {
            throw new IllegalStateException("같은 위치로의 이동입니다. 다시 입력해주세요.");
        }
    }

    private boolean hasSameColorPiece(Square sourceSquare, Square targetSquare) {
        Piece sourcePiece = pieceBySquare.get(sourceSquare);

        if (pieceBySquare.containsKey(targetSquare)) {
            Piece targetPiece = pieceBySquare.get(targetSquare);
            return sourcePiece.isEqualColor(targetPiece.color());
        }
        return false;
    }

    private void pawnMoveValidate(Square sourceSquare, Square targetSquare, Piece findPiece) {
        if (findPiece.isPawn()) {
            Direction direction = Direction.findDirection(sourceSquare.position(), targetSquare.position());
            blackPawnValidate(targetSquare, findPiece, direction);
            whitePawnValidate(targetSquare, findPiece, direction);
        }
    }

    private void blackPawnValidate(Square targetSquare, Piece findPiece, Direction direction) {
        if (findPiece.isEqualColor(Color.BLACK)) {
            pawnMoveValidate(
                    Direction.SOUTH,
                    List.of(Direction.SOUTH_EAST, Direction.SOUTH_WEST),
                    targetSquare,
                    direction
            );
        }
    }

    private void whitePawnValidate(Square targetSquare, Piece findPiece, Direction direction) {
        if (findPiece.isEqualColor(Color.WHITE)) {
            pawnMoveValidate(
                    Direction.NORTH,
                    List.of(Direction.NORTH_EAST, Direction.NORTH_WEST),
                    targetSquare,
                    direction
            );
        }
    }

    private void pawnMoveValidate(Direction forward,
                                  List<Direction> diagonals,
                                  Square targetSquare,
                                  Direction direction
    ) {
        forwardValidate(forward, direction, targetSquare);
        diagonalValidate(diagonals, direction, targetSquare);
    }

    private void forwardValidate(Direction forwardDirection, Direction direction, Square targetSquare) {
        if (direction == forwardDirection && pieceBySquare.containsKey(targetSquare)) {
            throw new IllegalStateException("전진하려는 곳에 다른 기물이 있으면 이동할 수 없습니다.");
        }
    }

    private void diagonalValidate(List<Direction> diagonals, Direction direction, Square targetSquare) {
        diagonals.stream()
                .filter(diagonal -> hasNotPieceAtDiagonal(direction, targetSquare, diagonal))
                .findAny()
                .ifPresent(dir -> {
                    throw new IllegalStateException("대각선으로 이동할 수 없습니다.");
                });
    }

    private boolean hasNotPieceAtDiagonal(Direction direction, Square targetSquare, Direction diagonal) {
        return direction == diagonal && !pieceBySquare.containsKey(targetSquare);
    }

    private void moveValidateExceptKnight(Square sourceSquare, Square targetSquare, Piece findPiece) {
        if (findPiece.isNotKnight()) {
            Direction direction = Direction.findDirection(sourceSquare.position(), targetSquare.position());

            Position here = new Position(sourceSquare.position());
            here.move(direction);
            checkPieceOnRoute(targetSquare, here, direction);
        }
    }

    private void checkPieceOnRoute(Square targetSquare, Position here, Direction direction) {
        while (!here.equals(targetSquare.position())) {
            if (pieceBySquare.containsKey(new Square(here))) {
                throw new IllegalStateException("이동 경로에 다른 기물이 있으면 이동할 수 없습니다.");
            }
            here.move(direction);
        }
    }

    private void update(Square sourceSquare, Square targetSquare, Piece findPiece) {
        if (pieceBySquare.containsKey(targetSquare)) {
            pieceBySquare.remove(targetSquare);
        }
        pieceBySquare.put(targetSquare, findPiece);
        pieceBySquare.remove(sourceSquare);
    }

    public boolean hasPiece(final Square square) {
        return pieceBySquare.containsKey(square);
    }

    public Piece findPieceBySquare(Square targetSquare) {
        return pieceBySquare.get(targetSquare);
    }
}
