package chess.domain.chesspiece.movestrategy;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;

public class EmptyMoveStrategy implements MoveStrategy{

    @Override
    public void move(ChessBoard chessBoard, Square startSquare, Square targetSquare) {
    }
}
