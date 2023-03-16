package chess.domain.piece.moveRule;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;

public class QueenMoveRule implements MoveRule {

    private static QueenMoveRule instance;
    private QueenMoveRule() {}

    public static QueenMoveRule getInstance() {
        if (instance == null) {
            instance = new QueenMoveRule();
        }
        return instance;
    }

    @Override
    public void move(Position currentPosition, Position nextPosition, Map<Position, Piece> board) {
        if (!(currentPosition.isDiagonalEqual(nextPosition) || currentPosition.isStraightEqual(nextPosition))) {
            throw new IllegalArgumentException("퀸은 대각선 또는 직선 상으로만 움직일 수 있습니다.");
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
        return PieceType.QUEEN;
    }
}
