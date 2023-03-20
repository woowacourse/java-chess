package chess.domain.piece.moveRule;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.util.Map;

public interface MoveRule {
    void move(Position currentPosition, Position nextPosition, Map<Position, Piece> board);

    PieceType pieceType();

    default void validateDestination(Position currentPosition, Position nextPosition, Map<Position, Piece> board) {
        Piece pieceOfCurrentPosition = board.get(currentPosition);
        Piece pieceOfNextPosition = board.get(nextPosition);
        if (board.containsKey(nextPosition) && pieceOfCurrentPosition.isSameColor(pieceOfNextPosition)) {
            throw new IllegalArgumentException("도착 지점에 아군 기물이 있어 움직일 수 없습니다.");
        }
    }

    default void updatePiecePosition(Position currentPosition, Position nextPosition, Map<Position, Piece> board) {
        Piece movingPiece = board.remove(currentPosition);
        board.put(nextPosition, movingPiece);
    }
}
