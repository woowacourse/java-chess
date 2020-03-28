package chess;

import chess.View.ChessInputView;
import chess.View.ChessOutputView;
import chess.domain.chessBoard.CatchKingException;
import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessBoard.ChessBoardFactory;
import chess.domain.command.Command;

public class ChessController {
    public static void run() {
        ChessBoard chessBoard = new ChessBoard(ChessBoardFactory.create());
        ChessOutputView.chessStart();

        Command command = Command.of(ChessInputView.inputCommand());
        checkStartCommand(chessBoard, command);

        try {
            gameStart(chessBoard, command);
        } catch (CatchKingException e) {
            ChessOutputView.printCaughtKing(e.getMessage(), chessBoard);
        }
    }

    private static void gameStart(ChessBoard chessBoard, Command command) {
        while (!(command.isEnd())) {
            command = Command.of(ChessInputView.inputCommand(chessBoard));
            if (command.isMove()) {
                chessBoard.move(command.sourcePosition(), command.targetPosition());
                ChessOutputView.printChessBoard(chessBoard);
            }
            if (command.isStatus()) {
                ChessOutputView.scoreOf(chessBoard.getPlayerColor(), chessBoard);
            }
        }
    }

    private static void checkStartCommand(ChessBoard chessBoard, Command command) {
        try {
            if (command.isStart()) {
                ChessOutputView.printChessBoard(chessBoard);
                return;
            }
            if (!command.isEnd()) {
                throw new IllegalArgumentException("start 해주세요.");
            }
        } catch (RuntimeException e) {
            ChessOutputView.printError(e.getMessage());
            command = Command.of(ChessInputView.inputCommand());
            checkStartCommand(chessBoard, command);
        }
    }
}

