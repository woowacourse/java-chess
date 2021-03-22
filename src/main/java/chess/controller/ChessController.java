package chess.controller;

import chess.domain.piece.PieceFactory;
import chess.domain.player.Round;
import chess.domain.state.StateFactory;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Queue;
import java.util.Scanner;

import static chess.view.Command.END;
import static chess.view.Command.START;

public class ChessController {
    private static final InputView INPUT = new InputView(new Scanner(System.in));

    public void run() {
        if (isStart(INPUT.inputStart())) {
            startChessGame();
        }
    }

    private void startChessGame() {
        Round round = new Round(StateFactory.initialization(PieceFactory.whitePieces()),
                StateFactory.initialization(PieceFactory.blackPieces()));

        while (true) try {
            inputCommand(round);
            break;
        } catch (RuntimeException runtimeException) {
            System.out.println("[EROOR]: " + runtimeException.getMessage());
        }
    }

    private void inputCommand(Round round) {
        String command = "";
        while (!isEnd(command)) {
            OutputView.showChessBoard(round.getBoard());
            Queue<String> commands = INPUT.inputCommand();
            command = commands.poll();
            round.move(command, commands.poll(), commands.poll());
        }
    }

    private boolean isStart(final String input) {
        return START.isSame(input);
    }

    private boolean isEnd(final String input) {
        return END.isSame(input);
    }
}
