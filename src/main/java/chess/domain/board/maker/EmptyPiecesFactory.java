package chess.domain.board.maker;

import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.List;

public class EmptyPiecesFactory implements PiecesFactory {

    @Override
    public List<Piece> generate() {
        return new ArrayList<>();
    }
}
