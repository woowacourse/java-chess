package chess.controller;

import chess.domain.ChessGame;
import chess.domain.command.CommandInput;
import chess.domain.command.Commands;
import chess.domain.piece.Team;
import chess.exception.GameIsNotStartException;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.List;

public class ChessController {
    private static final String DELIMITER = " ";
    private static final String STATUS = "status";
    private static final String END = "end";
    private static final int ONLY_COMMAND = 1;
    private static final int OPTION_TARGET = 1;
    private static final int OPTION_DESTINATION = 2;

    public void run(ChessGame chessGame, Commands commands) {
        boolean isPlaying = true;

        OutputView.printManual();
        while (isPlaying) {
            isPlaying = playGame(chessGame, commands);
        }
    }

    public boolean playGame(ChessGame chessGame, Commands commands) {
        boolean play = true;
        try {
            CommandInput commandInput = createCommandInput();
            String command = commands.execute(chessGame, commandInput);
            play = isPlay(command);
            checkGameStart(chessGame, play);
            OutputView.printBoard(chessGame.getBoard());
            showStatus(command, chessGame);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return play;
    }

    private CommandInput createCommandInput() {
        String input = InputView.inputCommand();
        List<String> inputs = Arrays.asList(input.split(DELIMITER));
        String command = inputs.get(0);

        if (inputs.size() == ONLY_COMMAND) {
            return new CommandInput(command);
        }
        return new CommandInput(command, Arrays.asList(inputs.get(OPTION_TARGET), inputs.get(OPTION_DESTINATION)));
    }

    private void checkGameStart(ChessGame chessGame, boolean isPlay) {
        if (chessGame.isBeforeStart() && isPlay) {
            throw new GameIsNotStartException();
        }
    }

    private void showStatus(String command, ChessGame chessGame) {
        if (command.equals(STATUS)) {
            OutputView.printStatus(chessGame.status(Team.BLACK), chessGame.status(Team.WHITE));
        }
    }

    private boolean isPlay(String command) {
        return !command.equals(END);
    }
}