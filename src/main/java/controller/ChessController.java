package controller;

import service.ChessService;
import view.InputView;
import view.OutputView;

public class ChessController {
    private final ChessService chessService = new ChessService();

    public void run() {
        OutputView.printChessInfo();

        play();
    }

    private void play() {
        while (true) {
            String commandRequest = InputView.requestCommand();
            String[] inputs = commandRequest.split(" ");
            validateCommandRequest(inputs);
            chessService.execute(inputs);
            if (!chessService.isOngoing()) {
                break;
            }
            OutputView.printChessBoard(chessService.getChessBoard());
        }
    }

    private void validateCommandRequest(String[] inputs) {
        String command = inputs[0];
        validateStartEndCommand(inputs, command);
        validateMoveCommand(inputs, command);
    }

    private static void validateMoveCommand(String[] inputs, String command) {
        if (Command.findRunCommand(command).equals(Command.MOVE)) {
            if (inputs.length != 3) {
                throw new IllegalArgumentException("잘못된 입력입니다.");
            }
            if (inputs[1].length() != 2 || inputs[2].length() != 2) {
                throw new IllegalArgumentException("잘못된 입력입니다.");
            }
        }
    }

    private static void validateStartEndCommand(String[] inputs, String command) {
        if (Command.findRunCommand(command).equals(Command.START)
                || Command.findRunCommand(command).equals(Command.END)) {
            if (inputs.length != 1) {
                throw new IllegalArgumentException("잘못된 입력입니다.");
            }
        }
    }
}
