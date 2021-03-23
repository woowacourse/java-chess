package chess.domain.state;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.Map;

public interface State {

    boolean isSameColor(final Piece piece);

    boolean isFinish();

    Color winner();

    State init();

    double score(Color color);

    Map<Position, Piece> pieces();

    State movePiece(final Position sourcePosition, final Position targetPosition);
}
