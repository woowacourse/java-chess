package chess.domain.refactorPiece;

import chess.domain.refactorBoard.ChessBoard;
import chess.domain.refactorPosition.Position;

public interface Article {

    Article move(Position from, Position to, ChessBoard chessBoard);

    boolean isMovable(Position from, Position to, ChessBoard chessBoard);
}
