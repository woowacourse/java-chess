package chess.domain.piece;

import chess.exception.DomainException;

import java.util.Arrays;

public enum Color {
    BLACK(),
    WHITE();

    public Color opposite() {
        return Arrays.stream(values())
                .filter(color -> this != color)
                .findAny()
                .orElseThrow(() -> new DomainException("반대되는 색상이 없습니다."));
    }
}
