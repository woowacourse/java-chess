package chess.domain;

import chess.domain.command.*;
import chess.domain.piece.info.Color;

public class ChessGame {
    private Command command;
    private final ChessBoard chessBoard;
    private Color turn;

    public ChessGame() {
        chessBoard = ChessBoard.generate();
        turn = Color.WHITE;
    }

    private void next() {
        this.turn = turn.reverse();
    }

    public boolean isRunnable() {
        return chessBoard.isAliveAllKings();
    }

    public void selectFirstCommand(String inputCommand) {
        command = CommandFactory.selectFirstCommand(inputCommand);
    }

    public void selectRunningCommand(String inputCommand) {
        if (!isWaiting()) {
            command = command.changeWaiting();
        }
        command = command.changeRunningCommand(inputCommand);

    }

    public void move() {
        if (isMove()) {
            command = command.move(chessBoard, turn);
            next();
        }
    }

    public boolean isMove() {
        return command instanceof Move;
    }

    public boolean isWaiting() {
        return command instanceof Waiting;
    }

    public boolean isStart() {
        return command instanceof Start;
    }

    public boolean isEnd() {
        return command instanceof End;
    }

    public boolean isStatus() {
        return command instanceof Status;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
