package chess.domain.piece.type.pawn.state;

import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;

public interface PawnState {

    PawnState next(final PiecePosition piecePosition);

    void validatePath(final Path path);
}
