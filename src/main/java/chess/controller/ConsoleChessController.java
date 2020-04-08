package chess.controller;

import chess.domain.*;
import chess.domain.piece.Color;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleChessController implements Controller{

    ChessBoard chessBoard;

    public ConsoleChessController(){
        chessBoard = new ChessBoard("id", Color.WHITE);
    }

    @Override
    public void run() {
        OutputView.printGameInformation();
        init();
        do {
            OutputView.printChessBoard(chessBoard.getChessBoard());
        } while (proceed());
        end();
    }

    private void init() {
        GameState gameState = GameState.of(InputView.inputState());
        if (GameState.END == gameState) {
            end();
        }
        if (GameState.START == gameState) {
            return;
        }
        OutputView.printStartGameFirstMessage();
        init();
    }

    private boolean proceed() {
        String inputState = InputView.inputState();
        MoveState moveState = chessBoard.getMoveState();
        if (moveState.isMove(inputState)) {
            moveState.move(inputState, chessBoard);
            return !chessBoard.isKingCaptured();
        }
        if (GameState.START == GameState.of(inputState)) {
            OutputView.printAlreadyGameStartedMessage();
            return true;
        }
        if (GameState.STATUS == GameState.of(inputState)) {
            OutputView.printStatus(TeamScore.calculateTeamScore(chessBoard.getChessBoard()));
            OutputView.printWinner(Winner.getWinners(chessBoard.getChessBoard()));
            return true;
        }
        if (GameState.END == GameState.of(inputState)) {
            return false;
        }
        OutputView.printInputError();
        return true;
    }

    private void end() {
        showResult();
    }

    private void showResult() {
        OutputView.printStatus(TeamScore.calculateTeamScore(chessBoard.getChessBoard()));
        OutputView.printWinner(Winner.getWinners(chessBoard.getChessBoard()));
    }
}
