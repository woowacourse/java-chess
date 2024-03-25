package view;

import model.piece.Color;
import model.piece.role.RoleStatus;

import java.util.Arrays;

public enum ChessSymbol {
    KING('K', 'k'),
    QUEEN('Q', 'q'),
    PAWN('P', 'p'),
    BISHOP('B', 'b'),
    KNIGHT('N', 'n'),
    ROOK('R', 'r'),
    SQUARE('.', '.');

    private final char blackFactionAbbreviation;
    private final char whiteFactionAbbreviation;

    ChessSymbol(char blackFactionAbbreviation, char whiteFactionAbbreviation) {
        this.blackFactionAbbreviation = blackFactionAbbreviation;
        this.whiteFactionAbbreviation = whiteFactionAbbreviation;
    }

    public static char squareAbbreviation() {
        return SQUARE.blackFactionAbbreviation;
    }

    public static char from(RoleStatus roleStatus, Color color) {
        ChessSymbol chessSymbol = Arrays.stream(values())
                                        .filter(symbol -> symbol.name().equals(roleStatus.name()))
                                        .findFirst()
                                        .orElseThrow(() -> new IllegalArgumentException("해당 Role에 대한 ChessSymbol이 존재하지 않습니다."));
        if (color == Color.BLACK) {
            return chessSymbol.blackFactionAbbreviation;
        }
        return chessSymbol.whiteFactionAbbreviation;
    }
}

