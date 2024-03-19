package chess.chessPiece;

import chess.chessBoard.Square;
import java.util.Queue;

public abstract class ChessPiece {

    private final Camp camp;

    public ChessPiece(Camp camp) {
        this.camp = camp;
    }

    public abstract Queue<Square> findPath(Square startingPoint, Square endPoint);

    public boolean isBlackCamp() {
        return camp == Camp.BLACK;
    }

    public boolean isWhiteCamp() {
        return camp == Camp.WHITE;
    }
}
