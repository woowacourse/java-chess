package controller;

import controller.status.ChessProgramStatus;
import controller.status.StartingStatus;
import view.OutputView;

import java.sql.SQLException;

public class ChessFrontController {

    private final CommandRouter commandRouter;
    private ChessProgramStatus status;

    public ChessFrontController() {
        this.commandRouter = new CommandRouter();
        this.status = new StartingStatus();
    }

    public void run() throws SQLException {
        while (status.isNotEnd()) {
            try {
                final String command = status.readCommand();
                status = commandRouter.execute(command, status);
            } catch (final IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }
}
