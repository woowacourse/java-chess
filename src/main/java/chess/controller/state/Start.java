package chess.controller.state;

import chess.controller.command.Command;
import chess.controller.command.Commands;
import chess.controller.command.EndCommand;
import chess.controller.command.MoveCommand;
import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;
import java.util.Optional;

public class Start implements State {

    private final ChessGame chessGame;

    public Start() {
        this.chessGame = new ChessGame();
    }

    @Override
    public State execute(InputView inputView, OutputView outputView) {
        outputView.printBoard(new BoardDTO(chessGame.getBoard()));
        return inputCommand(inputView, outputView);
    }

    private State inputCommand(InputView inputView, OutputView outputView) {
        List<String> input = inputView.inputCommand();

        Commands commands = new Commands(List.of(new MoveCommand(), new EndCommand()));

        try {
            Command command = commands.findCommand(input.get(0));

            return command.execute(Optional.of(chessGame), input);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMesage(e);
            return inputCommand(inputView, outputView);
        }
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
