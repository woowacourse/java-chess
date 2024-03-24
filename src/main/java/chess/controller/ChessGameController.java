package chess.controller;

import chess.domain.BoardInitializer;
import chess.domain.ChessGame;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.view.ColumnMapper;
import chess.view.Commend;
import chess.view.CommandDto;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.RowMapper;

public class ChessGameController {
    private static final int INPUT_COLUMN_INDEX = 0;
    private static final int INPUT_ROW_INDEX = 1;

    private final InputView inputView;
    private final OutputView outputView;

    public ChessGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        process();
    }

    private void process() {
        boolean isRunning = true;
        ChessGame chessGame = new ChessGame(BoardInitializer.initialize());
        while (isRunning) {
            isRunning = processGame(chessGame);
        }
    }

    private boolean processGame(ChessGame chessGame) {
        try {
            CommandDto commandDto = inputView.readCommend();
            Commend commend = commandDto.commend();
            if (commend == Commend.START) {
                handleStartCommend(chessGame);
            }
            if (commend == Commend.MOVE) {
                handleMoveCommend(chessGame, commandDto);
            }
            if (commend == Commend.END) {
                return false;
            }
            return true;
        } catch (IllegalArgumentException error) {
            outputView.printError(error);
            return processGame(chessGame);
        }
    }

    private void handleStartCommend(ChessGame chessGame) {
        outputView.printBoard(chessGame.getBoard());
    }

    private void handleMoveCommend(ChessGame chessGame, CommandDto commandDto) {
        chessGame.handleMove(createPositionByString(commandDto.from()), createPositionByString(commandDto.to()));
        outputView.printBoard(chessGame.getBoard());
    }

    private Position createPositionByString(String positionValue) {
        Row row = RowMapper.findByInputValue(positionValue.split("")[INPUT_ROW_INDEX]);
        Column column = ColumnMapper.findByInputValue(positionValue.split("")[INPUT_COLUMN_INDEX]);
        return new Position(row, column);
    }
}
