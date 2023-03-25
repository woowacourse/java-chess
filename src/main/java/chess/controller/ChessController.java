package chess.controller;

import chess.domain.game.ChessGame;
import chess.domain.piece.Side;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class ChessController {

    public static final int COMMAND_INDEX = 0;
    public static final int SOURCE_INDEX = 1;
    public static final int TARGET_INDEX = 2;
    public static final int FILE_INDEX = 0;
    public static final int RANK_INDEX = 1;

    private final Map<Command, BiConsumer<ChessGame, String[]>> commands = new EnumMap<>(Command.class);
    private final ChessGameService chessGameService;

    public ChessController(ChessGameService chessGameService) {
        putCommands();
        this.chessGameService = chessGameService;
    }

    private void putCommands() {
        commands.put(Command.START, (chessGame, ignored) -> start(chessGame));
        commands.put(Command.END, (chessGame, ignored) -> end(chessGame));
        commands.put(Command.STATUS, (chessGame, ignored) -> status(chessGame));
        commands.put(Command.MOVE, this::moveOrNot);
    }

    private void start(ChessGame chessGame) {
        chessGame.start();
    }

    private void end(ChessGame chessGame) {
        chessGame.end();
    }

    private void status(ChessGame chessGame) {
        final Double whiteScore = chessGame.calculateWhiteScore();
        final Double blackScore = chessGame.calculateBlackScore();
        OutputView.printScore(whiteScore, blackScore);
        OutputView.printWinner(Side.calculateWinner(whiteScore, blackScore));
    }

    private void moveOrNot(ChessGame chessGame, String[] splitCommand) {
        final String[] source = splitPosition(splitCommand, SOURCE_INDEX);
        final String[] target = splitPosition(splitCommand, TARGET_INDEX);

        final Position sourcePosition = Position.of(File.getFile(source[FILE_INDEX]),
                Rank.getRank(Integer.parseInt(source[RANK_INDEX])));
        final Position targetPosition = Position.of(File.getFile(target[FILE_INDEX]),
                Rank.getRank(Integer.parseInt(target[RANK_INDEX])));

        chessGame.moveOrNot(sourcePosition, targetPosition);
        chessGameService.update(chessGame, sourcePosition, targetPosition);
    }

    private String[] splitPosition(final String[] splitCommand, final int index) {
        return splitCommand[index].split("");
    }

    public void run() {
        ChessGame chessGame = initChessGame(inputInitCommand());

        while (chessGame.isRunnable()) {
            printChessBoard(chessGame);
            executeCommand(chessGame);
        }
        processIfClear(chessGame);
    }

    private void processIfClear(final ChessGame chessGame) {
        if (chessGame.isClear()) {
            final Side winner = chessGame.calculateWinner();

            chessGameService.delete();
            OutputView.printKingDie(winner);
            OutputView.printScore(chessGame.calculateWhiteScore(), chessGame.calculateBlackScore());
            OutputView.printWinner(winner);
        }
    }

    private InitCommand inputInitCommand() {
        try {
            final String command = InputView.readInitCommand();
            return InitCommand.findByString(command);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return inputInitCommand();
        }
    }

    private ChessGame initChessGame(InitCommand command) {
        ChessGame chessGame = findChessGameIfContinue(command);

        if (chessGame == null) {
            OutputView.printNewGameMessage();
            chessGameService.delete();
            chessGame = chessGameService.save();
        }
        return chessGame;
    }

    private ChessGame findChessGameIfContinue(final InitCommand command) {
        ChessGame chessGame = null;
        if (command.isContinue()) {
            chessGame = chessGameService.findChessGame();
            printContinueMessage(chessGame);
        }
        return chessGame;
    }

    private void printContinueMessage(final ChessGame chessGame) {
        if (chessGame == null) {
            OutputView.printNonContinueMessage();
            return;
        }
        OutputView.printContinueMessage();
    }

    private void printChessBoard(final ChessGame chessGame) {
        if (chessGame.isStart()) {
            OutputView.printBoard(chessGame.getBoard());
        }
    }

    private void executeCommand(ChessGame chessGame) {
        try {
            final String[] splitCommand = InputView.readCommand().split(" ");
            final Command command = Command.findByString(splitCommand[COMMAND_INDEX]);
            commands.get(command).accept(chessGame, splitCommand);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            executeCommand(chessGame);
        }
    }
}
