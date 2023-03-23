package chess.domain.piece.maker;

import chess.domain.piece.Piece;

import java.util.Set;

public interface PiecesGenerator {

    Set<Piece> generate();
}
