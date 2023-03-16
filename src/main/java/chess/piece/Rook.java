package chess.piece;

import chess.ChessBoard;
import chess.Position;
import chess.Side;

import java.util.List;

public class Rook extends ChessPiece{

    public Rook(Side side) {
        super(side);
    }

    @Override
    List<Position> getMovablePosition(ChessBoard chessBoard, Position sourcePosition) {
        return null;
    }
}
