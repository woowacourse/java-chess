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
        do {
            isEnd = proceed(chessBoard);
        } while (isEnd);
    }

    private static boolean proceed(ChessBoard chessBoard) {
        GameState gameState;
        List<String> stateInput = Arrays.asList(InputView.inputState().split(" "));
        List<Square> moveSquares = getMoveSquare(stateInput);
        gameState = GameState.of(stateInput.get(0));
        checkInput(gameState);
        if (gameState.equals(GameState.END)) {
            return false;
        }
        if (gameState.equals(GameState.MOVE)) {
            if (!chessBoard.movePiece(moveSquares)) {
                OutputView.printCanNotMoveMessage();
            }
        }
        OutputView.printChessBoard(chessBoard.getChessBoard());
        return true;
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
            moveSquares = getMoveSquare(Arrays.asList(InputView.inputState().split(" ")));
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
