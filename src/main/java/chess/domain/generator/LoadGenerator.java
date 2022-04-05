package chess.domain.generator;

import chess.domain.piece.Piece;

import java.util.List;

public interface LoadGenerator {

    List<Piece> generate();
}
