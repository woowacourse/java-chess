package controller;

import domain.Chess;
import domain.command.Command;
import domain.position.Position;
import domain.position.PositionGenerator;
import view.InputView;
import view.OutputView;
import view.mapper.CommandInput;

import java.util.Arrays;
import java.util.List;

public class GameManager {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void start() {
        outputView.printStartNotice();
        String rawCommand = requestCommand();
        Command command = CommandInput.asCommand(rawCommand);
        if (command.isNotStart()) {
            return;
        }
        Chess chess = new Chess();
        outputView.printBoard(chess.getBoard());
        manage(chess);
    }

    private void manage(Chess chess) {
        String rawCommand = requestCommand();
        Command command = CommandInput.asCommand(rawCommand);
        if (isNotMove(command)) {
            return;
        }
        List<String> moveCommands = Arrays.stream(rawCommand.split(" ")).toList();
        playChess(chess, moveCommands);
        outputView.printBoard(chess.getBoard());
        manage(chess);
    }

    private boolean isNotMove(Command command) {
        if (command.isMove()) {
            return false;
        }
        if (command.isStart()) {
            start();
        }
        return true;
    }

    private void playChess(Chess chess, List<String> moveTokens) {
        PositionGenerator positionGenerator = new PositionGenerator();
        Position sourcePosition = positionGenerator.generate(moveTokens.get(1));
        Position targetPosition = positionGenerator.generate(moveTokens.get(2));
        chess.play(sourcePosition, targetPosition);
    }

    private String requestCommand() {
        try {
            return inputView.readCommand();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return requestCommand();
        }
    }
}
