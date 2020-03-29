package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.board.TeamScore;
import chess.domain.state.GameState;
import chess.domain.state.MoveSquare;
import chess.domain.state.StateAndSquares;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {

    public static void run() {
        ChessBoard chessBoard = new ChessBoard();
        start(chessBoard);
        while (proceed(chessBoard)) {
        }
    }

    private static void start(ChessBoard chessBoard) {
        OutputView.printStartInfo();
        GameState gameState = GameState.of(InputView.inputGameState());
        if (gameState != GameState.START) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
        }
        OutputView.printChessBoard(chessBoard);
    }

    private static boolean proceed(ChessBoard chessBoard) {
        StateAndSquares stateAndSquares = new StateAndSquares(InputView.inputGameState());
        proceedByGameState(chessBoard, stateAndSquares);
        GameState gameState = getGameStateByKingCaptured(chessBoard, stateAndSquares);
        return gameState.isContinue();
    }

    private static void proceedByGameState(ChessBoard chessBoard, StateAndSquares stateAndSquares) {
        if (stateAndSquares.isSameState(GameState.START)) {
            OutputView.printCanNotStart();
        }
        if (stateAndSquares.isSameState(GameState.MOVE)) {
            move(chessBoard, stateAndSquares);
        }
        if (stateAndSquares.isSameState(GameState.STATUS)
            || stateAndSquares.isSameState(GameState.END)) {
            printScoreAndWinners(chessBoard);
        }
    }

    private static GameState getGameStateByKingCaptured(ChessBoard chessBoard,
        StateAndSquares stateAndSquares) {
        if (chessBoard.isKingCaptured()) {
            OutputView.printWinner(chessBoard.getWinnerTurn());
            return GameState.END;
        }
        return stateAndSquares.getGameState();
    }

    private static void move(ChessBoard chessBoard, StateAndSquares stateAndSquares) {
        MoveSquare moveSquare = stateAndSquares.getMoveSquare();
        if (chessBoard.movePieceWhenCanMove(moveSquare)) {
            OutputView.printChessBoard(chessBoard);
            return;
        }
        if (chessBoard.isNoPiece(moveSquare)) {
            OutputView.printNoPiece();
            return;
        }
        if (chessBoard.isNotMyTurn(moveSquare)) {
            OutputView.printNotMyTurn(chessBoard.getGameTurn());
            return;
        }
        OutputView.printCanNotMove();
    }

    private static void printScoreAndWinners(ChessBoard chessBoard) {
        TeamScore teamScore = chessBoard.getTeamScore();
        OutputView.printScore(teamScore.getTeamScore());
        OutputView.printWinners(teamScore.getWinners());
    }
}
