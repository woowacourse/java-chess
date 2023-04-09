package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.CommandMapper;
import chess.domain.chessgame.ChessGame;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessGameController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ChessGameService chessGameService;

    public ChessGameController(final InputView inputView, final OutputView outputView, final ChessGameService chessGameService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessGameService = chessGameService;
    }

    public void run() {
        outputView.printInstructionMessage();

        final CommandMapper commandMapper = CommandMapper.getInstance();
        Command command = Command.emptyCommand();
        ChessGame chessGame = null;

        while (!command.isGameEnd()) {
            try {
                final List<String> commandWithOptions = inputView.readCommand();
                command = commandMapper.getCommand(commandWithOptions);
                chessGame = command.execute(inputView, outputView, chessGameService, chessGame);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
