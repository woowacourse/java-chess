package chess.domain;

import chess.domain.piece.Direction;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;

import java.util.List;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, Piece> chessBoard;

    public ChessBoard(final Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public List<Piece> findAllPieces() {
        return chessBoard.values()
                .stream()
                .toList();
    }

    public Piece findPieceByPosition(final Position position) {
        return chessBoard.get(position);
    }

    public void move(final Position sourcePosition, final Position targetPosition) {
        validateCanMove(sourcePosition, targetPosition);

        Piece sourcePiece = chessBoard.get(sourcePosition);
        chessBoard.put(targetPosition, sourcePiece);
        chessBoard.put(sourcePosition, EmptyPiece.of());
    }

    private void validateCanMove(Position sourcePosition, Position targetPosition) {
        Piece sourcePiece = chessBoard.get(sourcePosition);
        Direction direction = sourcePosition.calculateDirection(targetPosition);

        validateValidDirection(sourcePiece, direction);
        validateTargetNotAlly(sourcePosition, targetPosition);

        Position nextPosition = sourcePosition.moveTowardDirection(direction);
        if (sourcePiece.canMoveMoreThenOnce()) {
            nextPosition = moveUntilReachTargetOrUntilMeetSomeone(targetPosition, direction, nextPosition);
        }

        validateReachedTarget(targetPosition, nextPosition);
    }

    private void validateValidDirection(final Piece piece, final Direction direction) {
        if (!piece.canMoveInTargetDirection(direction)) {
            throw new IllegalArgumentException("[ERROR] 선택한 기물이 이동할 수 없는 방향입니다.");
        }
    }

    private void validateTargetNotAlly(Position source, Position target) {
        if (chessBoard.get(source).isAlly(chessBoard.get(target))) {
            throw new IllegalArgumentException("[ERROR] 이동하려는 위치에 아군 기물이 존재합니다.");
        }
    }

    private Position moveUntilReachTargetOrUntilMeetSomeone(Position targetPosition, Direction direction, Position nextPosition) {
        while (!nextPosition.equals(targetPosition) && chessBoard.get(nextPosition) == EmptyPiece.of()) {
            nextPosition = nextPosition.moveTowardDirection(direction);
        }

        return nextPosition;
    }

    private static void validateReachedTarget(Position targetPosition, Position nextPosition) {
        if (!nextPosition.equals(targetPosition)) {
            throw new IllegalArgumentException("[ERROR] 선택한 기물은 해당 위치에 도달할 수 없습니다.");
        }
    }
}
