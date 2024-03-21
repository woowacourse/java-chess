package controller;

import domain.Chess;
import domain.command.Command;
import domain.position.Position;
import domain.position.PositionGenerator;
import java.util.List;
import view.InputView;
import view.OutputView;
import view.mapper.CommandInput;

public class GameManager {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void start() {
        outputView.printStartNotice();
        List<String> rawCommand = inputView.readCommand();
        Command command = CommandInput.asCommand(rawCommand.get(0));
        if (command.isNotStart()) {
            return;
        }
        Chess chess = new Chess();
        outputView.printBoard(chess.getBoard());
        manage(chess);
    }

    private void manage(Chess chess) {
        List<String> rawCommand = inputView.readCommand();
        Command command = CommandInput.asCommand(rawCommand.get(0));
        if (command.isEnd()) {
            return;
        }
        PositionGenerator positionGenerator = new PositionGenerator();
        Position sourcePosition = positionGenerator.generate(rawCommand.get(1));
        Position targetPosition = positionGenerator.generate(rawCommand.get(2));
        chess.play(sourcePosition, targetPosition);
        outputView.printBoard(chess.getBoard());
        manage(chess);
    }
}
