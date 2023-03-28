package chess.controller;

import chess.controller.dto.BoardDto;
import chess.dao.JdbcGameDao;
import chess.dao.JdbcPieceDao;
import chess.domain.ChessGame;
import chess.domain.Color;
import chess.service.ChessService;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class ChessController {
    private final ChessService chessService;
    private final Map<String, GameAction> commandMapper = new HashMap<>();

    public ChessController() {
        chessService = new ChessService(new JdbcGameDao(), new JdbcPieceDao());
        commandMapper.put("start", (gameId, ignore) -> start(gameId));
        commandMapper.put("move", (gameId, arguments) -> move(gameId, arguments));
        commandMapper.put("status", (gameId, ignore) -> status(gameId));
        commandMapper.put("end", (gameId, ignore) -> end(gameId));
    }

    public void execute() {
        List<Integer> possibleGameIds = chessService.findPossibleGameIds();
        List<Integer> impossibleGameIds = chessService.findImpossibleGameIds();
        OutputView.printRoomState(possibleGameIds, impossibleGameIds);

        int gameId = InputView.readGameId();
        chessService.loadChessGame(gameId);

        OutputView.printGameStartMessage(gameId);
        OutputView.printBoard(chessService.getBoard());

        while (chessService.canPlay()) {
            runGame(gameId);
        }

        if (chessService.isCatch()) {
            OutputView.printResultWhenKingCatch(chessService.getWinner());
            return;
        }
        showStatus();
    }

    private void runGame(int gameId) {
        try {
            CommandLine commandLine = new CommandLine(InputView.readCommand());
            commandMapper.get(commandLine.getCommand())
                    .execute(gameId, commandLine.getArguments());
        } catch (IllegalArgumentException | IllegalStateException | NoSuchElementException e) {
            OutputView.printError(e.getMessage());
        }
    }

    private void start(int gameId) {
        chessService.start(gameId);
        OutputView.printBoard(chessService.getBoard());
    }

    private void move(int gameId, List<String> arguments) {
        chessService.move(gameId, arguments);
        OutputView.printBoard(chessService.getBoard());
    }

    private void status(int gameId) {
        showStatus();
    }

    private void end(int gameId) {
        chessService.end(gameId);
    }

    private void showStatus() {
        Map<Color, Double> status = chessService.status();
        OutputView.printStatus(status.get(Color.WHITE), status.get(Color.BLACK));
    }
}
