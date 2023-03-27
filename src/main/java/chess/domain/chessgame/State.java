package chess.domain.chessgame;

import chess.domain.board.Score;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
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
