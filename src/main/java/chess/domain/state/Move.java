package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.location.Location;
import chess.domain.piece.Piece;
import chess.domain.state.exception.UnsupportedCommandException;
import chess.domain.team.Team;
import java.util.List;

public class Move implements State<List<Piece>> {

    private static final String BLANK = " ";
    private static final int SOURCE_LOCATION_INDEX = 1;
    private static final int TARGET_LOCATION_INDEX = 2;

    private final Board board;
    private final Team team;
    private final String command;

    public Move(Board board, Team team, String command) {
        this.board = board;
        this.team = team;
        this.command = command;
    }

    @Override
    public void receive(String command) {
        throw new UnsupportedCommandException("움직이는 동작 중에는 입력 받을 수 없습니다.");
    }

    @Override
    public State next() {
        if (!board.isKingAlive(team.reverse())) {
            return new End();
        }
        return new Wait(board, team.reverse());
    }

    @Override
    public State before() {
        return new Wait(board, team);
    }

    @Override
    public List<Piece> result() {
        String[] words = command.split(BLANK);
        Location source = Location.of(words[SOURCE_LOCATION_INDEX].trim());
        Location target = Location.of(words[TARGET_LOCATION_INDEX].trim());

        board.move(source, target, team);
        return board.toList();
    }

    @Override
    public ResultType resultType() {
        return ResultType.BOARD;
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public boolean needsParam() {
        return false;
    }
}
