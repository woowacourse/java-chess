package chess.controller;

import chess.domain.board.Score;
import chess.domain.piece.Color;
import chess.service.ChessService;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Map;
import java.util.function.BiConsumer;

public class ChessController {

    private final Map<GameCommand, BiConsumer<ChessService, CommandRequest>> commandMapper = Map.of(
            GameCommand.START, (chessService, ignored) -> start(chessService),
            GameCommand.MOVE, this::move,
            GameCommand.STATUS, (chessService, ignored) -> status(chessService),
            GameCommand.LOAD, this::load,
            GameCommand.END, (chessService, ignored) -> end(chessService)
    );

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        final ChessService chessService = new ChessService();
        outputView.printGameStartInfo(chessService.findAllId());

        while (!chessService.isEnd()) {
            playChess(chessService);
            printBoard(chessService);
        }
    }

    private void printBoard(final ChessService chessService) {
        if (!chessService.isEnd()) {
            outputView.printBoard(chessService.getBoard());
        }
    }

    private void playChess(final ChessService chessService) {
        final CommandRequest commandRequest = readRequest();
        try {
            commandMapper.get(commandRequest.getGameCommand())
                    .accept(chessService, commandRequest);
        } catch (Exception e) {
            outputView.printExceptionMessage(e);
            playChess(chessService);
        }
    }

    private void start(final ChessService chessService) {
        chessService.start();
    }

    private void load(final ChessService chessService, final CommandRequest request) {
        chessService.load(request.getId());
    }

    private void move(final ChessService chessService, final CommandRequest request) {
        chessService.move(request.getFrom(), request.getTo());
    }

    private void status(final ChessService chessService) {
        final Map<Color, Score> status = chessService.status();
        outputView.printStatus(status);
    }

    private void end(final ChessService chessService) {
        chessService.end();
    }

    private CommandRequest readRequest() {
        try {
            return inputView.readRequest();
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e);
            return readRequest();
        }
    }
}
