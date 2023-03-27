package chess.controller;

import chess.domain.board.Score;
import chess.domain.chessgame.ChessGame;
import chess.domain.piece.Color;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Map;
import java.util.function.BiConsumer;

public class ChessController {

    private final Map<GameCommand, BiConsumer<ChessGame, CommandRequest>> commandMapper = Map.of(
            GameCommand.START, (chessGame, ignored) -> start(chessGame),
            GameCommand.MOVE, this::move,
            GameCommand.STATUS, (chessGame, ignored) -> status(chessGame),
            GameCommand.LOAD, this::load,
            GameCommand.END, (chessGame, ignored) -> end(chessGame)
    );

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        final ChessGame chessGame = new ChessGame();
        outputView.printGameStartInfo(chessGame.findAllId());

        while (!chessGame.isEnd()) {
            playChess(chessGame);
            printBoard(chessGame);
        }
    }

    private void printBoard(final ChessGame chessGame) {
        if (!chessGame.isEnd()) {
            outputView.printBoard(chessGame.getBoard());
        }
    }

    private void playChess(final ChessGame chessGame) {
        final CommandRequest commandRequest = readRequest();
        try {
            commandMapper.get(commandRequest.getGameCommand())
                    .accept(chessGame, commandRequest);
        } catch (Exception e) {
            outputView.printExceptionMessage(e);
            playChess(chessGame);
        }
    }

    private void start(final ChessGame chessGame) {
        chessGame.start();
    }

    private void load(final ChessGame chessGame, final CommandRequest request) {
        chessGame.load(request.getId());
    }

    private void move(final ChessGame chessGame, final CommandRequest request) {
        chessGame.move(request.getFrom(), request.getTo());
    }

    private void status(final ChessGame chessGame) {
        final Map<Color, Score> status = chessGame.status();
        outputView.printStatus(status);
    }

    private void end(final ChessGame chessGame) {
        chessGame.end();
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
