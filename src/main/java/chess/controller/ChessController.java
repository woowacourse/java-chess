package chess.controller;

import chess.command.Command;
import chess.command.CommandType;
import chess.domain.CurrentTurn;
import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardMaker;
import chess.domain.position.PathFinder;
import chess.domain.square.piece.Color;
import chess.util.ExceptionRetryHandler;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        Command firstCommand = ExceptionRetryHandler.handle(this::readFirstCommand);
        if (firstCommand.type() == CommandType.START) {
            startGame();
        }
    }

    private Command readFirstCommand() {
        Command command = inputView.readCommand();
        if (command.type() != CommandType.START && command.type() != CommandType.END) {
            throw new IllegalArgumentException("첫 커맨드는 start 또는 end만 가능합니다.");
        }
        return command;
    }

    private void startGame() {
        ChessBoard chessBoard = makeChessBoard();

        ExceptionRetryHandler.handle(() -> executeCommandsUntilEnd(chessBoard));
    }

    private ChessBoard makeChessBoard() {
        ChessBoardMaker chessBoardMaker = new ChessBoardMaker();
        ChessBoard chessBoard = chessBoardMaker.make(new CurrentTurn(Color.WHITE));

        outputView.printChessBoard(chessBoard.getSquares());
        return chessBoard;
    }

    private void executeCommandsUntilEnd(ChessBoard chessBoard) {
        Command command = inputView.readCommand();

        while (command.type() != CommandType.END) {
            executeCommand(command, chessBoard);
            command = inputView.readCommand();
        }
    }

    private void executeCommand(Command command, ChessBoard chessBoard) {
        if (command.type() == CommandType.START) {
            throw new IllegalArgumentException("이미 게임이 진행중이므로 start 외의 커맨드를 입력해 주세요.");
        }
        if (command.type() == CommandType.MOVE) {
            movePlayerPiece(command, chessBoard);
        }
    }

    private void movePlayerPiece(Command command, ChessBoard chessBoard) {
        PathFinder pathFinder = new PathFinder(command.getStartPosition(), command.getTargetPosition());
        chessBoard.move(pathFinder);

        outputView.printChessBoard(chessBoard.getSquares());
    }
}
