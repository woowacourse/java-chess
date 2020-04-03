package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.position.Position;

import java.util.Map;

public interface Board {
    Board movePiece();

    Piece getPiece(Position position);

    Map<Position, Piece> getPieces();

    boolean isNotFinished();

    Result concludeResult();
}
