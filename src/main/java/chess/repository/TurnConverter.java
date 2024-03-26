package chess.repository;

import chess.domain.chessGame.Turn;
import java.util.Arrays;
import java.util.function.Supplier;

public class TurnConverter {

    static Turn convert(String turnString) {
        return TurnState.convert(turnString);
    }

    static Turn waitTurn() {
        return Turn.create();
    }

    static Turn whiteTurn() {
        Turn turn = waitTurn();
        turn.startGame();
        return turn;
    }

    static Turn blackTurn() {
        Turn turn = whiteTurn();
        turn.oppositeTurn();
        return turn;
    }

    static Turn endTurn() {
        Turn turn = Turn.create();
        turn.stopGame();
        return turn;
    }

    private enum TurnState {
        WAIT("WAIT", TurnConverter::waitTurn),
        WHITE("WHITE", TurnConverter::whiteTurn),
        BLACK("BLACK", TurnConverter::blackTurn),
        END("END", TurnConverter::endTurn);

        private final String state;
        private final Supplier<Turn> turnSupplier;

        TurnState(String state, Supplier<Turn> turnSupplier) {
            this.state = state;
            this.turnSupplier = turnSupplier;
        }

        private static Turn convert(String state) {
            return Arrays.stream(values())
                    .filter(value -> value.state.equals(state))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("잘못된 Turn 상태입니다"))
                    .turnSupplier
                    .get();
        }
    }
}
