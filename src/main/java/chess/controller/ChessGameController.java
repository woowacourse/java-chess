package chess.controller;

import chess.domain.Board;
import chess.domain.ChessGame;
import chess.domain.Column;
import chess.domain.Position;
import chess.domain.Row;
import chess.view.ColumnMapper;
import chess.view.Commend;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.RowMapper;
import java.util.List;

public class ChessGameController {
    public static final int COMMEND_INDEX = 0;
    public static final int MOVE_COMMEND_FORMAT_SIZE = 3;
    public static final int MOVE_FROM_INDEX = 1;
    public static final int MOVE_TO_INDEX = 2;
    public static final int INPUT_COLUMN_INDEX = 0;
    public static final int INPUT_ROW_INDEX = 1;

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
            List<String> commendValues = inputView.readCommend();
            Commend commend = Commend.inputToCommend(commendValues.get(COMMEND_INDEX));
            if (commend == Commend.START) {
                handleStartCommend(board);
            }
            if (commend == Commend.MOVE) {
                handleMoveCommend(board, commendValues);
            }
            if (commend == Commend.END) {
                return false;
            }
            return true;
        } catch (IllegalArgumentException error) {
            outputView.printError(error);
            process(board);
            return false;
        }
    }

    private void handleStartCommend(Board board) {
        outputView.printBoard(board);
    }

    private void handleMoveCommend(Board board, List<String> commendValues) {
        if (commendValues.size() != MOVE_COMMEND_FORMAT_SIZE) {
            throw new IllegalArgumentException("게임 이동 입력 형식이 올바르지 않습니다.");
        }
        Position from = createPosition(commendValues, MOVE_FROM_INDEX);
        Position to = createPosition(commendValues, MOVE_TO_INDEX);

        List<Position> movablePositions = chessGame.generateMovablePositions(from);
        chessGame.movePiece(movablePositions, from, to);

        outputView.printBoard(board);
    }

    private Position createPosition(List<String> commendValues, int moveFromIndex) {
        String fromValue = commendValues.get(moveFromIndex);

        Column fromColumn = ColumnMapper.findByInputValue(fromValue.split("")[INPUT_COLUMN_INDEX]);
        Row fromRow = RowMapper.findByInputValue(fromValue.split("")[INPUT_ROW_INDEX]);
        return new Position(fromRow, fromColumn);
    }
}
