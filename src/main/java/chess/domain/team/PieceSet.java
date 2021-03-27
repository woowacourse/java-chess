package chess.domain.team;

import chess.domain.piece.Piece;
import java.util.Iterator;

public interface PieceSet {

    Score calculateScore();

    Iterator<Piece> values();

}
