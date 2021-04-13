package chess.domain.piece;

import chess.exception.DomainException;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Color {
    BLACK(),
    WHITE();

    public Color opposite() {
        return Arrays.stream(values())
                .filter(color -> this != color)
                .findAny()
                .orElseThrow(() -> new DomainException("반대되는 색상이 없습니다."));
    }

    public static Color of(String colorString) {
        return Arrays.stream(values())
                .filter(value -> value.name().equalsIgnoreCase(colorString))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("시스템 에러 - Deserialization이 불가합니다."));
    }
}
