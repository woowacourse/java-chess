package chess.domain.piece.maker;

import chess.domain.piece.Piece;

import java.util.List;

public interface PiecesGenerator {

    List<Piece> generate();
}
