package chess.board;

import chess.board.piece.Piece;

import java.util.Map;

public interface BoardGenerator {
    Map<Coordinate, Piece> generate();
}
