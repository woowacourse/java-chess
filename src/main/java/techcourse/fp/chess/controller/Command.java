package techcourse.fp.chess.controller;

public enum Command {
    START,
    END,
    MOVE,
    STATUS,
    EMPTY;

    public static Command createStartOrEnd(String input) {
        if (input.equalsIgnoreCase(START.name())) {
            return START;
        }

        if (input.equalsIgnoreCase(END.name())) {
            return END;
        }

        throw new IllegalArgumentException("start나 end 명령어를 입력해주세요.");
    }

    //TODO: 네이밍 수정
    public static Command createMoveOrEnd(String input) {
        if (input.equalsIgnoreCase(MOVE.name())) {
            return MOVE;
        }

        if (input.equalsIgnoreCase(END.name())) {
            return END;
        }

        if (input.equalsIgnoreCase(STATUS.name())) {
            return STATUS;
        }

        throw new IllegalArgumentException("move나 end 명령어를 입력해주세요.");
    }
}
