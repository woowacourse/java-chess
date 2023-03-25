package chess.domain.command;

import java.util.Arrays;

import chess.domain.piece.Side;

public enum Turn {
    BLACK(Side.BLACK, "black"),
    WHITE(Side.WHITE, "white");

    private final Side side;
    private final String displayName;

    Turn(final Side side, final String displayName) {
        this.side = side;
        this.displayName = displayName;
    }

    public static Turn from(String turnName) {
        return Arrays.stream(values())
                .filter(turn -> turn.displayName.equals(turnName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 이름에 해당하는 턴이 존재하지 않습니다."));
    }

    public boolean isCorrectTurn(Side side) {
        return this.side == side;
    }

    public Turn change() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }

    public String getDisplayName() {
        return displayName;
    }
}
