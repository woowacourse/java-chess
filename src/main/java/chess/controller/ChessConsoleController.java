package chess.controller;

import chess.domain.Command;
import chess.domain.game.ChessGame;
import chess.domain.position.Position;
import chess.domain.result.Score;
import chess.dto.console.BoardResponseDto;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessConsoleController {

    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    public void run() {
        OutputView.printStartMessage();
        final ChessGame chessGame = new ChessGame();
        do {
            runTurn(chessGame);
        } while (chessGame.isNotEnded());
    }

    private void runTurn(final ChessGame chessGame) {
        try {
            printTurnMessage(chessGame);
            executeCommand(chessGame, InputView.requestCommands());
        } catch (final IllegalArgumentException | IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void printTurnMessage(final ChessGame chessGame) {
        if (chessGame.isStarted()) {
            OutputView.printTurnMessage(chessGame.getTurnName());
        }
    }

    private void executeCommand(final ChessGame chessGame, final List<String> commands) {
        final Command command = Command.from(commands.get(COMMAND_INDEX));
        if (command == Command.START) {
            start(chessGame);
        }
        if (command == Command.END) {
            end(chessGame);
        }
        if (command == Command.MOVE) {
            move(chessGame, commands);
        }
        if (command == Command.STATUS) {
            status(chessGame);
        }
    }

    private void start(final ChessGame chessGame) {
        OutputView.printBoard(new BoardResponseDto(chessGame.getBoard()));
        chessGame.start();
    }

    private void end(final ChessGame chessGame) {
        chessGame.end();
    }

    private static void move(final ChessGame chessGame, final List<String> commands) {
        final Position from = Position.from(toPosition(commands, SOURCE_INDEX));
        final Position to = Position.from(toPosition(commands, TARGET_INDEX));
        chessGame.move(from, to);
        OutputView.printNewLine();
        OutputView.printBoard(new BoardResponseDto(chessGame.getBoard()));
    }

    private static String toPosition(final List<String> commands, final int index) {
        try {
            return commands.get(index);
        } catch (IndexOutOfBoundsException exception) {
            throw new IllegalArgumentException("[ERROR] source 위치와 target 위치를 입력해주세요.");
        }
    }

    private void status(final ChessGame chessGame) {
        chessGame.status();

        final Score myScore = chessGame.calculateMyScore();
        final Score opponentScore = chessGame.calculateOpponentScore();

        OutputView.printNewLine();
        OutputView.printScore(chessGame.getTurnName(), myScore.getValue());
        OutputView.printScore(chessGame.getOppositeTurnName(), opponentScore.getValue());
        OutputView.printResult(chessGame.getTurnName(), myScore.decideResult(opponentScore).getName());
    }
}
