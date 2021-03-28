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

    XPosition(char name) {
        this.name = name;
    }

    public XPosition moveUnit(int unit) {
        return findXPosition((char) (name + unit));
    }

    private XPosition findXPosition(char newXPosition) {
        return Arrays.stream(values())
            .filter(element -> element.name == newXPosition)
            .findAny()
            .orElseThrow(InvalidMoveException::new);
    }

    public int difference(XPosition anotherXPosition) {
        return anotherXPosition.name - this.name;
    }

    public char getValue() {
        return name;
    }
}
