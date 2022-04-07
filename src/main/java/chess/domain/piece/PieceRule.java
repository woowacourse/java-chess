package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.Position;

public interface PieceRule {

    PieceRule move(Position source, Position target, ChessBoard chessboard);

    String convertedName(Color color);

    double score();

    boolean isPawn();

    boolean isKing();

    String name();
}
