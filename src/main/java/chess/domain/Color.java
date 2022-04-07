package chess.domain;

import static chess.domain.state.Turn.BLACK_TURN;
import static chess.domain.state.Turn.WHITE_TURN;

import chess.domain.state.Turn;
import java.util.function.Function;

public enum Color {

    WHITE(String::toLowerCase, WHITE_TURN),
    BLACK(String::toUpperCase, BLACK_TURN),
    ;

    private final Function<String, String> caseConverter;
    private final Turn turn;

    Color(Function<String, String> caseConvertor, Turn turn) {
        this.caseConverter = caseConvertor;
        this.turn = turn;
    }

    public String convertToCase(String value) {
        return caseConverter.apply(value);
    }

    public Turn currentTurn() {
        return turn;
    }

    public Turn reverseTurn() {
        return turn.reverseTurn();
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }
}
