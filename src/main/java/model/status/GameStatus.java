package model.status;

import java.util.List;
import model.GameBoard;

public interface GameStatus {

    GameStatus play(List<String> command, GameBoard gameBoard);

    boolean isRunning();
}
