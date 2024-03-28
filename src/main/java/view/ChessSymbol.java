package view;

import java.util.Arrays;

public enum ChessSymbol {
    KING('K'),
    QUEEN('Q'),
    PAWN('P'),
    BISHOP('B'),
    KNIGHT('N'),
    ROOK('R'),
    SQUARE('.');

    private final char abbreviation;

    ChessSymbol(char abbreviation) {
        this.abbreviation = abbreviation;
    }

    public char getAbbreviation() {
        return abbreviation;
    }

    public static char getSymbolForRole(String role) {
        return Arrays.stream(ChessSymbol.values())
                .filter(piece -> piece.name()
                .equalsIgnoreCase(role))
                .findFirst()
                .map(ChessSymbol::getAbbreviation)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Role을 조회하였습니다."));
    }
}

