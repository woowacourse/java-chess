package chess.piece;

import chess.ChessBoard;
import chess.position.Position;
import chess.Side;

import java.util.List;

public class Bishop extends ChessPiece {

    public Bishop(Side side) {
        super(side);
    }

    @Override
    List<Position> getMovablePosition(ChessBoard chessBoard, Position sourcePosition) {
        return null;
    }
}
