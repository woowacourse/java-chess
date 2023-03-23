package chess.service;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public interface State {

    State start();

    State move(final Position from, final Position to);

    State end();

    Map<Position, Piece> getBoard();
}
