package chess.domain.piece.moveRule;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.util.Map;

public class KingMoveRule implements MoveRule {

    private static KingMoveRule instance;

    private KingMoveRule() {}

    public static KingMoveRule getInstance() {
        if (instance == null) {
            instance = new KingMoveRule();
        }
        return instance;
    }

    @Override
    public void move(Position currentPosition, Position nextPosition, Map<Position, Piece> board) {
        if (!currentPosition.isNear(nextPosition)) {
            throw new IllegalArgumentException("킹은 인접한 칸으로만 이동할 수 있습니다.");
        }

        Piece pieceOfCurrentPosition = board.get(currentPosition);
        Piece pieceOfNextPosition = board.get(nextPosition);

        if (board.containsKey(nextPosition) && !pieceOfCurrentPosition.isOpponent(pieceOfNextPosition)) {
            throw new IllegalArgumentException("도착 지점에 아군 기물이 있어 움직일 수 없습니다.");
        }
        Piece movingPiece = board.remove(currentPosition);
        board.put(nextPosition, movingPiece);
    }

    @Override
    public PieceType pieceType() {
        return PieceType.KING;
    }
}
