package domain.command;

import domain.chessgame.ChessGame;
import java.util.List;

public interface Command {

    void execute(ChessGame chessGame, List<String> inputs);

    boolean isCommand(String command);

    boolean isStatusCommand();
}
