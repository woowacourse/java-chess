package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.position.Column;
import chess.domain.board.position.Position;
import chess.domain.board.position.Row;
import chess.domain.game.ChessGame;
import chess.view.Command;
import chess.view.InputView;
import chess.view.MoveRequestDto;
import chess.view.OutputView;
import chess.view.mapper.ColumnMapper;
import chess.view.mapper.RowMapper;

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

        chessGame.movePiece(from, to);
        outputView.printBoard(board);
    }

    private Position createPosition(String requestColumn, String requestRow) {
        Column column = ColumnMapper.findByInputValue(requestColumn);
        Row row = RowMapper.findByInputValue(requestRow);
        return new Position(row, column);
    }
}
