package chess.domain;

import chess.exception.ChessException;

import java.util.Arrays;

public enum Side {
    WHITE, BLACK, NONE;

    public Side changeTurn() {
        if (this == WHITE) {
            return BLACK;
        }
        if (this == BLACK) {
            return WHITE;
        }
        return NONE;
    }

    public static Side getTurnByName(String name) {
        return Arrays.stream(Side.values())
                .filter(side -> side.name().equals(name))
                .findFirst().orElseThrow(() -> new ChessException("잘못된 턴입니다."));
    }
}
