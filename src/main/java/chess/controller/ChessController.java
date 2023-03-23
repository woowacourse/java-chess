package chess.controller;

import chess.dao.chessGameDao;
import chess.domain.board.Position;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.PositionParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ChessController {

    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    private final InputView inputView;
    private final OutputView outputView;
    private ChessGame chessGame;
    private final chessGameDao chessGameDao;
    private final Map<Command, Action> commandMap = new HashMap<>();

    public ChessController(final InputView inputView, final OutputView outputView, final ChessGame chessGame, final chessGameDao chessGameDao) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessGame = chessGame;
        this.chessGameDao = chessGameDao;
        commandMap.put(Command.START, new Action(ignored -> start()));
        commandMap.put(Command.LOAD, new Action(this::load));
        commandMap.put(Command.MOVE, new Action(this::move));
        commandMap.put(Command.END, new Action(ignored -> end()));
    }

    public void play() {
        outputView.startMessage();
        while (chessGame.isOnGoing()) {
            repeatRead();
        }
    }

    private void repeatRead() {
        try {
            List<String> commands = inputView.inputCommand();
            Command command = Command.from(commands.get(COMMAND_INDEX));
            commandMap.get(command).excute(commands);
        } catch (RuntimeException e) {
            outputView.printErrorMessage(e);
            outputView.printGuideMessage();
            repeatRead();
        }
    }

    private void start() {
        chessGame.startGame();
        printBoard(chessGame.getBoard());
    }

    private void printBoard(final Map<Position, Piece> board) {
        outputView.printBoard(board);
    }

    private void load(List<String> gameId) {
        int gameNumber = Integer.parseInt(gameId.get(1));

        this.chessGame = chessGameDao.load(gameNumber);
        printBoard(chessGame.getBoard());
    }

    private void move(final List<String> commands) {
        Position parsedFile = PositionParser.parse(commands.get(SOURCE_INDEX));
        Position parsedRank = PositionParser.parse(commands.get(TARGET_INDEX));
        chessGame.playTurn(parsedFile, parsedRank);
        printBoard(chessGame.getBoard());
    }

    private void end() {
        chessGameDao.save(chessGame);
        Integer recentSavedId = chessGameDao.findRecentSavedId();
        outputView.printGameId(recentSavedId);
        chessGame.end();
    }
}
