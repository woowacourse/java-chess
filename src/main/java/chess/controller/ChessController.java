package chess.controller;

import chess.domain.board.Game;
import chess.domain.board.TeamScore;
import chess.domain.state.GameState;
import chess.domain.state.GameStateAndMoveSquare;
import chess.domain.state.MoveSquare;
import chess.domain.state.MoveState;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public static void run() {
        Game game = new Game();
        start(game);
        proceed(game);
    }

    private static void start(Game game) {
        OutputView.printStartInfo();
        GameState gameState = GameState.of(InputView.inputGameState());
        if (gameState != GameState.START) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
        }
        OutputView.printChessBoard(game);
    }

    private static void proceed(Game game) {
        GameState gameState;
        do {
            GameStateAndMoveSquare gameStateAndMoveSquare
                = new GameStateAndMoveSquare(InputView.inputGameState());
            proceedByGameState(game, gameStateAndMoveSquare);
            gameState = getGameStateByKingCaptured(game, gameStateAndMoveSquare);
        } while (gameState.isContinue());
    }

    private static void proceedByGameState(Game game,
        GameStateAndMoveSquare gameStateAndMoveSquare) {
        if (gameStateAndMoveSquare.isSameState(GameState.START)) {
            OutputView.printCanNotStart();
        }
        if (gameStateAndMoveSquare.isSameState(GameState.MOVE)) {
            move(game, gameStateAndMoveSquare);
        }
        if (gameStateAndMoveSquare.isSameState(GameState.STATUS)
            || gameStateAndMoveSquare.isSameState(GameState.END)) {
            printScoreAndWinners(game);
        }
    }

    private static GameState getGameStateByKingCaptured(Game game,
        GameStateAndMoveSquare gameStateAndMoveSquare) {
        if (game.isKingCaptured()) {
            return GameState.END;
        }
        return gameStateAndMoveSquare.getGameState();
    }

    private static void move(Game game, GameStateAndMoveSquare gameStateAndMoveSquare) {
        MoveSquare moveSquare = gameStateAndMoveSquare.getMoveSquare();
        MoveState moveState = game.movePieceWhenCanMove(moveSquare);
        if (moveState == MoveState.SUCCESS) {
            OutputView.printChessBoard(game);
            return;
        }
        if (moveState == MoveState.SUCCESS_BUT_PAWN_PROMOTION
            || moveState == MoveState.FAIL_MUST_PAWN_PROMOTION) {
            changePawn(game);
            return;
        }
        if (moveState == MoveState.FAIL_NO_PIECE) {
            OutputView.printNoPiece();
            return;
        }
        if (moveState == MoveState.FAIL_NOT_ORDER) {
            OutputView.printNotMyTurn(game.getGameTurn());
            return;
        }
        if (moveState == MoveState.KING_CAPTURED) {
            OutputView.printWinner(game.getWinnerTurn());
            return;
        }
        OutputView.printCanNotMove();
    }

    private static void changePawn(Game game) {
        MoveState moveState;
        do {
            OutputView.print("폰 재입력 요망");
            moveState = game.promotion(InputView.inputChangeType());
            OutputView.printChessBoard(game);
        } while (moveState != MoveState.SUCCESS);
    }

    private static void printScoreAndWinners(Game game) {
        TeamScore teamScore = game.getTeamScore();
        OutputView.printScore(teamScore.getTeamScore());
        OutputView.printWinners(teamScore.getWinners());
    }
}
