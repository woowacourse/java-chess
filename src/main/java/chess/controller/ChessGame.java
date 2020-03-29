package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.board.TeamScore;
import chess.domain.state.GameState;
import chess.domain.state.GameStateAndMoveSquare;
import chess.domain.state.MoveSquare;
import chess.exceptions.ChangePawnException;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {

    public static void run() {
        ChessBoard chessBoard = new ChessBoard();
        start(chessBoard);
        proceed(chessBoard);
    }

    private static void start(ChessBoard chessBoard) {
        OutputView.printStartInfo();
        GameState gameState = GameState.of(InputView.inputGameState());
        if (gameState != GameState.START) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
        }
        OutputView.printChessBoard(chessBoard);
    }

    private static void proceed(ChessBoard chessBoard) {
        GameState gameState;
        do {
            GameStateAndMoveSquare gameStateAndMoveSquare
                = new GameStateAndMoveSquare(InputView.inputGameState());
            proceedByGameState(chessBoard, gameStateAndMoveSquare);
            gameState = getGameStateByKingCaptured(chessBoard, gameStateAndMoveSquare);
        } while (gameState.isContinue());
    }

    private static void proceedByGameState(ChessBoard chessBoard,
        GameStateAndMoveSquare gameStateAndMoveSquare) {
        if (gameStateAndMoveSquare.isSameState(GameState.START)) {
            OutputView.printCanNotStart();
        }
        if (gameStateAndMoveSquare.isSameState(GameState.MOVE)) {
            move(chessBoard, gameStateAndMoveSquare);
        }
        if (gameStateAndMoveSquare.isSameState(GameState.STATUS)
            || gameStateAndMoveSquare.isSameState(GameState.END)) {
            printScoreAndWinners(chessBoard);
        }
    }

    private static GameState getGameStateByKingCaptured(ChessBoard chessBoard,
        GameStateAndMoveSquare gameStateAndMoveSquare) {
        if (chessBoard.isKingCaptured()) {
            OutputView.printWinner(chessBoard.getWinnerTurn());
            return GameState.END;
        }
        return gameStateAndMoveSquare.getGameState();
    }

    private static void move(ChessBoard chessBoard, GameStateAndMoveSquare gameStateAndMoveSquare) {
        MoveSquare moveSquare = gameStateAndMoveSquare.getMoveSquare();
        try {
            if (chessBoard.movePieceWhenCanMove(moveSquare)) {
                OutputView.printChessBoard(chessBoard);
                return;
            }
        } catch (ChangePawnException cpe) {
            OutputView.printExceptionMessage(cpe.getMessage());
            chessBoard.changeFinishPawn(InputView.inputChangeType());
            OutputView.printChessBoard(chessBoard);
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
