package chess.controller;

import static chess.constant.GameCommand.END;
import static chess.view.PositionConverter.convertToSourcePosition;
import static chess.view.PositionConverter.convertToTargetPosition;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.Team;
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
        start();
        Board board = Board.init();
        progress(board);
    }

    private void start() {
        try {
            inputView.readStart();
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e);
            start();
        }
    }

    private void progress(Board board) {
        boolean isPlay = true;
        Team turn = Team.getStartTeam();
        while (isPlay) {
            outputView.printBoard(board);
            isPlay = play(board, turn);
            turn = turn.reverse();
        }
    }

    private boolean play(Board board, Team turn) {
        String command = inputCommand();
        if (command.equals(END)) {
            return false;
        }
        Position source = convertToSourcePosition(command);
        Position target = convertToTargetPosition(command);
        if (isWrongInputTeam(board, turn, source)) {
            return play(board, turn);
        }

        board.move(source, target);
        return true;
    }

    private String inputCommand() {
        try {
            return inputView.readCommand();
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e);
            return inputCommand();
        }
    }

    private boolean isWrongInputTeam(Board board, Team turn, Position source) {
        if (!board.isTurn(source, turn)) {
            outputView.printWrongTurnMessage(turn);
            return true;
        }
        return false;
    }
}
