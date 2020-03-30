package chess;

import chess.View.ChessInputView;
import chess.View.ChessOutputView;
import chess.domain.ChessCalculator;
import chess.domain.chessBoard.CatchKingException;
import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessBoard.ChessBoardFactory;
import chess.domain.command.Command;
import chess.domain.command.CommandType;

public class ChessController {
    public static void run() {
        ChessOutputView.chessStart();
        ChessBoard chessBoard = new ChessBoard(ChessBoardFactory.create());

        Command command = checkStartCommand(ChessInputView.inputCommand());

        try {
            gameStart(chessBoard, command);
        } catch (CatchKingException e) {
            ChessOutputView.printCaughtKing(e.getMessage(), chessBoard);
        }
    }

    private static void gameStart(ChessBoard chessBoard, Command command) {
        CommandType commandType = CommandType.findValueOf(command);

        while (!(commandType.isEnd())) {
            chessBoard = playGame(chessBoard, command, commandType);
            command = Command.from(ChessInputView.inputCommand());
            commandType = CommandType.findValueOf(command);
        }

    }

    private static ChessBoard playGame(ChessBoard chessBoard, Command command, CommandType commandType) {
        switch (commandType) {
            case MOVE: {
                chessBoard = command.commandMoveRun(chessBoard);
                ChessOutputView.printChessBoard(chessBoard);
                break;
            }
            case STATUS: {
                ChessOutputView.scoreOf(chessBoard, ChessCalculator.calculateScoreOf(chessBoard));
                break;
            }
            default: {
                ChessOutputView.printChessBoard(chessBoard);
            }
        }
        return chessBoard;
    }

    private static Command checkStartCommand(String inputCommand) {
        Command command = Command.from(inputCommand);
        CommandType commandType = CommandType.findValueOf(command);

        while (commandType.checkInitialCommand()) {
            ChessOutputView.printError("start 해주세요.");
            command = Command.from(ChessInputView.inputCommand());
        }
        return command;
    }
}

