package controller;

import connection.ChessConnectionGenerator;
import domain.ChessGameResult;
import domain.Team;
import domain.piece.Piece;
import domain.player.Player;
import domain.player.PlayerName;
import domain.square.Square;
import dto.PlayerGameRecordDto;
import service.ChessBoardService;
import service.ChessGameService;
import service.ChessResultService;
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

    private final ChessBoardService chessBoardService;
    private final ChessGameService chessGameService;
    private final ChessResultService chessResultService;
    private final PlayerService playerService;
    private final CommandRouter commandRouter;
    private boolean isRunning;

    public ChessController() {
        final Connection connection = ChessConnectionGenerator.getConnection();
        this.chessBoardService = new ChessBoardService(connection);
        this.chessGameService = new ChessGameService(connection);
        this.chessResultService = new ChessResultService(connection);
        this.playerService = new PlayerService(connection);

        this.commandRouter = new CommandRouter(this);
        this.isRunning = true;
    }

    public void run() throws SQLException {
        while (isRunning) {
            try {
                final StartCommandsFormat command = InputView.readStartOption();
                commandRouter.execute(command);
            } catch (final IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

    public void startNewGame() throws SQLException {
        final Player blackPlayer = roadPlayer(Team.BLACK);
        final Player whitePlayer = roadPlayer(Team.WHITE);
        final int gameId = chessGameService.createNewGame(blackPlayer, whitePlayer);
        play(gameId);
    }

    private Player roadPlayer(final Team team) {
        while (true) {
            try {
                final String name = InputView.readTeamPlayerName(team);
                return playerService.roadPlayer(name);
            } catch (final IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

    private void play(final int gameId) throws SQLException {
        printGameOptions(gameId);

        while (isRunning && chessGameService.isNotEnd(gameId)) {
            try {
                final PlayCommandFormat command = readCommand(gameId);
                commandRouter.execute(command, gameId);
            } catch (final IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }

        finishGame(gameId);
    }

    private PlayCommandFormat readCommand(final int gameId) {
        final Team currentTeam = chessGameService.findCurrentTeam(gameId);
        final PlayerName currentPlayerName = chessGameService.findPlayerName(gameId, currentTeam);
        return InputView.readGameCommand(currentTeam, currentPlayerName);
    }

    public void continueGame() throws SQLException {
        final List<Integer> runningGame = chessGameService.findRunningGame();
        final int gameId = readGameId(runningGame);
        play(gameId);
    }

    private int readGameId(final List<Integer> runningGame) {
        while (true) {
            final int input = InputView.readContinueGame(runningGame);
            final boolean hasGame = chessGameService.containRunningGame(input);
            if (hasGame) {
                return input;
            }
            OutputView.printError("게임 ID가 존재하지 않습니다.");
        }
    }

    public void printPlayerRecord() {
        final Player player = readPlayer();
        final PlayerGameRecordDto gameRecord = chessResultService.findGameRecord(player);
        OutputView.printGameRecord(gameRecord);
    }

    private Player readPlayer() {
        while (true) {
            try {
                final String name = InputView.readPlayerName();
                return playerService.findPlayer(name);
            } catch (final IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

    public void quit() {
        isRunning = false;
    }

    private void printGameOptions(final int gameId) {
        final PlayerName blackPlayerName = chessGameService.findPlayerName(gameId, Team.BLACK);
        final PlayerName whitePlayerName = chessGameService.findPlayerName(gameId, Team.WHITE);
        OutputView.printGameOption(gameId, blackPlayerName, whitePlayerName);
        printBoard(gameId);
    }

    public void runStatus(final int gameId) {
        final ChessGameResult chessGameResult = chessResultService.calculateResult(gameId);
        OutputView.printStatus(chessGameResult);
    }

    public void runMove(final PlayCommandFormat command, final int gameId) throws SQLException {
        final Square source = Square.from(command.getSourceInput());
        final Square target = Square.from(command.getTargetInput());

        chessBoardService.move(gameId, source, target);
        printBoard(gameId);
    }

    public void endGame(final int gameId) {
        chessGameService.endGame(gameId);
    }

    private void printBoard(final int gameId) {
        final Map<Square, Piece> pieceSquares = chessBoardService.getPieceSquares(gameId);
        OutputView.printChessBoard(pieceSquares);
    }

    private void finishGame(final int gameId) {
        if (chessGameService.isEnd(gameId)) {
            chessResultService.saveResult(gameId);
            runStatus(gameId);
        }
    }
}
