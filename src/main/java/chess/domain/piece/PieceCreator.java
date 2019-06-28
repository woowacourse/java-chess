package chess.domain.piece;

import chess.domain.Aliance;
import chess.domain.PieceValue;

public interface PieceCreator {
    Piece create(Aliance aliance, PieceValue pieceValue);
}
