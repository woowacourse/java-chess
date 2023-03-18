package chess.domain.board.maker;

import chess.domain.piece.Piece;

import java.util.Collections;
import java.util.List;

public class EmptyPieceGenerator implements PiecesGenerator {

    @Override
    public List<Piece> generate() {
        return Collections.emptyList();
    }
}
