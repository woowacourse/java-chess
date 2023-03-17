package chess.piece;

import chess.ChessBoard;
import chess.Side;
import chess.position.Position;
import java.util.List;

public class Queen extends ChessPiece {

    public Queen(Side side) {
        super(side);
    }

    @Override
    List<Position> getMovablePosition(ChessBoard chessBoard, Position sourcePosition) {
        return null;
    }
}
