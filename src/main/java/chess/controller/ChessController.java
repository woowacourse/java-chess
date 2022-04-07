package chess.controller;

import chess.domain.ChessGame;
import chess.domain.GameStatus;
import chess.domain.board.strategy.BoardGenerationStrategy;
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

    public void checkReady() {
        GameStatus gameStatus = chessGame.checkGameStatus();
        if (gameStatus.isReady()) {
            throw new IllegalArgumentException("체스 게임을 시작해야 합니다.");
        }
    }

    public void start(BoardGenerationStrategy strategy) {
        chessGame.startGame(strategy);
        printBoard();
    }

    public void move(Position from, Position to) {
        chessGame.move(from, to);
        printBoard();
    }

    public void end() {
        OutputView.printStatus(chessGame.stepGame());
    }

    public void status() {
        OutputView.printStatus(chessGame.createResult());
    }

    public void printBoard() {
        OutputView.printBoard(chessGame.getBoard());
    }
}
