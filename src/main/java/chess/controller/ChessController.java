package chess.controller;

import chess.domain.Position;
import chess.domain.chessboard.ChessBoard;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.List;

public class ChessController {
    private static final int MOVE_COMMAND_SIZE = 3;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;
    private static final int MOVE_COMMAND_INDEX = 0;
    private static final String MOVE_COMMAND = "move";

    public static void run() {
        String gameState = InputView.inputGameState();

        if ("start".equalsIgnoreCase(gameState)) {
            ChessBoard chessBoard = new ChessBoard();
            OutputView.printChessBoard(chessBoard);

            int count = 5;
            while (count-- > 0) {
                List<String> movePositions = validateInputMoveCommand(InputView.inputMoveCommand());
                pieceMove(movePositions, chessBoard);
                OutputView.printChessBoard(chessBoard);
            }
        }
        System.out.println("\n게임을 종료 합니다");
    }

    private static List<String> validateInputMoveCommand(String input) {
        List<String> inputValues = Arrays.asList(input.split(" "));
        if (isIncorrectCommandLength(inputValues)) {
            throw new IllegalArgumentException();
        }
        if (isNotMoveCommand(inputValues)) {
            throw new IllegalArgumentException();
        }
        if (isSourceEqualTarget(inputValues)) {
            throw new IllegalArgumentException();
        }
        return extractMovePosition(inputValues);
    }

    private static boolean isIncorrectCommandLength(List<String> inputValues) {
        return inputValues.size() != MOVE_COMMAND_SIZE;
    }

    private static boolean isNotMoveCommand(List<String> inputValues) {
        return !MOVE_COMMAND.equals(inputValues.get(MOVE_COMMAND_INDEX));
    }

    private static boolean isSourceEqualTarget(List<String> inputValues) {
        return inputValues.get(SOURCE_INDEX).equals(inputValues.get(TARGET_INDEX));
    }

    private static List<String> extractMovePosition(List<String> inputValues) {
        return Arrays.asList(inputValues.get(SOURCE_INDEX), inputValues.get(TARGET_INDEX));
    }


    private static void pieceMove(List<String> movePositions, ChessBoard chessBoard) {
        Position source = Position.of(movePositions.get(0));
        Position target = Position.of(movePositions.get(1));
        chessBoard.movePiece(source, target);

    }
}
