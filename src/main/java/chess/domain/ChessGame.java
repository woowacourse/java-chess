package chess.domain;

import chess.domain.board.Board;
import chess.domain.piece.Position;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGame {

    public void run() {
        OutputView.printStartView();

        if (requestFirstCommand() != Command.START) {
            return;
        }

        Board board = new Board();
        playRound(board);

        OutputView.printResult(board);
    }

    private Command requestFirstCommand() {
        try {
            return Command.firstCommand(InputView.requestCommand());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return requestFirstCommand();
        }
    }

    private void playRound(Board board) {
        while (!board.isEnd()) {
            OutputView.printBoard(board);
            executeTurn(board);
        }
    }

    private void executeTurn(Board board) {
        try {
            executeCommand(board);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
            OutputView.printBoard(board);
            executeTurn(board);
        }
    }

    private void executeCommand(Board board) {
        List<String> input = List.of(InputView.requestCommand().split(" "));
        if (Command.inGameCommand(input.get(0)) == Command.END) {
            board.terminate();
        }
        if (Command.inGameCommand(input.get(0)) == Command.STATUS) {
            OutputView.printScore(board);
        }
        if (Command.inGameCommand(input.get(0)) == Command.MOVE && input.size() == 3) {
            board.move(new Position(input.get(1)), new Position(input.get(2)));
        }
    }
}
