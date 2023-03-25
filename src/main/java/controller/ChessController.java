package controller;

import controller.command.Command;
import controller.command.SystemBoot;
import view.OutputView;

public class ChessController {

    public void run() {
        OutputView.printStartMessage();
        Command command = new SystemBoot();
        command = command.execute();
        while (!command.isEnd()) {
            command = command.execute();
        }
    }
}
