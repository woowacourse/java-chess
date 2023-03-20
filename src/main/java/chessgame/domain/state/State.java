package chessgame.domain.state;

import chessgame.domain.Board;
import chessgame.domain.Command;

public interface State {
    boolean isEnd();
    State click(Command command, Board board);
}
