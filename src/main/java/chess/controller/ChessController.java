package chess.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import chess.board.ChessBoard;
import chess.board.Position;
import chess.dto.ChessBoardDto;
import chess.game.ChessGame;
import chess.game.Command;
import chess.piece.Team;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.PositionConvertor;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ChessGame chessGame;

    public ChessController(final InputView inputView, final OutputView outputView, final ChessGame chessGame) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessGame = chessGame;
    }

    public void run() {
        outputView.printInitGame();
        processGame();
    }

    // TODO: move에 대한 재입력 로직 추가
    private void processGame() {
        final Command firstCommand = repeat(this::readCommand);
        chessGame.receiveCommand(firstCommand);

        while (chessGame.isProcessing()) {
            renderChessBoard();

            final List<String> movePositions = new ArrayList<>();
            final Command command = readMoveCommand(movePositions);
            if (command == Command.END) {
                break;
            }
            if (command == Command.STATUS) {
                outputView.printWinner(chessGame.calculateScore(Team.WHITE), chessGame.calculateScore(Team.BLACK), chessGame.findWinner());
                continue;
            }

            final Position from = PositionConvertor.convert(movePositions.get(0));
            final Position to = PositionConvertor.convert(movePositions.get(1));
            chessGame.movePiece(from, to);
        }
    }

    private Command readCommand() {
        final List<String> commands = inputView.readGameCommand();
        return Command.from(commands.get(0));
    }

    private void renderChessBoard() {
        final ChessBoard chessBoard = chessGame.getChessBoard();
        final ChessBoardDto chessBoardDto = ChessBoardDto.toView(chessBoard);
        outputView.printChessBoard(chessBoardDto);
    }

    private Command readMoveCommand(final List<String> movePositions) {
        return repeat(() -> {
            final List<String> commands = inputView.readGameCommand();
            final Command result = Command.from(commands.get(0));
            if (result != Command.MOVE) {
                return result;
            }
            movePositions.add(commands.get(1));
            movePositions.add(commands.get(2));
            return result;
        });
    }

    private <T> T repeat(final Supplier<T> function) {
        Optional<T> input;
        do {
            input = repeatByEx(function);
        } while (input.isEmpty());
        return input.get();
    }

    private <T> Optional<T> repeatByEx(final Supplier<T> function) {
        try {
            return Optional.of(function.get());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }
}
