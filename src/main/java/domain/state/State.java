package domain.state;

import domain.Board;
import domain.piece.objects.Piece;
import domain.piece.position.Position;
import domain.score.Score;

import java.util.Map;

public interface State {

    State run(Map<Position, Piece> pieces);

    State move(Position start, Position end);

    State finish();

    boolean isRunning();

    Map<Boolean, Score> pieceScore();

    Board getBoard();
}
