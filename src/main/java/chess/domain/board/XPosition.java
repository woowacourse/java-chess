package chess.domain.board;

import chess.domain.exceptions.InvalidMoveException;

import java.util.Arrays;

public enum XPosition {
    A('a'),
    B('b'),
    C('c'),
    D('d'),
    E('e'),
    F('f'),
    G('g'),
    H('h');

    private final char name;

    XPosition(final char name) {
        this.name = name;
    }

    public XPosition moveUnit(final int unit) {
        return findXPosition((char) (name + unit));
    }

    private XPosition findXPosition(final char newXPosition) {
        return Arrays.stream(values())
            .filter(element -> element.name == newXPosition)
            .findAny()
            .orElseThrow(InvalidMoveException::new);
    }

    public int difference(final XPosition anotherXPosition) {
        return anotherXPosition.name - this.name;
    }

    public char getValue() {
        return name;
    }
}
