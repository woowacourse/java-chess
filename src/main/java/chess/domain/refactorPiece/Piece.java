package chess.domain.refactorPiece;

import chess.domain.refactorBoard.ChessBoard;
import chess.domain.refactorPosition.Position;

public interface Piece {

    Piece move(Position to, Position from, ChessBoard chessBoard);
}
