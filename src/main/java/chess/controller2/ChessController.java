package chess.controller2;

import chess.domain2.ChessBoard;
import chess.domain2.GameState;
import chess.domain2.Square;
import chess.view2.InputView;
import chess.view2.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessController {

    public static void run() {
        OutputView.printGameInformation();
        ChessBoard chessBoard = initGame();
        if (chessBoard == null) {
            return;
        }
        GameState gameState;
        boolean isEnd = false;
        while (!isEnd) {
            isEnd = proceed(chessBoard);
        }
    }

    private static boolean proceed(ChessBoard chessBoard) {
        List<Square> moveSquares = new ArrayList<>();
        List<String> stateInput = Arrays.asList(InputView.inputState().split(" "));
        if (isInputStateMove(stateInput)) {
            moveSquares = getMoveSquare(stateInput);
        }
        if (moveSquares == null) {
            return true;
        }
        GameState gameState = GameState.of(stateInput.get(0));
        checkInput(gameState);
        if (gameState.equals(GameState.END)) {
            return true;
        }
        move(chessBoard, moveSquares, gameState);
        OutputView.printChessBoard(chessBoard.getChessBoard());
        return false;
    }

    private static void move(ChessBoard chessBoard, List<Square> moveSquares, GameState gameState) {
        if (gameState.equals(GameState.MOVE)) {
            if (!chessBoard.movePiece(moveSquares)) {
                OutputView.printCanNotMoveMessage();
            }
        }
    }

    private static boolean isInputStateMove(List<String> stateInput) {
        return stateInput.size() != 1;
    }

    private static ChessBoard initGame() {
        if (!startChessGame()) {
            return null;
        }
        ChessBoard chessBoard = new ChessBoard();
        OutputView.printChessBoard(chessBoard.getChessBoard());
        return chessBoard;
    }

    private static List<Square> getMoveSquare(List<String> input) {
        List<Square> moveSquares = new ArrayList<>();
        try {
            moveSquares.add(Square.of(input.get(1)));
            moveSquares.add(Square.of(input.get(2)));
        } catch (Exception e) {
            OutputView.printInputError();
            String inputState = InputView.inputState();
            if (GameState.of(inputState).equals(GameState.END)) {
                return null;
            }
            moveSquares = getMoveSquare(Arrays.asList(inputState.split(" ")));
        }
        return moveSquares;
    }

    private static void checkInput(GameState gameState) {
        if (gameState.equals(GameState.START)) {
            OutputView.printAlreadyGameStartedMessage();
        }
        if (gameState.equals(GameState.ERROR)) {
            OutputView.printInputError();
        }
    }

    private static boolean startChessGame() {
        String input = InputView.inputState();
        while (!GameState.of(input).equals(GameState.START)) {
            if (GameState.of(input).equals(GameState.END)) {
                return false;
            }
            OutputView.printStartGameFirstMessage();
            input = InputView.inputState();
        }
        return true;
    }
}
