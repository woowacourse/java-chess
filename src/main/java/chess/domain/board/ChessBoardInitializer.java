package chess.domain.board;

import chess.domain.Position;
import chess.domain.piece.ChessPiece;
import java.util.Map;

public interface ChessBoardInitializer {

    Map<Position, ChessPiece> initialize(ChessPiece blackKing, ChessPiece whiteKing);
}
