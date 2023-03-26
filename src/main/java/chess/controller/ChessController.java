package chess.controller;

import chess.controller.dto.BoardDto;
import chess.dao.ChessGameDao;
import chess.dao.JdbcChessGameDao;
import chess.domain.ChessGame;
import chess.domain.Color;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class ChessController {
    private ChessGame chessGame;
    private final ChessGameDao chessGameDao;
    private final Map<String, GameAction> commandMapper = new HashMap<>();

    public ChessController() {
        this.chessGameDao = new JdbcChessGameDao();
        commandMapper.put("start", (gameId, ignore) -> start(gameId));
        commandMapper.put("move", (gameId, arguments) -> move(gameId, arguments));
        commandMapper.put("status", (gameId, ignore) -> status(gameId));
        commandMapper.put("end", (gameId, ignore) -> end(gameId));
    }

    public void execute() {
        int gameId = loadChessGame();

        OutputView.printGameStartMessage(gameId);
        OutputView.printBoard(BoardDto.create(chessGame.getBoard()));
        while (!chessGame.isEnd() && !chessGame.isCatch()) {
            runGame(gameId);
        }

        if (chessGame.isCatch()) {
            OutputView.printResultWhenKingCatch(chessGame.getTurn().reverse());
            return;
        }
        showStatus(chessGame);
    }

    private int loadChessGame() {
        List<Integer> possibleIds = chessGameDao.findAllPossibleId();
        List<Integer> impossibleIds = chessGameDao.findAllImpossibleId();
        OutputView.printRoomState(possibleIds, impossibleIds);

        int id = InputView.readGameId();
        ChessGame existedChessGame = chessGameDao.findById(id);

        if (existedChessGame == null) {
            this.chessGame = new ChessGame();
            chessGameDao.save(id, chessGame);
            return id;
        }

        this.chessGame = existedChessGame;
        return id;
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
        chessGame.start();
        chessGameDao.updateById(gameId, chessGame); // Board 초기화 때문에 ..
        OutputView.printBoard(BoardDto.create(chessGame.getBoard()));
    }

    private void move(int gameId, List<String> arguments) {
        chessGame.move(arguments);
        chessGameDao.updateById(gameId, chessGame);
        OutputView.printBoard(BoardDto.create(chessGame.getBoard()));
    }

    private void status(int gameId) {
        showStatus(chessGame);
    }

    private void end(int gameId) {
        chessGame.end();
        chessGameDao.updateById(gameId, chessGame);
    }

    private void showStatus(ChessGame chessGame) {
        Map<Color, Double> status = chessGame.status();
        OutputView.printStatus(status.get(Color.WHITE), status.get(Color.BLACK));
    }
}
