package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.Position;

public interface Piece {

    Piece move(Position source, Position target, ChessBoard chessBoard);

    String convertedName();

    boolean isSameColor(Color color);

    boolean isSameTeamPiece(Piece piece);

    boolean isMovable(Position source, Position target, ChessBoard chessBoard);

    double score();

    Color color();

    boolean isPawn();

    boolean isKing();
}
