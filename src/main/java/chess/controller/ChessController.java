package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.GameState;
import chess.domain.TeamScore;
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
        TeamScore teamScore = new TeamScore();
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
                printScoreAndWinners(chessBoard, teamScore);
                break;
            }
            if (gameState == GameState.STATUS) {
                printScoreAndWinners(chessBoard, teamScore);
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

    private static void printScoreAndWinners(ChessBoard chessBoard, TeamScore teamScore) {
        OutputView.printScore(teamScore.updateTeamScore(chessBoard));
        OutputView.printWinners(teamScore.getWinners());
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
