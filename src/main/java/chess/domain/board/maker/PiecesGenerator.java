package chess.domain.board.maker;

import chess.domain.piece.Piece;

import java.util.List;

public interface PiecesGenerator {

    List<Piece> generate();
}
