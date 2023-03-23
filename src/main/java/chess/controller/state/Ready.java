package chess.controller.state;

import chess.controller.command.Command;
import chess.controller.command.Commands;
import chess.controller.command.EndCommand;
import chess.controller.command.StartCommand;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;
import java.util.Optional;

public class Ready implements State {

    @Override
    public State execute(InputView inputView, OutputView outputView) {
        outputView.startMessage();

        Commands commands = new Commands(List.of(new StartCommand(), new EndCommand()));

        List<String> input = inputView.inputCommand();

        Command command = commands.findCommand(input.get(0));
        return command.execute(Optional.empty(), input);
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
