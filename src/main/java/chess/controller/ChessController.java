package chess.controller;

import chess.model.domain.board.ChessGame;
import chess.model.domain.board.TeamScore;
import chess.model.domain.state.GameState;
import chess.model.domain.state.GameStateAndMoveSquare;
import chess.model.domain.state.MoveSquare;
import chess.model.domain.state.MoveState;
import chess.model.dto.ChessGameDto;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public static void run() {
        ChessGame chessGame = new ChessGame();
        start(chessGame);
        proceed(chessGame);
    }

    private static void start(ChessGame chessGame) {
        OutputView.printStartInfo();
        GameState gameState = GameState.of(InputView.inputGameState());
        if (gameState != GameState.START) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
        }
        OutputView.printChessBoard(new ChessGameDto(chessGame));
    }

    private static void proceed(ChessGame chessGame) {
        GameState gameState;
        do {
            GameStateAndMoveSquare gameStateAndMoveSquare
                = new GameStateAndMoveSquare(InputView.inputGameState());
            proceedByGameState(chessGame, gameStateAndMoveSquare);
            gameState = getGameStateByKingCaptured(chessGame, gameStateAndMoveSquare);
        } while (gameState.isContinue());
    }

    private static void proceedByGameState(ChessGame chessGame,
        GameStateAndMoveSquare gameStateAndMoveSquare) {
        if (gameStateAndMoveSquare.isSameState(GameState.START)) {
            OutputView.printCanNotStart();
        }
        if (gameStateAndMoveSquare.isSameState(GameState.MOVE)) {
            move(chessGame, gameStateAndMoveSquare);
        }
        if (gameStateAndMoveSquare.isSameState(GameState.STATUS)
            || gameStateAndMoveSquare.isSameState(GameState.END)) {
            printScoreAndWinners(chessGame);
        }
    }

    private static GameState getGameStateByKingCaptured(ChessGame chessGame,
        GameStateAndMoveSquare gameStateAndMoveSquare) {
        if (chessGame.isKingCaptured()) {
            return GameState.END;
        }
        return gameStateAndMoveSquare.getGameState();
    }

    private static void move(ChessGame chessGame, GameStateAndMoveSquare gameStateAndMoveSquare) {
        MoveSquare moveSquare = gameStateAndMoveSquare.getMoveSquare();
        MoveState moveState = chessGame.movePieceWhenCanMove(moveSquare);
        if (moveState == MoveState.SUCCESS) {
            OutputView.printChessBoard(new ChessGameDto(chessGame));
            return;
        }
        if (moveState == MoveState.SUCCESS_BUT_PAWN_PROMOTION
            || moveState == MoveState.FAIL_MUST_PAWN_PROMOTION) {
            changePawn(chessGame);
            return;
        }
        if (moveState == MoveState.FAIL_NO_PIECE) {
            OutputView.printNoPiece();
            return;
        }
        if (moveState == MoveState.FAIL_NOT_ORDER) {
            OutputView.printNotMyTurn(chessGame.getGameTurn());
            return;
        }
        if (moveState == MoveState.KING_CAPTURED) {
            OutputView.printWinner(chessGame.getWinnerTurn());
            return;
        }
        OutputView.printCanNotMove();
    }

    private static void changePawn(ChessGame chessGame) {
        MoveState moveState;
        do {
            OutputView.print("폰 재입력 요망");
            moveState = chessGame.promotion(InputView.inputChangeType());
            OutputView.printChessBoard(new ChessGameDto(chessGame));
        } while (moveState != MoveState.SUCCESS);
    }

    private static void printScoreAndWinners(ChessGame chessGame) {
        TeamScore teamScore = chessGame.getTeamScore();
        OutputView.printScore(teamScore.getTeamScore());
        OutputView.printWinners(teamScore.getWinners());
    }
}
