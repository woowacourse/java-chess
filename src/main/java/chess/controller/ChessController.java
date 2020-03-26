package chess.controller;

import chess.domain.Position;
import chess.domain.chessboard.ChessBoard;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.List;

public class ChessController {
    private static final int MOVE_COMMAND_SIZE = 3;
    private static final int MOVE_COMMAND_INDEX = 0;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;
    private static final String MOVE_COMMAND = "move";
    private static final String START_VALUE = "start";
    private static final String BLANK = " ";

    public static void run() {
        String gameState = InputView.inputGameState();

        if (START_VALUE.equalsIgnoreCase(gameState)) {
            chessStart();
        }

        OutputView.printGameEndMessage();
    }

    private static void chessStart() {
        ChessBoard chessBoard = new ChessBoard();
        OutputView.printChessBoard(chessBoard);

        while (chessBoard.isSurviveKings()) {
            gameRun(chessBoard);
        }
        OutputView.calculateScore(chessBoard);
    }

    private static void gameRun(ChessBoard chessBoard) {
        try {
            List<String> positionsToMove = validateInputMoveCommand(InputView.inputMoveCommand());
            pieceMove(positionsToMove, chessBoard);
            OutputView.printChessBoard(chessBoard);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            OutputView.printChessBoard(chessBoard);
            gameRun(chessBoard);
        }
    }


    private static List<String> validateInputMoveCommand(String input) {
        List<String> inputValues = Arrays.asList(input.split(BLANK));
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
        Position source = Position.of(movePositions.get(FILE_INDEX));
        Position target = Position.of(movePositions.get(RANK_INDEX));
        chessBoard.movePiece(source, target);

    }
}
