package chess.controller;

import static chess.controller.GameStatus.READY;
import static chess.controller.GameStatus.RUNNING;

import chess.controller.converter.BoardConverter;
import chess.domain.Camp;
import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.dto.CommandRequest;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;
    private GameStatus gameStatus;
    private ChessBoard chessBoard;

    public ChessController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.gameStatus = READY;
    }

    public void run() {
        outputView.printGuideMessage();
        // TODO 프로그램 종료 명령어 임의 추가?
        while (true) {
            gameStatus = retryExecuteIfCommandIllegal();
        }
    }

    private GameStatus retryExecuteIfCommandIllegal() {
        try {
            return executeInputCommand();
        } catch (final IllegalStateException | IllegalArgumentException exception) {
            outputView.printInputErrorMessage(exception.getMessage());
            return retryExecuteIfCommandIllegal();
        }
    }

    private GameStatus executeInputCommand() {
        CommandRequest commandRequest = inputView.requestGameCommand();
        Command command = commandRequest.getCommand();
        gameStatus.validateCommand(command);
        if (command == Command.START) {
            return start();
        }
        if (command == Command.MOVE) {
            return move(commandRequest);
        }
        return end();
    }

    private GameStatus start() {
        initializeChessBoard();
        outputView.printBoard(BoardConverter.convertToBoard(chessBoard.piecesByPosition()));
        return RUNNING;
    }

    private GameStatus move(CommandRequest commandRequest) {
        chessBoard.move(Position.from(commandRequest.getSourceCoordinate()),
                Position.from(commandRequest.getDestinationCoordinate()));
        outputView.printBoard(BoardConverter.convertToBoard(chessBoard.piecesByPosition()));
        return RUNNING;
    }

    private GameStatus end() {
        initializeChessBoard();
        outputView.printGuideMessage();
        return READY;
    }

    private void initializeChessBoard() {
        chessBoard = new ChessBoard(Camp.WHITE, Camp::transfer);
    }

}
