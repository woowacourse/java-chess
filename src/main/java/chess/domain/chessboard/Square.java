package chess.domain.chessboard;

import java.util.Objects;

public class Square {

    private static final Integer FORMATTING_TO_UPPERCASE_LETTER_ASCII_NUMBER = 64;
    private static final Integer FORMATTING_TO_LOWERCASE_LETTER_ASCII_NUMBER = 96;
    private static final Integer FORMATTING_TO_NUMBER_ASCII_NUMBER = 48;

    private final Lettering lettering;
    private final Numbering numbering;

    public Square(Lettering lettering, Numbering numbering) {
        this.lettering = lettering;
        this.numbering = numbering;
    }

    public Square(String inputSquare) {
        this.lettering = createSquareLettering(inputSquare);
        this.numbering = createSquareNumbering(inputSquare);
    }

    private Numbering createSquareNumbering(String inputSquare) {
        char number = inputSquare.charAt(1);
        return Numbering.findNumbering(number - FORMATTING_TO_NUMBER_ASCII_NUMBER);
    }

    private Lettering createSquareLettering(String inputSquare) {
        char letter = inputSquare.charAt(0);
        if (Character.isUpperCase(letter)) {
            return Lettering.findLettering(letter - FORMATTING_TO_UPPERCASE_LETTER_ASCII_NUMBER);
        }
        return Lettering.findLettering(letter - FORMATTING_TO_LOWERCASE_LETTER_ASCII_NUMBER);
    }

    public boolean isForwardMost() {
        return numbering == Numbering.EIGHT;
    }

    public boolean isBackwardMost() {
        return numbering == Numbering.ONE;
    }

    public boolean isLeftMost() {
        return lettering == Lettering.A;
    }

    public boolean isRightMost() {
        return lettering == Lettering.H;
    }

    public Lettering getLettering() {
        return lettering;
    }

    public Numbering getNumbering() {
        return numbering;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Square square = (Square) object;
        return lettering == square.lettering && numbering == square.numbering;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lettering, numbering);
    }
}
