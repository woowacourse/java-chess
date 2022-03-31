package chess.controller;

import chess.domain.ChessGame;
import chess.domain.GameStatus;
import chess.domain.board.BoardGenerationStrategy;
import chess.domain.position.Position;
import chess.view.OutputView;

public class ChessController {

    private final ChessGame chessGame;

    public ChessController(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public boolean isContinue() {
        if (chessGame.isCheck()) {
            OutputView.printMessage("현재 check 상황입니다.");
        }
        GameStatus gameStatus = chessGame.checkGameStatus();
        return !gameStatus.isEnd();
    }

    public boolean isReady() {
        GameStatus gameStatus = chessGame.checkGameStatus();
        return gameStatus.isReady();
    }

    public void start(BoardGenerationStrategy strategy) {
        chessGame.startGame(strategy);
        printBoard();
    }

    public void move(Position from, Position to){
        chessGame.move(from,to);
        printBoard();
    }

    public void makeEnd() {
        OutputView.printStatus(chessGame.stepGame());
    }

    public void createStatus() {
        OutputView.printStatus(chessGame.createResult());
    }

    public void printBoard() {
        OutputView.printBoard(chessGame.getBoard());
    }
}
