package chess.controller;

import chess.domain.GameState;
import chess.domain.board.BoardSquare;
import chess.domain.board.ChessBoard;
import chess.domain.piece.Color;
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
        proceed(chessBoard);
    }

    private static void proceed(ChessBoard chessBoard) {
        boolean blackTurn = false;
        while (true) {
            String input = InputView.inputStart();
            List<BoardSquare> boardSquares = new ArrayList<>();
            if (input.length() == 10) {
                List<String> inputs = Arrays.asList(input.split(" "));
                input = inputs.get(0);
                boardSquares.add(BoardSquare.of(inputs.get(1)));
                boardSquares.add(BoardSquare.of(inputs.get(2)));
            }
            GameState gameState = GameState.of(input);
            if (gameState == GameState.START) {
                throw new IllegalArgumentException("왜 시작하세요");
            }
            if (gameState == GameState.MOVE) {
                if (move(chessBoard, boardSquares, blackTurn)) {
                    blackTurn = !blackTurn;
                }
            }
            if (chessBoard.isKingCaptured()) {
                if (blackTurn) {
                    OutputView.printWinner(Color.WHITE);
                    break;
                }
                OutputView.printWinner(Color.BLACK);
                break;
            }
            if (gameState == GameState.END) {
                printScoreAndWinners(chessBoard);
                break;
            }
            if (gameState == GameState.STATUS) {
                printScoreAndWinners(chessBoard);
            }
        }
    }

    private static void printScoreAndWinners(ChessBoard chessBoard) {
        OutputView.printScore(chessBoard.getTeamScore());
        OutputView.printWinners(chessBoard.getWinners());
    }

    private static boolean move(ChessBoard chessBoard, List<BoardSquare> boardSquares,
        boolean blackTurn) {
        if (chessBoard.movePieceWhenCanMove(boardSquares, blackTurn)) {
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
