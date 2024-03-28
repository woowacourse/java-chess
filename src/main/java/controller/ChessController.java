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
import view.format.command.PlayCommandFormat;
import view.format.command.StartCommandsFormat;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ChessService chessService;
    private final PlayerService playerService;
    private final CommandRouter commandRouter;
    private boolean isRunning;

    public ChessController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();

        final Connection connection = ChessConnectionGenerator.getConnection();
        this.chessService = new ChessService(connection);
        this.playerService = new PlayerService(connection);

        this.commandRouter = new CommandRouter(this);
        this.isRunning = true;
    }

    public void run() throws SQLException {
        while (isRunning) {
            try {
                final StartCommandsFormat command = inputView.readStartOption();
                commandRouter.execute(command);
            } catch (final IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    public void startNewGame() throws SQLException {
        final Player blackPlayer = roadPlayer(Team.BLACK);
        final Player whitePlayer = roadPlayer(Team.WHITE);
        final int gameId = chessService.createNewGame(blackPlayer, whitePlayer);
        play(gameId);
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

    private void play(final int gameId) throws SQLException {
        printGameOptions(gameId);

        while (isRunning && chessService.isNotEnd(gameId)) {
            try {
                final PlayCommandFormat command = readCommand(gameId);
                commandRouter.execute(command, gameId);
            } catch (final IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }

        finishGame(gameId);
    }

    private PlayCommandFormat readCommand(final int gameId) {
        final Team currentTeam = chessService.currentTeam(gameId);
        final PlayerName currentPlayerName = chessService.findPlayerName(gameId, currentTeam);
        return inputView.readGameCommand(currentTeam, currentPlayerName);
    }

    public void continueGame() throws SQLException {
        final List<Integer> runningGame = chessService.findRunningGame();
        final int gameId = readGameId(runningGame);
        play(gameId);
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

    public void printPlayerRecord() {
        final PlayerName name = readPlayerName();
        final PlayerGameRecordDto gameRecord = chessService.findGameRecord(name);
        outputView.printGameRecord(gameRecord);
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

    public void quit() {
        isRunning = false;
    }

    private void printGameOptions(final int gameId) {
        final PlayerName blackPlayerName = chessService.findPlayerName(gameId, Team.BLACK);
        final PlayerName whitePlayerName = chessService.findPlayerName(gameId, Team.WHITE);
        outputView.printGameOption(gameId, blackPlayerName, whitePlayerName);
        printBoard(gameId);
    }

    public void runStatus(final int gameId) {
        final ChessGameResult chessGameResult = chessService.calculateResult(gameId);
        outputView.printStatus(chessGameResult);
    }

    public void runMove(final PlayCommandFormat command, final int gameId) throws SQLException {
        final Square source = Square.from(command.getSourceInput());
        final Square target = Square.from(command.getTargetInput());

        chessService.move(gameId, source, target);
        printBoard(gameId);
    }

    public void endGame(final int gameId) {
        chessService.endGame(gameId);
    }

    private void printBoard(final int gameId) {
        final Map<Square, Piece> pieceSquares = chessService.getPieceSquares(gameId);
        outputView.printChessBoard(pieceSquares);
    }

    private void finishGame(final int gameId) {
        if (chessService.isEnd(gameId)) {
            chessService.saveResult(gameId);
            runStatus(gameId);
        }
    }
}
