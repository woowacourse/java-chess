package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public interface Board {

    Board initialize();

    boolean isInitialized();

    boolean isEnd();

    Board move(final String source, final String target);

    double score(final Color color);

    Map<Position, Piece> getBoard();
}
