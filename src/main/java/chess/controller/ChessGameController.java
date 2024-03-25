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

    public void run() {
        ChessGame chessGame = new ChessGame(new Board());
        outputView.printStartMessage();
        process(chessGame);
    }

    private void process(ChessGame chessGame) {
        boolean isRunning = true;
        while (isRunning) {
            isRunning = processGame(chessGame);
        }
    }

    private boolean processGame(ChessGame chessGame) {
        try {
            Command command = inputView.readCommend();
            if (command == Command.START) {
                handleStart(chessGame);
            }
            if (command == Command.MOVE) {
                handleMove(chessGame);
            }
            return command != Command.END;
        } catch (IllegalArgumentException error) {
            outputView.printError(error);
            process(chessGame);
            return false;
        }
    }

    private void handleStart(ChessGame chessGame) {
        outputView.printBoard(chessGame.getBoard());
    }

    private void handleMove(ChessGame chessGame) {
        MoveRequestDto moveRequestDto = inputView.readPositions();
        Position from = createPosition(moveRequestDto.getFromColumn(), moveRequestDto.getFromRow());
        Position to = createPosition(moveRequestDto.getToColumn(), moveRequestDto.getToRow());
        chessGame.movePiece(from, to);
        outputView.printBoard(chessGame.getBoard());
    }

    private Position createPosition(String requestColumn, String requestRow) {
        Column column = ColumnMapper.findByInputValue(requestColumn);
        Row row = RowMapper.findByInputValue(requestRow);
        return new Position(row, column);
    }
}
