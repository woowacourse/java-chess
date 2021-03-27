package chess.domain.board;

import chess.domain.order.MoveResult;
import chess.domain.order.MoveRoute;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Map;

public interface Board {
    Piece getPieceByPosition(Position position);

    MoveResult move(MoveRoute moveRoute);
    
    Map<Color, Double> getScoreMap();

    boolean hasPiece(Position position);

    MoveRoute createMoveRoute(Position from, Position to);
}
