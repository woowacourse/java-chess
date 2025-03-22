package chess.piece;

import chess.board.ChessBoard;
import chess.board.Color;
import chess.board.Position;

public interface Piece {

    boolean canMoveToDestination(ChessBoard chessBoard, Position start, Position end);

    boolean isSameColor(Color color);
}
