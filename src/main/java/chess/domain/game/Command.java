package chess.domain.game;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import java.util.List;

public class Command {
    private static final int COMMAND_INDEX = 0;
    private static final int MOVE_COMMAND_SIZE = 3;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;
    private static final String MOVE_COMMAND = "move";
    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";
    private static final String SQUARE_BOUND_REGULAR_EXPRESSION = "^[a-h][1-8]$";

    private String command;
    private Square source;
    private Square target;

    public Command(final List<String> commands) {
        extractCommand(commands);
    }

    private void extractCommand(final List<String> commands) {
        command = commands.get(COMMAND_INDEX);

        if (isMove() && commands.size() == MOVE_COMMAND_SIZE) {
            source = convertToSquare(commands.get(SOURCE_INDEX));
            target = convertToSquare(commands.get(TARGET_INDEX));
            return;
        }
        if (!isStart() && !isEnd()) {
            throw new IllegalArgumentException("게임 명령어가 올바르지 않습니다.");
        }
    }

    private Square convertToSquare(final String input) {
        validateSquareInput(input);
        final File file = File.findFileByLetter(input.charAt(FILE_INDEX));
        final Rank rank = Rank.findRankByLetter(input.charAt(RANK_INDEX));

        return new Square(file, rank);
    }

    private void validateSquareInput(final String input) {
        if (!input.matches(SQUARE_BOUND_REGULAR_EXPRESSION)) {
            throw new IllegalArgumentException("정확한 source 위치와 target 위치를 입력해주세요");
        }
    }

    public boolean isMove() {
        return command.equals(MOVE_COMMAND);
    }

    public boolean isStart() {
        return command.equals(START_COMMAND);
    }

    public boolean isEnd() {
        return command.equals(END_COMMAND);
    }

    public Square getSource() {
        if (!isMove()) {
            throw new IllegalStateException("source를 요청할 수 없습니다.");
        }
        return source;
    }

    public Square getTarget() {
        if (!isMove()) {
            throw new IllegalStateException("target을 요청할 수 없습니다.");
        }
        return target;
    }
}
