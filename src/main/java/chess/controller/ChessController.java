package chess.controller;

import chess.domain.board.Board;
import chess.domain.command.CommandType;
import chess.domain.location.Location;
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

        while (!command.isSame(CommandType.END)) {
            String input = inputRunningCommand();
            command = CommandType.from(input);
            if (command.isSame(CommandType.MOVE)) {
                String[] splited = input.split(" ");
                Location source = Location.of(splited[1]);
                Location target = Location.of(splited[2]);
                board.move(source, target, team);
                team = team.reverse();
                OutputView.printBoard(BoardUtil.generateViewBoard(board));
                if (!board.isKingAlive(team)) {
                    command = CommandType.END;
                }
            } else if (command.isSame(CommandType.STATUS)) {
                double blackScore = board.score(Team.BLACK);
                double whiteScore = board.score(Team.WHITE);
                OutputView.printStatus(blackScore, whiteScore);
            }
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

    private String inputRunningCommand() {
        try {
            String input = InputView.inputCommand();
            CommandType command = CommandType.from(input);
            if (command == CommandType.START) {
                throw new IllegalArgumentException("제대로 입력하세요.");
            }
            return input;
        } catch (RuntimeException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return inputRunningCommand();
        }
    }
}
