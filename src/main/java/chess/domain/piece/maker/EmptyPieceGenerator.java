package chess.domain.piece.maker;

import chess.domain.piece.Piece;

import java.util.Collections;
import java.util.Set;

public class EmptyPieceGenerator implements PiecesGenerator {

    @Override
    public Set<Piece> generate() {
        return Collections.emptySet();
    }
}
