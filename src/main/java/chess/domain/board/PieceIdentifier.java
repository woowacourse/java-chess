package chess.domain.board;

import chess.domain.piece.Piece;

@FunctionalInterface
public interface PieceIdentifier {

    boolean identify(Piece piece);
}
