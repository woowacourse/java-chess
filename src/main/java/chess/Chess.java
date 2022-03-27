package chess;

import chess.domain.GameState;
import chess.domain.Status;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.PieceType;
import chess.view.InputView;
import chess.view.OutputView;

public class Chess {

    private static final String COMMAND_DISTRIBUTOR = " ";
    private static final String CANNOT_IMPLEMENT_COMMAND = "현재 실행할 수 없는 명령입니다.";
    private static final String INVALID_MOVING_COMMAND = "올바르지 않은 이동 명령입니다.";
    private static final int COMMAND = 0;
    private static final int MOVE_COMMAND_LENGTH = 3;
    private static final int STARTING_POINT = 1;
    private static final int DESTINATION = 2;

    private final Board board;
    private GameState gameState;

    public Chess(final Board board) {
        this.board = board;
        gameState = GameState.READY;
    }

    public void run() {
        OutputView.printStartMessage();
        while (gameState != GameState.END) {
            repeatTurn();
        }
        OutputView.printStatus(new Status(board));
    }

    private void repeatTurn() {
        try {
            operateOnce();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            repeatTurn();
        }
    }

    private void operateOnce() {
        String[] args = InputView.input()
                .split(COMMAND_DISTRIBUTOR, -1);
        Command command = Command.from(args[COMMAND]);
        if (command == Command.START && gameState == GameState.READY) {
            start();
            return;
        }
        if (command == Command.MOVE && gameState.isRunning()) {
            move(args);
            return;
        }
        if (command == Command.END) {
            gameState = GameState.END;
            return;
        }
        if (command == Command.STATUS && gameState.isRunning()) {
            OutputView.printStatus(new Status(board));
            return;
        }
        throw new IllegalArgumentException(CANNOT_IMPLEMENT_COMMAND);
    }

    private void start() {
        gameState = GameState.WHITE_RUNNING;
        OutputView.printBoard(board.getPieces());
    }

    private void move(String[] args) {
        if (args.length != MOVE_COMMAND_LENGTH) {
            throw new IllegalArgumentException(INVALID_MOVING_COMMAND);
        }
        Position start = Position.from(args[STARTING_POINT]);
        Position target = Position.from(args[DESTINATION]);
        Color currentColor = getCurrentColor();
        if (board.move(start, target, currentColor).isSamePiece(PieceType.KING)) {
            gameState = GameState.END;
        }
        gameState = gameState.getOpposite();
        OutputView.printBoard(board.getPieces());
    }

    private Color getCurrentColor() {
        if (gameState == GameState.BLACK_RUNNING) {
            return Color.BLACK;
        }
        return Color.WHITE;
    }
}
