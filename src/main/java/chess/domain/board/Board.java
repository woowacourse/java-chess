package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.position.MovingFlow;
import chess.domain.piece.position.Position;

import java.util.Map;

public interface Board {
    Board movePiece(MovingFlow movingFlow);

    Piece getPiece(Position position);

    Map<Position, Piece> getPieces();

    boolean isNotFinished();

    Result concludeResult();
}
