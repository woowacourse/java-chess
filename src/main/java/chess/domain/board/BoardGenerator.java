package chess.domain.board;

import java.util.Map;

import chess.domain.piece.Piece;

public interface BoardGenerator {
    Map<Point, Piece> generate();
}
