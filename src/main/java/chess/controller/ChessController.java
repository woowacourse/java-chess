package chess.controller;

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
    private ChessBoard chessBoard;
    private boolean isReady = true;

    public ChessController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        outputView.printGuideMessage();
        while (true) {
            isReady = retryPlayIfCommandIllegal();
        }
    }

    private boolean retryPlayIfCommandIllegal() {
        try {
            return executeInputCommand();
        } catch (final IllegalStateException | IllegalArgumentException exception) {
            outputView.printInputErrorMessage(exception.getMessage());
            return retryPlayIfCommandIllegal();
        }
    }

    private boolean executeInputCommand() {
        CommandRequest commandRequest = inputView.requestGameCommand();
        Command command = commandRequest.getCommand();
        validateRunningStatus(command);
        if (command == Command.START) {
            return start();
        }
        if (command == Command.MOVE) {
            return move(commandRequest);
        }
        initializeChessBoard();
        outputView.printGuideMessage();
        return true;
    }

    private void validateRunningStatus(Command command) {
        if (isReady && (command != Command.START)) {
            throw new IllegalArgumentException("아직 게임이 실행중이지 않습니다.");
        }
        if (!isReady && (command == Command.START)) {
            throw new IllegalArgumentException("이미 게임이 실행중입니다.");
        }
    }

    private boolean start() {
        initializeChessBoard();
        outputView.printBoard(BoardConverter.convertToBoard(chessBoard.piecesByPosition()));
        return false;
    }

    private boolean move(CommandRequest commandRequest) {
        chessBoard.move(Position.from(commandRequest.getSourceCoordinate()),
                Position.from(commandRequest.getDestinationCoordinate()));
        outputView.printBoard(BoardConverter.convertToBoard(chessBoard.piecesByPosition()));
        return false;
    }

    private void initializeChessBoard() {
        chessBoard = new ChessBoard(Camp.WHITE, Camp::transfer);
    }

}
