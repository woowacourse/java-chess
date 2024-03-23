package chess.controller;

import chess.domain.Board;
import chess.domain.ChessGame;
import chess.domain.Column;
import chess.domain.Position;
import chess.domain.Row;
import chess.view.Command;
import chess.view.InputView;
import chess.view.MoveRequestDto;
import chess.view.OutputView;
import chess.view.mapper.ColumnMapper;
import chess.view.mapper.RowMapper;
import java.util.List;

public class ChessGameController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final ChessGame chessGame = new ChessGame(new Board());

    public void run() {
        outputView.printStartMessage();
        Board board = chessGame.getBoard();
        process(board);
    }

    private void process(Board board) {
        boolean isRunning = true;
        while (isRunning) {
            isRunning = processGame(board);
        }
    }

    private boolean processGame(Board board) {
        try {
            Command command = inputView.readCommend();
            if (command == Command.START) {
                handleStartCommend(board);
            }
            if (command == Command.MOVE) {
                handleMoveCommend(board);
            }
            return command != Command.END;
        } catch (IllegalArgumentException error) {
            outputView.printError(error);
            process(board);
            return false;
        }
    }

    private void handleStartCommend(Board board) {
        outputView.printBoard(board);
    }

    private void handleMoveCommend(Board board) {
        MoveRequestDto moveRequestDto = inputView.readPositions();
        Position from = createPosition(moveRequestDto.getFromColumn(), moveRequestDto.getFromRow());
        Position to = createPosition(moveRequestDto.getToColumn(), moveRequestDto.getToRow());

        List<Position> movablePositions = chessGame.generateMovablePositions(from);
        chessGame.movePiece(movablePositions, from, to);

        outputView.printBoard(board);
    }

    private Position createPosition(String requestColumn, String requestRow) {
        Column column = ColumnMapper.findByInputValue(requestColumn);
        Row row = RowMapper.findByInputValue(requestRow);
        return new Position(row, column);
    }
}
