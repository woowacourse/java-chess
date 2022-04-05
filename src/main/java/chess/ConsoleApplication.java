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

    private int userId;

    public static void main(String[] args) {
        final ConsoleApplication chess = new ConsoleApplication();
        chess.startGame();
    }

    private void startGame() {
        final String userName = InputView.inputName();
        final ChessController chess = new ChessController();
        userId = chess.initGame(userName);
        OutputView.printStartMessage();
        StateDto state = chess.getCurrentStatus(userId);
        while (!isEnd(state)) {
            repeatTurn(chess);
            state = chess.getCurrentStatus(userId);
        }
        chess.finishGame(userId);
    }

    private boolean isEnd(final StateDto stateDto) {
        return stateDto.getState().equals("END");
    }

    private void repeatTurn(final ChessController chess) {
        try {
            operateOnce(chess);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            repeatTurn(chess);
        }
    }

    private void operateOnce(final ChessController chessController) {
        final ParsedCommand parsedCommand = new ParsedCommand(InputView.input());
        if (isCommandRelatedToScore(parsedCommand)) {
            final ScoreDto scoreDto = chessController.doActionAboutScore(parsedCommand, userId);
            OutputView.printStatus(scoreDto);
            return;
        }
        final PiecesDto piecesDto = chessController.doActionAboutPieces(parsedCommand, userId);
        OutputView.printBoard(piecesDto);
    }

    private boolean isCommandRelatedToScore(final ParsedCommand parsedCommand) {
        final Command command = parsedCommand.getCommand();
        return command == Command.END || command == Command.STATUS;
    }
}
