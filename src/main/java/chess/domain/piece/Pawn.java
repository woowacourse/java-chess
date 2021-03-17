package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.Position;

public class Pawn extends Piece {
    private boolean started;

    public Pawn(final String team) {
        super(team);
        started = false;
    }

    @Override
    public boolean isMovable(Position current, Position destination, ChessBoard chessBoard) {
        if ("Black".equals(getTeam())) {
            //최초 시작인 경우
            if (!started && (current.moveY(-1).equals(destination) || current.moveY(-2).equals(destination))) {
                started = true;
                return true;
            }
            //이미 시작한 경우
            if (started && current.moveY(-1).equals(destination)) {
                return true;
            }
            // 설마 대각선에 상대 기물이 있나?
            if (current.moveLeftDown().equals(destination) || current.moveRightDown().equals(destination)) {
                if (chessBoard.getChessBoard().get(destination).getTeam() == "White") {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    boolean checkPositionRule(Position current, Position destination) {
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
