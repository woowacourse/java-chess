package chess.domain.state;

import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.position.Position;
import chess.domain.position.Source;
import chess.domain.position.Target;

import java.util.Optional;

public interface State {
    State move(final Source source, final Target target, final State anotherState);

    State toRunningState(final State anotherState);

    Optional<Piece> findPiece(final Position position);

    Pieces pieces();

    boolean isFinish();

    boolean isKing(final Position position);

    void removePiece(final Position position);
}
