package chess.controller;

import chess.dao.JdbcGameDao;
import chess.dao.JdbcPieceDao;
import chess.domain.ChessGame;
import chess.domain.Color;
import chess.dto.BoardDto;
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
        commandMapper.put("start", (gameId, ignore, chessGame) -> start(gameId, chessGame));
        commandMapper.put("move", (gameId, arguments, chessGame) -> move(gameId, chessGame, arguments));
        commandMapper.put("status", (ignore1, ignore2, chessGame) -> status(chessGame));
        commandMapper.put("end", (gameId, ignore, chessGame) -> end(gameId, chessGame));
    }

    public void execute() {
        List<Integer> possibleGameIds = chessService.findPossibleGameIds();
        List<Integer> impossibleGameIds = chessService.findImpossibleGameIds();
        OutputView.printRoomState(possibleGameIds, impossibleGameIds);

        int gameId = InputView.readGameId();
        ChessGame chessGame = chessService.loadChessGame(gameId);

        OutputView.printGameStartMessage(gameId);
        OutputView.printBoard(BoardDto.create(chessGame.getBoard()));

        while (!chessGame.isEnd() && !chessGame.isCatch()) {
            runGame(gameId, chessGame);
        }

        if (chessGame.isCatch()) {
            OutputView.printResultWhenKingCatch(chessGame.getTurn().reverse());
            return;
        }
        showStatus(chessGame);
    }

    private void runGame(int gameId, ChessGame chessGame) {
        try {
            CommandLine commandLine = new CommandLine(InputView.readCommand());
            commandMapper.get(commandLine.getCommand())
                    .execute(gameId, commandLine.getArguments(), chessGame);
        } catch (IllegalArgumentException | IllegalStateException | NoSuchElementException e) {
            OutputView.printError(e.getMessage());
        }
    }

    private void start(int gameId, ChessGame chessGame) {
        chessService.start(gameId, chessGame);
        OutputView.printBoard(BoardDto.create(chessGame.getBoard()));
    }

    private void move(int gameId, ChessGame chessGame, List<String> arguments) {
        chessService.move(gameId, chessGame, arguments);
        OutputView.printBoard(BoardDto.create(chessGame.getBoard()));
    }

    private void status(ChessGame chessGame) {
        showStatus(chessGame);
    }

    private void end(int gameId, ChessGame chessGame) {
        chessService.end(gameId, chessGame);
    }

    private void showStatus(ChessGame chessGame) {
        Map<Color, Double> score = chessGame.status().getScore();
        OutputView.printStatus(score.get(Color.WHITE), score.get(Color.BLACK));
    }
}
