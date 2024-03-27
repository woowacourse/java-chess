package controller;

import connection.ChessConnectionGenerator;
import domain.ChessGameResult;
import domain.Team;
import domain.piece.Piece;
import domain.player.Player;
import domain.player.PlayerName;
import domain.square.Square;
import dto.PlayerGameRecordDto;
import service.ChessService;
import service.PlayerService;
import view.InputView;
import view.OutputView;

import java.sql.Connection;
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
    private final ChessService chessService;
    private final PlayerService playerService;
    private boolean isRunning;

    public ChessController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();

        final Connection connection = ChessConnectionGenerator.getConnection();
        this.chessService = new ChessService(connection);
        this.playerService = new PlayerService(connection);

        this.isRunning = true;
    }

    public void run() throws SQLException {
        while (isRunning) {
            try {
                final String command = inputView.readStartOption();
                if ("quit".equals(command)) {
                    isRunning = false;
                    continue;
                }
                if ("start".equals(command)) {
                    final Player blackPlayer = roadPlayer(Team.BLACK);
                    final Player whitePlayer = roadPlayer(Team.WHITE);
                    final int gameId = chessService.createNewGame(blackPlayer, whitePlayer);
                    play(gameId);
                    continue;
                }
                if ("continue".equals(command)) {
                    final List<Integer> runningGame = chessService.findRunningGame();
                    final int gameId = readGameId(runningGame);
                    play(gameId);
                    continue;
                }
                if ("record".equals(command)) {
                    final PlayerName name = readPlayerName();
                    final PlayerGameRecordDto gameRecord = chessService.findGameRecord(name);
                    outputView.printGameRecord(gameRecord);
                    continue;
                }
                throw new IllegalArgumentException("잘못된 커맨드입니다.");
            } catch (final IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private Player roadPlayer(final Team team) {
        while (true) {
            try {
                final String name = inputView.readTeamPlayerName(team);
                return playerService.roadPlayer(name);
            } catch (final IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private PlayerName readPlayerName() {
        while (true) {
            try {
                final String name = inputView.readPlayerName();
                return playerService.findPlayerName(name);
            } catch (final IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private void play(final int gameId) throws SQLException {
        final PlayerName blackPlayerName = chessService.findPlayerName(gameId, Team.BLACK);
        final PlayerName whitePlayerName = chessService.findPlayerName(gameId, Team.WHITE);
        outputView.printGameOption(gameId, blackPlayerName, whitePlayerName);
        printBoard(gameId);

        while (chessService.isNotEnd(gameId)) {
            try {
                final String command = readCommand(gameId);
                if ("quit".equals(command)) {
                    isRunning = false;
                    return;
                }
                if ("status".equals(command)) {
                    runStatus(gameId);
                    continue;
                }
                if ("end".equals(command)) {
                    chessService.endGame(gameId);
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
        chessService.saveResult(gameId);
        runStatus(gameId);
    }

    private int readGameId(final List<Integer> runningGame) {
        while (true) {
            final int input = inputView.readContinueGame(runningGame);
            final boolean hasGame = chessService.containRunningGame(input);
            if (hasGame) {
                return input;
            }
            outputView.printError("게임 ID가 존재하지 않습니다.");
        }
    }

    private String readCommand(final int gameId) {
        final Team currentTeam = chessService.currentTeam(gameId);
        final PlayerName currentPlayerName = chessService.findPlayerName(gameId, currentTeam);
        return inputView.readGameCommand(currentTeam, currentPlayerName);
    }

    private void runStatus(final int gameId) {
        final ChessGameResult chessGameResult = chessService.calculateResult(gameId);
        outputView.printStatus(chessGameResult);
    }

    private void runMove(final String command, final int gameId) throws SQLException {
        final List<String> commands = Arrays.asList(command.split(COMMAND_DELIMITER));

        final Square source = Square.from(commands.get(MOVE_SOURCE_INDEX));
        final Square target = Square.from(commands.get(TARGET_SOURCE_INDEX));

        chessService.move(gameId, source, target);
        printBoard(gameId);
    }

    private void printBoard(final int gameId) {
        final Map<Square, Piece> pieceSquares = chessService.getPieceSquares(gameId);
        outputView.printChessBoard(pieceSquares);
    }
}
