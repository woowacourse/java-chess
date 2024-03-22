package model.status;

import java.util.List;
import model.ChessBoard;

public interface GameStatus {

    GameStatus play(List<String> command, ChessBoard chessBoard);

    boolean isRunning();
}
