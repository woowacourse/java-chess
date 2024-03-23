package chess.domain.state;

import java.util.List;

public interface GameState {

    GameState play(List<String> inputCommand);

    boolean isEnd();
}
