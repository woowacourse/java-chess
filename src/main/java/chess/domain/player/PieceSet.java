package chess.domain.player;

import chess.domain.piece.Piece;

import java.util.Iterator;

public interface PieceSet {

    Score calculateScore();

    Iterator<Piece> values();
}
