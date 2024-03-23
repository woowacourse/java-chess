package domain.game;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.PieceGenerator;
import domain.position.Position;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, Piece> pieceByPosition;

    public ChessBoard() {
        this.pieceByPosition = PieceGenerator.generate();
    }

    public void add(Position position, Piece piece) {
        pieceByPosition.put(position, piece);
    }

    public void move(Position sourcePosition, Position targetPosition) {
        commonMoveValidate(sourcePosition, targetPosition);

        Piece findPiece = pieceByPosition.get(sourcePosition);
        pawnMoveValidate(sourcePosition, targetPosition, findPiece);
        pieceOnRouteValidate(sourcePosition, targetPosition, findPiece);

        if (findPiece.canMove(sourcePosition, targetPosition)) {
            updateChessBoardAfterMove(sourcePosition, targetPosition, findPiece);
        }
    }

    private void commonMoveValidate(Position sourcePosition, Position targetPosition) {
        if (!pieceByPosition.containsKey(sourcePosition)) {
            throw new IllegalStateException("해당 위치에 Piece가 존재하지 않습니다.");
        }
        if (hasSameColorPiece(sourcePosition, targetPosition)) {
            throw new IllegalStateException("같은 진영의 기물이 있는 곳으로 옮길 수 없습니다.");
        }
        if (sourcePosition.equals(targetPosition)) {
            throw new IllegalStateException("같은 위치로의 이동입니다. 다시 입력해주세요.");
        }
    }

    private boolean hasSameColorPiece(Position sourcePosition, Position targetPosition) {
        Piece sourcePiece = pieceByPosition.get(sourcePosition);

        if (pieceByPosition.containsKey(targetPosition)) {
            Piece targetPiece = pieceByPosition.get(targetPosition);
            return sourcePiece.isEqualColor(targetPiece.getColor());
        }
        return false;
    }

    private void pawnMoveValidate(Position sourcePosition, Position targetPosition, Piece findPiece) {
        if (findPiece.isPawn()) {
            Direction direction = Direction.findDirection(sourcePosition, targetPosition);
            blackPawnValidate(targetPosition, findPiece, direction);
            whitePawnValidate(targetPosition, findPiece, direction);
        }
    }

    private void blackPawnValidate(Position targetPosition, Piece findPiece, Direction direction) {
        if (findPiece.isEqualColor(Color.BLACK)) {
            pawnMoveValidate(
                    Direction.SOUTH,
                    List.of(Direction.SOUTH_EAST, Direction.SOUTH_WEST),
                    targetPosition,
                    direction
            );
        }
    }

    private void whitePawnValidate(Position targetPosition, Piece findPiece, Direction direction) {
        if (findPiece.isEqualColor(Color.WHITE)) {
            pawnMoveValidate(
                    Direction.NORTH,
                    List.of(Direction.NORTH_EAST, Direction.NORTH_WEST),
                    targetPosition,
                    direction
            );
        }
    }

    private void pawnMoveValidate(Direction forward,
                                  List<Direction> diagonals,
                                  Position targetPosition,
                                  Direction direction
    ) {
        forwardValidate(forward, direction, targetPosition);
        diagonalValidate(diagonals, direction, targetPosition);
    }

    private void forwardValidate(Direction forwardDirection, Direction direction, Position targetPosition) {
        if (direction == forwardDirection && pieceByPosition.containsKey(targetPosition)) {
            throw new IllegalStateException("전진하려는 곳에 다른 기물이 있으면 이동할 수 없습니다.");
        }
    }

    private void diagonalValidate(List<Direction> diagonals, Direction direction, Position targetPosition) {
        diagonals.stream()
                .filter(diagonal -> hasNotPieceAtDiagonal(direction, targetPosition, diagonal))
                .findAny()
                .ifPresent(dir -> {
                    throw new IllegalStateException("대각선으로 이동할 수 없습니다.");
                });
    }

    private boolean hasNotPieceAtDiagonal(Direction direction, Position targetPosition, Direction diagonal) {
        return direction == diagonal && !pieceByPosition.containsKey(targetPosition);
    }

    private void pieceOnRouteValidate(Position sourcePosition, Position targetPosition, Piece findPiece) {
        if (findPiece.isNotKnight()) {
            Direction direction = Direction.findDirection(sourcePosition, targetPosition);
            checkPieceOnRoute(sourcePosition, targetPosition, direction);
        }
    }

    private void checkPieceOnRoute(Position sourcePosition, Position targetPosition, Direction direction) {
        Position movePosition = sourcePosition.move(direction);

        while (!movePosition.equals(targetPosition)) {
            if (pieceByPosition.containsKey(movePosition)) {
                throw new IllegalStateException("이동 경로에 다른 기물이 있으면 이동할 수 없습니다.");
            }
            movePosition = movePosition.move(direction);
        }
    }

    private void updateChessBoardAfterMove(Position sourcePosition, Position targetPosition, Piece findPiece) {
        if (pieceByPosition.containsKey(targetPosition)) {
            pieceByPosition.remove(targetPosition);
        }
        pieceByPosition.put(targetPosition, findPiece);
        pieceByPosition.remove(sourcePosition);
    }

    public boolean hasPiece(final Position position) {
        return pieceByPosition.containsKey(position);
    }

    public Piece findPieceByPosition(Position targetPosition) {
        return pieceByPosition.get(targetPosition);
    }
}
