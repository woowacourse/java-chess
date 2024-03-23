package controller;

import domain.Chess;
import domain.command.Command;
import domain.position.Position;
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
        Chess chess = initChess();
        manage(chess);
    }

    private void manage(Chess chess) { // TODO: indent 1로 줄이기
        try {
            outputView.printTurn(chess.getTurn());
            String rawCommand = requestCommand();
            Command command = CommandInput.asCommand(rawCommand);
            if (command.isEnd()) {
                return;
            }
            if (command.isStart()) {
                chess = initChess();
                manage(chess);
                return;
            }
            List<String> rawPositions = CommandInput.extractPositions(rawCommand);
            playChess(chess, rawPositions);
            outputView.printBoard(chess.getBoard());
            manage(chess);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            manage(chess);
        }
    }

    private Chess initChess() {
        Chess chess = new Chess();
        outputView.printBoard(chess.getBoard());
        return chess;
    }

    private void playChess(Chess chess, List<String> rawPositions) {
        Position sourcePosition = Position.generate(rawPositions.get(0));
        Position targetPosition = Position.generate(rawPositions.get(1));
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
