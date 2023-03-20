package chess.controller;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.view.InputView;
import java.util.List;

public class MoveSubController implements SubController {

    public static final String POSITION_LENGTH_VALIDATE = "올바른 체스 좌표를 입력해주십시오";
    private final Board board;

    public MoveSubController(final Board board) {
        this.board = board;
    }

    public void run(boolean isStart) {
        validateIsGameStart(isStart);
        final List<Position> positions = readPositions();
        board.move(positions.get(0), positions.get(1));
    }

    private void validateIsGameStart(final boolean isStart) {
        if (!isStart) {
            throw new IllegalArgumentException("아직 게임이 시작되지 않았습니다.");
        }
    }

    private List<Position> readPositions() {
        final List<String> positions = InputView.readPositions();
        final String from = positions.get(0);
        final String to = positions.get(1);
        return List.of(renderPosition(from), renderPosition(to));
    }

    private Position renderPosition(final String position) {
        if (position.length() != 2) {
            throw new IllegalArgumentException(POSITION_LENGTH_VALIDATE);
        }
        final int file = position.charAt(0) - 'a' + 1;
        final int rank = position.charAt(1) - '0';
        return new Position(file, rank);
    }
}
