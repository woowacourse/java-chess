package controller;

import connection.ChessConnectionGenerator;
import domain.ChessGameResult;
import domain.Team;
import domain.piece.Piece;
import domain.square.Square;
import service.ChessGameService;
import view.InputView;
import view.OutputView;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class ChessController {
    private static final Pattern MOVE_FORMAT = Pattern.compile("^move [a-z]\\d [a-z]\\d$");
    private static final String COMMAND_DELIMITER = " ";
    private static final int MOVE_SOURCE_INDEX = 1;
    private static final int TARGET_SOURCE_INDEX = 2;

    private final InputView inputView;
    private final OutputView outputView;
    private final ChessGameService chessGameService;
    private boolean isRunning;

    public ChessController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.chessGameService = new ChessGameService(ChessConnectionGenerator.getConnection());
        this.isRunning = true;
    }

    public void run() throws SQLException {
        while (isRunning) {
            try {
                final String command = inputView.readStartOption();
                final int gameId;
                if ("quit".equals(command)) {
                    isRunning = false;
                    continue;
                }
                if ("start".equals(command)) {
                    gameId = chessGameService.createNewGame();
                    play(gameId);
                    continue;
                }
                if ("continue".equals(command)) {
                    final List<Integer> runningGame = chessGameService.findRunningGame();
                    gameId = readGameId(runningGame);
                    play(gameId);
                    continue;
                }
                throw new IllegalArgumentException("잘못된 커맨드입니다.");
            } catch (final IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private int readGameId(final List<Integer> runningGame) {
        while (true) {
            final int input = inputView.readContinueGame(runningGame);
            final boolean hasGame = chessGameService.containRunningGame(input);
            if (hasGame) {
                return input;
            }
            outputView.printError("게임 ID가 존재하지 않습니다.");
        }
    }

    private void play(final int gameId) throws SQLException {
        outputView.printGameOption(gameId);
        printBoard(gameId);

        while (chessGameService.isNotEnd(gameId)) {
            try {
                final Team currentTeam = chessGameService.currentTeam(gameId);
                final String command = inputView.readGameCommand(currentTeam);
                if ("quit".equals(command)) {
                    isRunning = false;
                    return;
                }
                if ("status".equals(command)) {
                    runStatus(gameId);
                    continue;
                }
                if ("end".equals(command)) {
                    chessGameService.endGame(gameId);
                    continue;
                }
                if (MOVE_FORMAT.matcher(command).matches()) {
                    runMove(command, gameId);
                    continue;
                }
                throw new IllegalArgumentException("잘못된 커맨드입니다.");
            } catch (final IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
        runStatus(gameId);
    }

    private void runStatus(final int gameId) {
        final ChessGameResult chessGameResult = chessGameService.calculateResult(gameId);
        outputView.printStatus(chessGameResult);
    }

    private void runMove(final String command, final int gameId) throws SQLException {
        final List<String> commands = Arrays.asList(command.split(COMMAND_DELIMITER));

        final Square source = Square.from(commands.get(MOVE_SOURCE_INDEX));
        final Square target = Square.from(commands.get(TARGET_SOURCE_INDEX));

        chessGameService.move(gameId, source, target);
        printBoard(gameId);
    }

    private void printBoard(final int gameId) {
        final Map<Square, Piece> pieceSquares = chessGameService.getPieceSquares(gameId);
        outputView.printChessBoard(pieceSquares);
    }
}
