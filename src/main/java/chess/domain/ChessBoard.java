package chess.domain;

import chess.domain.piece.Direction;
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

    public void move(final Position source, final Position target) {
        Piece piece = chessBoard.get(source);

        Direction direction = source.calculateDirection(target);

        validateDirection(piece, direction);
        validateNotAlly(source, target);

        Position nextPosition = source.moveTowardDirection(direction);

        while (piece.canMoveMoreThenOnce() && !nextPosition.equals(target) && chessBoard.get(nextPosition) == null) {
            nextPosition = nextPosition.moveTowardDirection(direction);
        }

        if (!nextPosition.equals(target)) {
            throw new IllegalArgumentException("[ERROR] 선택한 기물은 해당 위치에 도달할 수 없습니다.");
        }

        if (piece.canMoveMoreThenOnce() && !nextPosition.equals(target) && chessBoard.get(nextPosition) != null) {
            throw new IllegalArgumentException("[ERROR] 이동 경로에 기물이 존재합니다.");
        }

        chessBoard.put(target, piece);
        chessBoard.put(source, null);
    }

    private void validateNotAlly(Position source, Position target) {
        if (chessBoard.get(source).isAlly(chessBoard.get(target))) {
            throw new IllegalArgumentException("[ERROR] 이동하려는 위치에 아군 기물이 존재합니다.");
        }
    }

    private void validateDirection(final Piece piece, final Direction direction) {
        if (!piece.canMoveInTargetDirection(direction)) {
            throw new IllegalArgumentException("[ERROR] 선택한 기물이 이동할 수 없는 방향입니다.");
        }
    }
}
