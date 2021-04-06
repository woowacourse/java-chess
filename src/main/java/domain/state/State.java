package domain.state;

import domain.Board;
import domain.piece.position.Position;
import domain.score.Score;

public interface State {

    State run();

    State move(Position start, Position end);

    State finish();

    boolean isEnd();

    Board getBoard();

    boolean getTurn();

    Score blackScore();

    Score whiteScore();
}
