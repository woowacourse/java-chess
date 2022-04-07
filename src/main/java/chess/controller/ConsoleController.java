package chess.controller;

import chess.domain.ChessGame;
import chess.domain.Command;
import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.state.WhiteTurn;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ConsoleController {
    public void run() {
        ChessGame game = new ChessGame(new WhiteTurn(new Board(BoardInitializer.initBoard())));
        OutputView.printStartView();
        if (requestFirstCommand() != Command.START) {
            return;
        }
        playRound(game);
        OutputView.printResult(game.getWinnerName(),
                game.getWhiteScore(),
                game.getBlackScore());
    }

    private Command requestFirstCommand() {
        try {
            return Command.firstCommand(InputView.requestCommand());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return requestFirstCommand();
        }
    }

    private void playRound(ChessGame game) {
        while (!game.isEnd()) {
            OutputView.printBoard(game.getBoard(), game.isBlackTurn());
            executeTurn(game);
        }
    }

    private void executeTurn(ChessGame game) {
        try {
            executeCommand(game);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
            OutputView.printBoard(game.getBoard(), game.isBlackTurn());
            executeTurn(game);
        }
    }

    private void executeCommand(ChessGame game) {
        List<String> input = List.of(InputView.requestCommand().split(" "));
        if (Command.inGameCommand(input.get(0)) == Command.END) {
            game.terminate();
        }
        if (Command.inGameCommand(input.get(0)) == Command.STATUS) {
            OutputView.printScore(game.getWhiteScore(), game.getBlackScore());
        }
        if (Command.inGameCommand(input.get(0)) == Command.MOVE && input.size() == 3) {
            game.move(input.get(1), input.get(2));
        }
    }
}
