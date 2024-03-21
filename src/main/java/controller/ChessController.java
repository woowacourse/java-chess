package controller;

import domain.ChessBoard;
import domain.Square;
import view.InputView;
import view.OutputView;

import java.util.List;

import static view.InputView.*;

public class ChessController {
    public static final int COMMAND_INDEX = 0;
    public static final int MOVE_SOURCE_INDEX = 1;
    public static final int MOVE_TARGET_INDEX = 2;
    public static final int MOVE_COMMAND_SIZE = 3;

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        outputView.printHeader();
        ChessBoard chessBoard = new ChessBoard();

        boolean isRunning = true;
        while (isRunning) {
            try {
                final List<String> input = inputView.readCommand();
                final String command = input.get(COMMAND_INDEX);

                if (START_COMMAND.equals(command)) {
                    chessBoard = init();
                    continue;
                }
                if (MOVE_COMMAND.equals(command)) {
                    play(input, chessBoard);
                    continue;
                }
                if (END_COMMAND.equals(command)) {
                    isRunning = false;
                    continue;
                }
                throw new IllegalArgumentException("잘못된 커맨드 입니다.");
            } catch (final IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private ChessBoard init() {
        final ChessBoard chessBoard = ChessBoard.create();
        outputView.printChessTable(chessBoard.getPieceSquares());

        return chessBoard;
    }

    private void play(final List<String> input, final ChessBoard chessBoard) {
        validateMoveCommend(input);
        final Square source = Square.from(input.get(MOVE_SOURCE_INDEX));
        final Square target = Square.from(input.get(MOVE_TARGET_INDEX));

        chessBoard.move(source, target);

        outputView.printChessTable(chessBoard.getPieceSquares());
    }

    private void validateMoveCommend(final List<String> input) {
        if (input.size() != MOVE_COMMAND_SIZE) {
            throw new IllegalArgumentException("잘못된 move 커맨드 입니다.");
        }
    }
}
