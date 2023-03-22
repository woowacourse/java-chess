package chess.controller;

import chess.service.ChessGame;
import chess.view.OutputView;

public class MoveCommand implements Command {
    private static final int SOURCE_INDEX_OF_MOVE_COMMAND = 1;
    private static final int TARGET_INDEX_OF_MOVE_COMMAND = 2;
    private static final String DELIMITER_OF_MOVE_COMMAND = " ";

    private final OutputView outputView;
    private final ChessGame chessGame;

    public MoveCommand(final OutputView outputView, final ChessGame chessGame) {
        this.outputView = outputView;
        this.chessGame = chessGame;
    }

    @Override
    public void execute(final String command) {
        String[] commands = command.split(DELIMITER_OF_MOVE_COMMAND);
        validateMoveCommand(commands);
        String sourceSquare = commands[SOURCE_INDEX_OF_MOVE_COMMAND];
        String targetSquare = commands[TARGET_INDEX_OF_MOVE_COMMAND];
        chessGame.move(sourceSquare, targetSquare);
        outputView.printBoard(chessGame.findChessBoard());
    }

    private void validateMoveCommand(final String[] commands) {
        if (commands.length != 3) {
            throw new IllegalArgumentException("이동할 기물과 이동할 위치를 입력해주세요.");
        }
    }
}
