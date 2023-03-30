package chess.controller;

import chess.domain.Color;
import chess.domain.CommandAction;
import chess.dto.response.PiecesResponse;
import chess.service.GameService;
import chess.ui.InputView;
import chess.ui.OutputView;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public final class ChessGameController {

    private final GameService gameService;

    private final CommandAction commandAction = new CommandAction(Map.of(
            Command.START, this::start,
            Command.MOVE, this::move,
            Command.END, this::end,
            Command.STATUS, this::status)
    );

    public ChessGameController(final GameService gameService) {
        this.gameService = gameService;
    }

    public void run() {
        OutputView.printStartGame();
        CommandManagement commandWithArguments;
        do {
            commandWithArguments = readCommand(InputView::getCommands);
            commandAction.execute(commandWithArguments);
        } while (commandWithArguments.isNotEnd() && gameService.everyKingAlive());
        finishGame();
    }

    private void finishGame() {
        if (!gameService.everyKingAlive()) {
            OutputView.printWinner(gameService.getWinnerPlayerColor());
            gameService.updateTurn(Color.WHITE);
        }
    }

    private CommandManagement readCommand(Supplier<List<String>> commandReader) {
        try {
            List<String> arguments = commandReader.get();
            return new CommandManagement(arguments);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return readCommand(commandReader);
        }
    }

    private void start(final CommandManagement commandManagement) {
        PiecesResponse piecesResponse = new PiecesResponse(gameService.getPiecesByColor(Color.WHITE), gameService.getPiecesByColor(Color.BLACK));
        OutputView.printInitializedChessBoard(piecesResponse);
    }

    private void status(final CommandManagement commandManagement) {
        OutputView.printStatus(gameService.getScore());
    }

    private void move(final CommandManagement commandManagement) {
        String inputMovablePiece = commandManagement.getMovablePiece();
        String inputTargetPosition = commandManagement.getTargetPosition();

        try {
            gameService.movePiece(inputMovablePiece, inputTargetPosition);
            OutputView.printChessBoardStatus(
                    new PiecesResponse(gameService.getPiecesByColor(Color.WHITE), gameService.getPiecesByColor(Color.BLACK)));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void end(final CommandManagement commandManagement) {
    }

}
