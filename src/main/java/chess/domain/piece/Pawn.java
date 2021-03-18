package chess.domain.piece;

import chess.domain.Position;

import java.util.Map;

public class Pawn extends Piece {
    private boolean started;

    public Pawn() {
        started = false;
    }

    @Override
    public boolean isMovable(Position current, Position destination, Map<Position, Piece> chessBoard) {
        if (!started && (current.moveY(-1).equals(destination) || current.moveY(-2).equals(destination))) {
            started = true;
            return true;
        }
        if (started && current.moveY(-1).equals(destination)) {
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
    public boolean checkPositionRule(Position current, Position destination) {
        if (!started && (current.moveDown().equals(destination) || current.moveDown().moveDown().equals(destination))) {
            started = true;
            return true;
        }
        if (started && current.moveDown().equals(destination)) {
            return true;
        }
        return false;
    }
}
