package chess.controller;

import chess.command.Command;
import chess.command.CommandType;
import chess.domain.Turn;
import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardMaker;
import chess.domain.position.TerminalPosition;
import chess.domain.square.piece.Color;
import chess.util.ExceptionRetryHandler;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.TerminalPositionView;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        Command command = receiveCommand();
        validateStartOrEnd(command.getType());
        if (command.getType() == CommandType.START) {
            startGame();
        }
    }

    private Command receiveCommand() {
        return ExceptionRetryHandler.handle(inputView::readCommand);
    }

    private static void validateStartOrEnd(CommandType commandType) {
        if (commandType != CommandType.START && commandType != CommandType.END) {
            throw new IllegalArgumentException("게임을 시작하거나 끝내는 것만 가능합니다.");
        }
    }

    // TODO: 게임을 진행하는 로직(ex: turn 관리)을 ChessGame으로 분리
    private void startGame() {
        ChessBoard chessBoard = makeChessBoard();
        Turn turn = new Turn(Color.WHITE);

        ExceptionRetryHandler.handle(() -> processGame(chessBoard, turn));
    }

    private ChessBoard makeChessBoard() {
        ChessBoardMaker chessBoardMaker = new ChessBoardMaker();
        ChessBoard chessBoard = chessBoardMaker.make();

        outputView.printChessBoard(chessBoard.getSquares());
        return chessBoard;
    }

    private void processGame(ChessBoard chessBoard, Turn turn) {
        Command command = receiveProcessCommand();

        while (command.getType() != CommandType.END) {
            tryTurn(command, chessBoard, turn);
            command = receiveProcessCommand();
        }
    }

    private Command receiveProcessCommand() {
        Command command = inputView.readCommand();
        validateNotStart(command.getType());
        return command;
    }

    private void validateNotStart(CommandType commandType) {
        if (commandType == CommandType.START) {
            throw new IllegalArgumentException("게임이 이미 진행중입니다.");
        }
    }

    private void tryTurn(Command command, ChessBoard chessBoard, Turn turn) {
        if (command.getType() == CommandType.MOVE) {
            movePiece(command, chessBoard, turn);
            turn.change();
        }
    }

    private void movePiece(Command command, ChessBoard chessBoard, Turn turn) {
        TerminalPosition terminalPosition = TerminalPositionView.of(command.getArguments());
        chessBoard.move(terminalPosition, turn.getCurrentTurn());

        outputView.printChessBoard(chessBoard.getSquares());
    }
}
