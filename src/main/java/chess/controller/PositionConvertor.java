package chess.controller;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;

import java.util.List;

public class PositionConvertor {

    private final Position from;
    private final Position to;

    private static final int MOVE_FROM_INDEX = 1;
    private static final int MOVE_TO_INDEX = 2;

    public PositionConvertor(List<String> commands) {
        validate(commands);
        this.from = convert(commands.get(MOVE_FROM_INDEX));
        this.to = convert(commands.get(MOVE_TO_INDEX));
    }

    public Position convert(final String position) {
        final int fileIndex = position.charAt(0) - 'a' + 1;
        final int rankIndex = Character.getNumericValue(position.charAt(1));

        return Position.of(File.from(fileIndex), Rank.from(rankIndex));
    }

    private void validate(List<String> commands) {
        if (commands.size() != 3) {
            throw new IllegalArgumentException("2개의 인자를 가지고 있어야합니다.");
        }
    }

    public Position getFromPosition() {
        return from;
    }

    public Position getToPosition() {
        return to;
    }
}
