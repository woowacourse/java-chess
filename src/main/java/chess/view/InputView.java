package chess.view;

import chess.controller.command.chess.ChessGameCommand;
import chess.controller.command.execute.ExecuteCommand;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public ChessGameCommand readChessGameCommand() {
        return ChessGameCommandFactory.getInstance(scanner.nextLine());
    }

    public ExecuteCommand readExecuteCommand() {
        return ExecuteCommandFactory.getInstance(scanner.nextLine());
    }
}
