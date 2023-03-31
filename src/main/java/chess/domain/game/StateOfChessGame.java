package chess.domain.game;

import java.util.Arrays;

public enum StateOfChessGame {

    STARTED(true, false),
    PAUSED(true, false),
    FINISHED(true, true);

    private final boolean isStarted;
    private final boolean isFinished;

    StateOfChessGame(final boolean isStarted, final boolean isFinished) {
        this.isStarted = isStarted;
        this.isFinished = isFinished;
    }

    public static final StateOfChessGame of(final String name) {
        return Arrays.stream(values())
                .filter(stateOfChessGame -> stateOfChessGame.name().equals(name))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("일치하는 상태가 존재하지 않습니다"));
    }

    public boolean isStarted() {
        return isStarted;
    }

    public boolean isFinished() {
        return isFinished;
    }

}
