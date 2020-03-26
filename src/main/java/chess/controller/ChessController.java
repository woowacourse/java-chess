package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.GameState;
import chess.domain.Square;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessController {

    public static void run() {
        OutputView.printStartGame();
        OutputView.printStartEndOption();
        ChessBoard chessBoard = new ChessBoard();

        start(chessBoard);
        String input;
        GameState gameState;
        boolean blackTurn = false;
        do {
            input = InputView.inputStart();
            List<Square> squares = new ArrayList<>();
            if (input.length() == 10) {
                List<String> inputs = Arrays.asList(input.split(" "));
                input = inputs.get(0);
                squares.add(Square.of(inputs.get(1)));
                squares.add(Square.of(inputs.get(2)));
            }
            gameState = GameState.of(input);
            if (gameState == GameState.START) {
                throw new IllegalArgumentException("왜 시작하세요");
            }
            if (gameState == GameState.MOVE) {
                if (proceed(chessBoard, squares, blackTurn)) {
                    blackTurn = !blackTurn;
                }
            }
        } while (gameState != GameState.END);
    }

    private static boolean proceed(ChessBoard chessBoard, List<Square> squares, boolean blackTurn) {
        if (chessBoard.canMove(squares, blackTurn)) {
            chessBoard.movePiece(squares);
            OutputView.printChessBoard(chessBoard);
            return true;
        }
        OutputView.printCanNotMove();
        return false;
    }

    private static void start(ChessBoard chessBoard) {
        GameState gameState = GameState.of(InputView.inputStart());
        if (gameState != GameState.START) {
            throw new IllegalArgumentException("게임을 시작해야 합니다");
        }
        OutputView.printChessBoard(chessBoard);
    }
}
