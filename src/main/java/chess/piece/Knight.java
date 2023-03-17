package chess.piece;

import chess.ChessBoard;
import chess.position.Position;
import chess.Side;

import java.util.List;

public class Knight extends ChessPiece{

    public Knight(Side side) {
        super(side);
    }

    @Override
    List<Position> getMovablePosition(ChessBoard chessBoard, Position sourcePosition) {
        return null;
    }
}
