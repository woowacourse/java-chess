package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.game.ChessGame;
import chess.domain.piece.Side;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
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

    public ChessController() {
        putCommands();
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
    }

    private static String[] splitPosition(final String[] splitCommand, final int index) {
        return splitCommand[index].split("");
    }

    public void run() {
        final Board board = BoardFactory.generateBoard();
        final ChessGame chessGame = new ChessGame(board);

        OutputView.printStartMessage();
        while (chessGame.isRunnable()) {
            printChessBoard(chessGame);
            executeCommand(chessGame);
        }
        OutputView.printScore(chessGame.calculateWhiteScore(), chessGame.calculateBlackScore());
        OutputView.printWinner(chessGame.calculateWinner());
    }

    private void printChessBoard(final ChessGame chessGame) {
        if (chessGame.isStart()) {
            OutputView.printBoard(chessGame.getBoard());
        }
    }

    private void executeCommand(final ChessGame chessGame) {
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
