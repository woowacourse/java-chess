package chess.model.service;

import chess.model.domain.board.Score;
import chess.model.domain.piece.Color;
import chess.model.domain.piece.Piece;
import chess.model.domain.position.Position;
import java.util.List;
import java.util.Map;

public interface State {

    State start();

    State move(final Position from, final Position to);

    Map<Color, Score> status();

    State end();

    Map<Position, Piece> getBoard();

    State load(final long id);

    List<Long> findAllId();
}
