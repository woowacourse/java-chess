package chess.domain.piece.moveRule;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.util.Map;

public class KnightMoveRule implements MoveRule {

    private static KnightMoveRule instance;
    private KnightMoveRule() {}

    public static KnightMoveRule getInstance() {
        if (instance == null) {
            instance = new KnightMoveRule();
        }
        return instance;
    }

    @Override
    public void move(Position currentPosition, Position nextPosition,  Map<Position, Piece> board) {
        if (!currentPosition.isKnightPosition(nextPosition)) {
            throw new IllegalArgumentException("나이트는 L 모양으로만 움직일 수 있습니다.");
        }

        Piece pieceOfCurrentPosition = board.get(currentPosition);
        Piece pieceOfNextPosition = board.get(nextPosition);

        if(board.containsKey(nextPosition) && !pieceOfCurrentPosition.isOpponent(pieceOfNextPosition)){
            throw new IllegalArgumentException("도착 지점에 아군 기물이 있어 움직일 수 없습니다.");
        }
        Piece movingPiece = board.remove(currentPosition);
        board.put(nextPosition, movingPiece);
    }

    @Override
    public PieceType pieceType() {
        return PieceType.KNIGHT;
    }
}
