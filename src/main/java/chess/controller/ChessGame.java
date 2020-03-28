package chess.controller;

import chess.domain.GameState;
import chess.domain.board.BoardSquare;
import chess.domain.board.ChessBoard;
import chess.domain.board.TeamScore;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        String input = InputView.inputGameState();
        List<BoardSquare> boardSquares = new ArrayList<>();
        if (input.length() == 10) {
            List<String> inputs = Arrays.asList(input.split(" "));
            input = inputs.get(0);
            boardSquares.add(BoardSquare.of(inputs.get(1)));
            boardSquares.add(BoardSquare.of(inputs.get(2)));
        }
        GameState gameState = GameState.of(input);
        proceedByGameState(chessBoard, boardSquares, gameState);
        gameState = getGameStateByKingCaptured(chessBoard, gameState);
        return gameState.isContinue();
    }

    private static void proceedByGameState(ChessBoard chessBoard, List<BoardSquare> boardSquares,
        GameState gameState) {
        if (gameState == GameState.START) {
            OutputView.printCanNotStart();
        }
        if (gameState == GameState.MOVE) {
            move(chessBoard, boardSquares);
        }
        if (gameState == GameState.STATUS || gameState == GameState.END) {
            printScoreAndWinners(chessBoard);
        }
    }

    private static GameState getGameStateByKingCaptured(ChessBoard chessBoard,
        GameState gameState) {
        if (chessBoard.isKingCaptured()) {
            OutputView.printWinner(chessBoard.getWinnerTurn());
            gameState = GameState.END;
        }
        return gameState;
    }

    private static void move(ChessBoard chessBoard, List<BoardSquare> boardSquares) {
        if (chessBoard.movePieceWhenCanMove(boardSquares)) {
            OutputView.printChessBoard(chessBoard);
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
