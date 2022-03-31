package chess.domain.board;

import chess.domain.piece.Piece;

import java.util.Map;

public interface Builder {
    Map<Position, Piece> build();
}
