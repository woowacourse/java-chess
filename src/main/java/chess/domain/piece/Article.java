package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.attribute.Color;
import chess.domain.board.ChessBoard;

public interface Article {

    Article move(Position from, Position to, ChessBoard chessBoard);

    boolean isMovable(Position from, Position to, ChessBoard chessBoard);

    Color getColor();

    String getName();

    boolean isKing();

    boolean isPawn();
}
