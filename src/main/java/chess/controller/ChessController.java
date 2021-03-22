package chess.controller;

import chess.domain.command.Command;
import chess.domain.command.CommandFactory;
import chess.domain.piece.PieceFactory;
import chess.domain.player.Round;
import chess.domain.state.StateFactory;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Scanner;

public class ChessController {
    private static final InputView INPUT = new InputView(new Scanner(System.in));

    public void run() {
        Command command = CommandFactory.initialCommand(INPUT.inputStart());
        if (command.isStart()) {
            startChessGame(command);
        }
    }

    private void startChessGame(final Command command) {
        Round round = new Round(StateFactory.initialization(PieceFactory.whitePieces()),
                StateFactory.initialization(PieceFactory.blackPieces()), command);

        while (true) try {
            inputCommand(round);
            break;
        } catch (RuntimeException runtimeException) {
            System.out.println("[EROOR]: " + runtimeException.getMessage());
        }
    }

    private void inputCommand(final Round round) {
        while (!round.isEnd()) {
            OutputView.showChessBoard(round.getBoard());
            round.execute(INPUT.inputCommand());
        }
    }
}
