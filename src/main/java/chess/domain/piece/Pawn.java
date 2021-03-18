package chess.domain.piece;

import chess.domain.Position;

import java.util.Map;

public class Pawn extends Piece {
    private final int direction;

    public Pawn(final int direction) {
        this.direction = direction;
    }

    @Override
    public boolean isMovable(Position current, Position destination, Map<Position, Piece> chessBoard){
        if (!isMoved && (current.moveY(-1).equals(destination) || current.moveY(-2).equals(destination))) {
            isMoved = true;
            return true;
        }
        if (isMoved && current.moveY(-1).equals(destination)) {
            return true;
        }
        // 설마 대각선에 상대 기물이 있나?
//        if (current.moveLeftDown().equals(destination) || current.moveRightDown().equals(destination)) {
//            if (chessBoard.getChessBoard().get(destination).getTeam() == "White") {
//                return true;
//            }
//        }
        return false;
    }

    @Override
    public boolean checkPositionRule(Position current, Position destination){

        if (!isMoved && current.moveY(direction).equals(destination) || current.moveY(direction * 2).equals(destination)) {
            return true;
        }

        if (isMoved && current.moveY(direction).equals(destination)) {
            return true;
        }
        if(current.checkDiagonalToDirection(destination, direction)) {
            return true;
        }
        return false;
    }

}
