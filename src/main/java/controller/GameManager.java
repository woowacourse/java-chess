package controller;

import domain.Chess;
import domain.command.Command;
import domain.position.Position;
import domain.position.PositionGenerator;
import java.util.Arrays;
import java.util.List;
import view.InputView;
import view.OutputView;
import view.mapper.CommandInput;

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

    private void manage(Chess chess) { // TODO: indent 1로 줄이기
        try {
            outputView.printTurn(chess.getTurn());
            String rawCommand = requestCommand();
            Command command = CommandInput.asCommand(rawCommand);
            if (command.isStart()) {
                chess = new Chess();
                outputView.printBoard(chess.getBoard());
                manage(chess);
                return;
            }
            if (command.isEnd()) {
                return;
            }
            List<String> moveCommands = Arrays.stream(rawCommand.split(" ")).toList();
            playChess(chess, moveCommands);
            outputView.printBoard(chess.getBoard());
            manage(chess);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            manage(chess);
        }
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
