package chess.domain;

import chess.domain.state.GameState;
import chess.domain.piece.Position;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGame {
    private GameState state;

    public ChessGame(GameState state) {
        this.state = state;
    }

    public void run() {
        OutputView.printStartView();

        if (requestFirstCommand() != Command.START) {
            return;
        }

        playRound();

        OutputView.printResult(state.findWinner().getName(),
                state.calculateWhiteScore(),
                state.calculateBlackScore());
    }

    private Command requestFirstCommand() {
        try {
            return Command.firstCommand(InputView.requestCommand());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return requestFirstCommand();
        }
    }

    private void playRound() {
        while (!state.isEnd()) {
            OutputView.printBoard(state.getBoard(), state.isBlackTurn());
            executeTurn();
        }
    }

    private void executeTurn() {
        try {
            executeCommand();
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
            OutputView.printBoard(state.getBoard(), state.isBlackTurn());
            executeTurn();
        }
    }

    private void executeCommand() {
        List<String> input = List.of(InputView.requestCommand().split(" "));
        if (Command.inGameCommand(input.get(0)) == Command.END) {
            state = state.terminate();
        }
        if (Command.inGameCommand(input.get(0)) == Command.STATUS) {
            OutputView.printScore(state.calculateWhiteScore(), state.calculateBlackScore());
        }
        if (Command.inGameCommand(input.get(0)) == Command.MOVE && input.size() == 3) {
            state = state.move(new Position(input.get(1)), new Position(input.get(2)));
        }
    }
}
