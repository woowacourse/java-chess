package chess.domain;

import chess.domain.command.*;
import chess.domain.piece.info.Color;
import chess.domain.piece.CurrentPieces;
import chess.domain.piece.Piece;
import chess.domain.piece.info.Position;

import java.util.List;

public class ChessGame {
    private static final String TURN_ERROR = "[ERROR] 현재 턴이 아닌 말은 움직일 수 없습니다.";

    private Command command;
    private final ChessBoard chessBoard;
    private Color turn;

    public ChessGame() {
        chessBoard = ChessBoard.generate();
        turn = Color.WHITE;
    }

//    //public CurrentPieces getCurrentPieces() {
//        return currentPieces;
//    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

//    public void play(List<String> sourceTarget) {
//        Position source = Position.of(sourceTarget.get(0).charAt(0), sourceTarget.get(0).charAt(1));
//        Position target = Position.of(sourceTarget.get(1).charAt(0), sourceTarget.get(1).charAt(1));
//        Piece sourcePiece = currentPieces.findByPosition(source);
//        validateTurn(sourcePiece);
//        sourcePiece.move(target, currentPieces);
//        next();
//    }

    private void next() {
        this.turn = turn.reverse();
    }

    private void validateTurn(Piece sourcePiece) {
        if (!sourcePiece.isSameColor(turn)) {
            throw new IllegalArgumentException(TURN_ERROR);
        }
    }

//    public boolean isRunnable(CommandType commandType) {
//        return !(commandType == CommandType.END) && currentPieces.isAliveAllKings();
//    }

    public boolean isRunnable() {
        return chessBoard.isAliveAllKings();
    }

    public void selectFirstCommand(String inputCommand) {
        command = CommandFactory.selectFirstCommand(inputCommand);
    }

    public boolean isStart() {
        return command instanceof Start;
    }

    public void selectRunningCommand(String inputCommand) {
        if (!(command instanceof Waiting)) {
            command = command.changeWaiting();
        }
        command = command.changeRunningCommand(inputCommand);

    }

    public boolean isMove() {
        return command instanceof Move;
    }

    public void move() {
        if (isMove()) {
            command = command.move(chessBoard, turn);
            next();
        }
    }

    public boolean isEnd() {
        return command instanceof End;
    }

    public boolean isStatus() {
        return command instanceof Status;
    }
}
