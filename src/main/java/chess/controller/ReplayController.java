package chess.controller;

import chess.domain.game.ChessGame;
import chess.domain.game.User;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ReplayController {

    private final LoadGameController loadGameController = new LoadGameController();

    public void replayGame(User user) {
        String userId = user.getId();
        String gameId = loadGameController.selectGame(userId);
        if (gameId == null) {
            OutputView.printNoGameExistMessage();
            return;
        }
        showReplay(gameId);
    }

    private void showReplay(String gameId) {
        int lastTurn = loadGameController.getLastTurn(gameId);
        OutputView.printReplayCommandMessage();
        int turn = 0;
        while (turn <= lastTurn && turn != -1) {
            printTurnStatus(gameId, turn);
            turn = getTurnByCommand(turn);
        }
        OutputView.printReplayEndMessage();
    }

    private void printTurnStatus(String gameId, int turn) {
        ChessGame chessGame = loadGameController.getGameById(gameId, turn);
        OutputView.printTurn(turn);
        OutputView.printGameStatus(chessGame.getGameStatus());
    }

    private int getTurnByCommand(int turn) {
        Command command = readReplayCommand();
        if (command == Command.BACK) {
            return decreaseTurnWhenNotOne(turn);
        }
        if (command == Command.NEXT) {
            return turn + 1;
        }
        return -1;
    }

    private int decreaseTurnWhenNotOne(int turn) {
        if (turn == 0) {
            OutputView.printErrorMessage("현재가 초기 상황입니다.");
            return 0;
        }
        return turn - 1;
    }

    private Command readReplayCommand() {
        Command command = InputView.readCommand();
        try {
            validateReplayCommand(command);
            return command;
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return readReplayCommand();
        }
    }

    private void validateReplayCommand(Command command) {
        if (command == Command.NEXT || command == Command.BACK || command == Command.END) {
            return;
        }
        throw new IllegalArgumentException("다음 수를 보려면 next, 이전 수로 돌아가려면 back, 리플레이를 종료하려면 end를 입력하세요.");
    }
}
