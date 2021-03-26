package chess.domain.piece.strategy;

import chess.domain.position.Notation;

public interface PieceStrategy {

    PieceRange pieceRangeFrom(Notation notation);

    boolean isPawn();

    boolean isKing();

    double score();
}
