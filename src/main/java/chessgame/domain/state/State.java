package chessgame.domain.state;

import chessgame.domain.Board;
import chessgame.domain.Command;

public interface State {
    boolean isEnd();
    State run(Command command, Board board);
}
