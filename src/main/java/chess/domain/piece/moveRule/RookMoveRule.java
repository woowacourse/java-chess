package chess.domain.piece.moveRule;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;

public class RookMoveRule implements MoveRule {


    private static RookMoveRule instance;
    private RookMoveRule() {}

    public static RookMoveRule getInstance() {
        if (instance == null) {
            instance = new RookMoveRule();
        }
        return instance;
    }

    @Override
    public void move(Position currentPosition, Position nextPosition, Map<Position, Piece> board) {
        if (!currentPosition.isStraightEqual(nextPosition)) {
            throw new IllegalArgumentException("룩은 직선상으로만 움직일 수 있습니다.");
        }
        List<Position> route = currentPosition.getRoute(nextPosition);
        if(route.stream().anyMatch((position) -> board.containsKey(position))){
            throw new IllegalArgumentException("경로상에 다른 기물이 있어 움직일 수 없습니다.");
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
        return PieceType.ROOK;
    }

}
