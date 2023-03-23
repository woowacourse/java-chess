package domain.board;

import domain.piece.Piece;
import domain.position.Position;

import java.util.Map;

interface ChessAlignment {
    Map<Position, Piece> makeInitialPieces();
}
