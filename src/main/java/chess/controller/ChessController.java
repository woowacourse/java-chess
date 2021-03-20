package chess.controller;

import chess.domain.board.Board;
import chess.domain.command.CommandType;
import chess.domain.team.Team;
import chess.utils.BoardUtil;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void play() {
        OutputView.printGameStartMessage();
        Team team = Team.WHITE;
        CommandType command = CommandType.from(inputStartCommand());

        Board board = BoardUtil.generateInitialBoard();
        if (command.isSame(CommandType.START)) {
            OutputView.printBoard(BoardUtil.generateViewBoard(board));
        }
    }

    private String inputStartCommand() {
        try {
            String input = InputView.inputCommand();
            CommandType command = CommandType.from(input);
            if (command != CommandType.START && command != CommandType.END) {
                throw new IllegalArgumentException("제대로 입력하세요.");
            }
            return input;
        } catch (RuntimeException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return inputStartCommand();
        }
    }
}
