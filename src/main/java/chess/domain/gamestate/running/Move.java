package chess.domain.gamestate.running;

import chess.domain.board.Board;
import chess.domain.gamestate.CommandType;
import chess.domain.gamestate.State;
import chess.domain.gamestate.finished.End;
import chess.domain.location.Location;
import chess.domain.team.Team;

public class Move extends Running {

    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    public Move(Board board) {
        super(board);
    }

    @Override
    public State changeCommand(CommandType command) {
        validateCommand(command);
        if (command == CommandType.STATUS) {
            return new Status(board);
        }
        if (command == CommandType.MOVE) {
            return this;
        }
        return new End(board);
    }

    public void validateCommand(CommandType command) {
        if (command != CommandType.STATUS && command != CommandType.END && command != CommandType.MOVE) {
            throw new IllegalArgumentException("[ERROR] status, move, end만 가능합니다.");
        }
    }

    @Override
    public void processMove(String input, Team currentTeam) {
        String[] splittedInput = input.split(" ");
        Location source = Location.of(splittedInput[SOURCE_INDEX]);
        Location target = Location.of(splittedInput[TARGET_INDEX]);
        board.move(source, target, currentTeam);
    }

    @Override
    public boolean isMove() {
        return true;
    }

    @Override
    public String getValue() {
        return "move";
    }
}
