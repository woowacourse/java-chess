package chess;

import chess.Controller.ChessController;
import chess.Controller.command.Command;
import chess.Controller.command.ParsedCommand;
import chess.Controller.dto.PiecesDto;
import chess.Controller.dto.ScoreDto;
import chess.Controller.dto.StateDto;
import chess.console.view.InputView;
import chess.console.view.OutputView;

public class ConsoleApplication {

    public static void main(String[] args) {
        startGame();
    }

    private static void startGame() {
        final String userName = InputView.inputName();
        final ChessController chess = new ChessController();
        final int userId = chess.initGame(userName);
        StateDto state = chess.getCurrentStatus(userId);
        if (!isReady(state)) {
            OutputView.printBoard(chess.getCurrentBoardState(userId));
        }
        OutputView.printStartMessage();
        while (!isEnd(state)) {
            repeatTurn(chess, userId);
            state = chess.getCurrentStatus(userId);
        }
        chess.finishGame(userId);
    }

    private static boolean isReady(final StateDto stateDto) {
        return stateDto.getState().equals("READY");
    }

    private static boolean isEnd(final StateDto stateDto) {
        return stateDto.getState().equals("END");
    }

    private static void repeatTurn(final ChessController chess, final int userId) {
        try {
            operateOnce(chess, userId);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            repeatTurn(chess, userId);
        }
    }

    private static void operateOnce(final ChessController chessController, final int userId) {
        final ParsedCommand parsedCommand = new ParsedCommand(InputView.input());
        if (isCommandRelatedToScore(parsedCommand)) {
            final ScoreDto scoreDto = chessController.doActionAboutScore(parsedCommand, userId);
            OutputView.printStatus(scoreDto);
            return;
        }
        final PiecesDto piecesDto = chessController.doActionAboutPieces(parsedCommand, userId);
        OutputView.printBoard(piecesDto);
    }

    private static boolean isCommandRelatedToScore(final ParsedCommand parsedCommand) {
        final Command command = parsedCommand.getCommand();
        return command == Command.END || command == Command.STATUS;
    }
}
