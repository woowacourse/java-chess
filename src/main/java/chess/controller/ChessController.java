package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.GameState;
import chess.domain.Square;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessController {


    public static void run() {
        List<Square> squares = new ArrayList<>();
        OutputView.printGameInformation();
        ChessBoard chessBoard = new ChessBoard();
        String state = InputView.inputState();
        GameState gameState = GameState.of(state);
        start(chessBoard, gameState);
        if (checkGameState(squares, chessBoard, gameState)) {
            printScoreAndWinners(chessBoard);
        }
    }

    private static boolean checkGameState(List<Square> squares, ChessBoard chessBoard, GameState gameState) {
        String state;
        boolean blackTurn = false;
        while (!isEnd(gameState)) {
            squares.clear();
            state = splitInputWhenMove(InputView.inputState(), squares);
            gameState = GameState.of(state);
            if (gameState == GameState.MOVE) {
                blackTurn = changeTurn(chessBoard, blackTurn, squares);
            }
            if (chessBoard.isKingCaptured()) {
                endWhenKingCaptured(blackTurn);
                return false;
            }
            isStateStatus(chessBoard, gameState);
            isStateStart(gameState);
        }
        return true;
    }

    private static void isStateStatus(ChessBoard chessBoard, GameState gameState) {
        if (gameState == GameState.STATUS) {
            printScoreAndWinners(chessBoard);
        }
    }

    private static void isStateStart(GameState gameState) {
        if (gameState == GameState.START) {
            OutputView.printStartedErrorMessage();
        }
    }

    private static boolean changeTurn(ChessBoard chessBoard, boolean blackTurn, List<Square> squares) {
        if (proceed(chessBoard, squares, blackTurn)) {
            blackTurn = !blackTurn;
        }
        return blackTurn;
    }

    private static boolean isEnd(GameState gameState) {
        return gameState.equals(GameState.END);
    }

    private static void endWhenKingCaptured(boolean blackTurn) {
        if (blackTurn) {
            OutputView.printWinner(Color.WHITE);
            return;
        }
        OutputView.printWinner(Color.BLACK);
    }

    private static String splitInputWhenMove(String input, List<Square> squares) {
        if (input.length() == 10) {
            List<String> inputs = Arrays.asList(input.split(" "));
            input = inputs.get(0);
            squares.add(0, Square.of(inputs.get(1)));
            squares.add(1, Square.of(inputs.get(2)));
        }
        return input;
    }

    private static void printScoreAndWinners(ChessBoard chessBoard) {
        OutputView.printScore(chessBoard.getTeamScore());
        OutputView.printWinners(chessBoard.getWinners());
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

    private static void start(ChessBoard chessBoard, GameState gameState) {
        if (gameState == GameState.END) {
            return;
        }
        if (gameState != GameState.START) {
            throw new IllegalArgumentException("게임을 시작해야 합니다");
        }
        OutputView.printChessBoard(chessBoard);
    }
}
