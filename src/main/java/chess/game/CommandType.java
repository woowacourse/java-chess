package chess.game;

import static chess.view.output.OutputView.printScore;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.vo.TeamColor;
import java.util.Arrays;
import java.util.List;

public enum CommandType {

    START("start") {
        @Override
        public Board play(Board board, List<String> command) {
            throw new IllegalArgumentException();
        }
    },
    END("end") {
        @Override
        public Board play(Board board, List<String> command) {
            printScore(board.getTotalPoint(TeamColor.WHITE), board.getTotalPoint(TeamColor.BLACK));
            return board;
        }
    },
    MOVE("move") {
        @Override
        public Board play(Board board, List<String> command) {
            final Position sourcePosition = Position.from(command.get(SOURCE_POSITION_INDEX));
            final Position targetPosition = Position.from(command.get(TARGET_POSITION_INDEX));
            return board.movePiece(sourcePosition, targetPosition);
        }
    },
    STATUS("status") {
        @Override
        public Board play(Board board, List<String> command) {
            printScore(board.getTotalPoint(TeamColor.WHITE), board.getTotalPoint(TeamColor.BLACK));
            return board;
        }
    },
    ;

    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;

    private final String value;

    CommandType(final String value) {
        this.value = value;
    }

    public abstract Board play(Board board, List<String> command);

    public static CommandType from(final String value) {
        return Arrays.stream(values())
                .filter(command -> command.value.equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어입니다."));
    }
}
