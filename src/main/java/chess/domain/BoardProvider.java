package chess.domain;

import chess.domain.piece.Piece;

import java.util.List;

public interface BoardProvider {

    List<Piece> getPieces();

    int getLineSize();
}
