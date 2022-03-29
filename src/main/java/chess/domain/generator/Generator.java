package chess.domain.generator;

import chess.domain.piece.Piece;
import java.util.List;

public interface Generator {

    int PAWN_COUNT_PER_PLAYER = 8;

    List<Piece> generate();
}
