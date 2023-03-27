package chess.controller;

import chess.database.ChessBoardDao;
import chess.domain.*;
import chess.view.InputView;
import chess.view.OutputView;
import dto.ChessBoardDto;
import dto.CommandDto;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class ChessGameController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ChessBoardDao chessBoardDao;

    public ChessGameController(final InputView inputView, final OutputView outputView, final ChessBoardDao chessBoardDao) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessBoardDao = chessBoardDao;
    }

    public void run() {
        outputView.printInitialMessage();
        Command command = repeatUntilNoException(this::startGame);
        ChessBoard chessBoard = loadChessBoardOrSaveNewChessBoard();
        outputView.printChessBoard(ChessBoardDto.of(chessBoard.getPieces()));
        while (command.isPlaying()) {
            command = repeatUntilNoException(this::playTurn, chessBoard);
        }
    }

    private ChessBoard loadChessBoardOrSaveNewChessBoard() {
        ChessBoard chessBoard = chessBoardDao.findChessBoard();
        if (chessBoard == null) {
            chessBoard = ChessBoardFactory.generate();
            chessBoardDao.saveChessBoard(chessBoard);
            return chessBoard;
        }
        return chessBoard;
    }

    private Command startGame() {
        CommandDto commandDto = inputView.readCommand();
        Command command = Command.from(commandDto.getCommand());
        command.validateCommandInStart();
        return command;
    }

    private Command playTurn(final ChessBoard chessBoard) {
        CommandDto commandDto = inputView.readCommand();
        Command command = Command.from(commandDto.getCommand());
        command.validateCommandInPlaying();
        executeCommand(chessBoard, commandDto, command);
        return confirmGameEnd(chessBoard, command);
    }

    private void executeCommand(final ChessBoard chessBoard, final CommandDto commandDto, final Command command) {
        if (command.isMove()) {
            updateChessBoard(chessBoard, commandDto);
            chessBoardDao.updateChessBoard(chessBoard);
        }
        if (command.isStatus()) {
            outputView.printScore(Side.WHITE.name(), chessBoard.calculateScore(Side.WHITE));
            outputView.printScore(Side.BLACK.name(), chessBoard.calculateScore(Side.BLACK));
        }
    }

    private void updateChessBoard(final ChessBoard chessBoard, final CommandDto commandDto) {
        final Square from = Square.of(Rank.from(commandDto.getSourceRank()), File.from(commandDto.getSourceFile()));
        final Square to = Square.of(Rank.from(commandDto.getDestinationRank()), File.from(commandDto.getDestinationFile()));
        validateSameSquare(from, to);
        if (!chessBoard.canMove(from, to)) {
            outputView.printInvalidMoveMessage();
        }
        outputView.printChessBoard(ChessBoardDto.of(chessBoard.getPieces()));
    }

    private void validateSameSquare(final Square from, final Square to) {
        if (from == to) {
            throw new IllegalArgumentException("같은 지점이 들어왔습니다.");
        }
    }

    private Command confirmGameEnd(final ChessBoard chessBoard, final Command command) {
        List<String> aliveKings = chessBoard.findAliveKing();
        if (aliveKings.size() == 1) {
            outputView.printWinner(aliveKings.get(0));
            chessBoardDao.deleteChessBoard();
            return Command.END;
        }
        return command;
    }

    private <T> T repeatUntilNoException(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private <T, R> R repeatUntilNoException(Function<T, R> function, T arg) {
        while (true) {
            try {
                return function.apply(arg);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
