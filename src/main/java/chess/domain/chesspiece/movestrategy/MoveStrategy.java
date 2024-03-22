package chess.domain.chesspiece.movestrategy;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;

public interface MoveStrategy {

    void move(ChessBoard chessBoard, Square startSquare, Square targetSquare);
}
