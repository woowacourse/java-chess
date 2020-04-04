package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.GameState;
import chess.domain.piece.Color;
import chess.domain.square.Square;
import chess.utils.InputParser;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessController {

    public static void run() {
        ChessBoard chessBoard = new ChessBoard();
        startGame(chessBoard);
        proceedGame(chessBoard);
    }

    private static void startGame(ChessBoard chessBoard) {
        OutputView.printBeginGame();
        GameState gameState = InputParser.parseState(InputView.inputCommand());
        if (gameState != GameState.START) {
            throw new IllegalArgumentException("먼저 게임을 시작해야 합니다");
        }
        OutputView.printChessBoard(chessBoard);
    }

    private static void proceedGame(ChessBoard chessBoard) {
        GameState gameState;
        boolean blackTurn = false;
        List<Square> moveSourceDestination;
        String input;
        while (true) {
            input = InputView.inputCommand();
            gameState = InputParser.parseState(input);
            if (gameState == GameState.START) {
                OutputView.printAlreadyStarted();
                continue;
            }
            if (gameState == GameState.END) {
                printScoreAndWinners(chessBoard);
                break;
            }
            if (gameState == GameState.STATUS) {
                printScoreAndWinners(chessBoard);
            }
            if (gameState == GameState.MOVE) {
                moveSourceDestination = InputParser.parseMoveSourceDestination(input);
                if (isMoved(chessBoard, moveSourceDestination, blackTurn)) {
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
        }
    }

    private static void printScoreAndWinners(ChessBoard chessBoard) {
        OutputView.printScore(chessBoard.getTeamScore());
        OutputView.printWinners(chessBoard.getWinners());
    }

    private static boolean isMoved(ChessBoard chessBoard, List<Square> squares, boolean blackTurn) {
        if (chessBoard.canMove(squares, blackTurn)) {
            chessBoard.movePiece(squares);
            OutputView.printChessBoard(chessBoard);
            return true;
        }
        OutputView.printCanNotMove();
        return false;
    }
}
