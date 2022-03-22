package chess.domain;

import chess.domain.piece.Piece;
import java.util.List;

public interface PiecesGenerator {

    List<Piece> generate();
}
