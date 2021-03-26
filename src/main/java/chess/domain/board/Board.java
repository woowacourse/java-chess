package chess.domain.board;

import chess.domain.order.MoveOrder;
import chess.domain.order.MoveResult;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.RealPiece;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;

public interface Board {
    Piece getPieceByPosition(Position position);

    RealPiece getRealPieceByPosition(Position position);

    MoveResult move(MoveOrder moveOrder);
    
    Map<Color, Double> getScoreMap();

    List<Piece> getRoute(Position from, Position to);

    boolean hasPiece(Position position);
}
