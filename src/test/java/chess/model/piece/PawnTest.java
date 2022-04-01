package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.model.Color;
import chess.model.board.Square;
import chess.model.piece.pawn.Pawn;
import chess.model.strategy.move.MoveType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnTest {


    private Pawn whitePawn;
    private Pawn blackPawn;
    private Square whiteStart;
    private Square blackStart;

    @BeforeEach
    void setUp() {
        whitePawn = Pawn.of(Color.WHITE);
        blackPawn = Pawn.of(Color.BLACK);
        whiteStart = Square.of("c2");
        blackStart = Square.of("c7");
    }

    @ParameterizedTest(name = "첫 이동 가능 : c2->{0}, c7->{1} (적군 여부 : {2})")
    @CsvSource(value = {"c3:c6:MOVE", "c4:c5:MOVE", "b3:b6:ATTACK", "d3:d6:ATTACK"}, delimiter = ':')
    void firstMoveOrAttack(String whiteTarget, String blackTarget, MoveType type) {
        assertAll(
                () -> assertThat(
                        whitePawn.movable(whiteStart, Square.of(whiteTarget), type)).isTrue(),
                () -> assertThat(
                        blackPawn.movable(blackStart, Square.of(blackTarget), type)).isTrue()
        );
    }

    @ParameterizedTest(name = "첫 {2} 불가 : c2->{0}, c7->{1} (적군 여부 : {2})")
    @CsvSource(value = {"b3:b6:MOVE", "d3:d6:MOVE", "c3:c6:ATTACK", "c4:c5:ATTACK"}, delimiter = ':')
    void invalidFirstMoveOrAttack(String whiteTarget, String blackTarget, MoveType type) {
        assertAll(
                () -> assertThat(
                        whitePawn.movable(whiteStart, Square.of(whiteTarget), type)).isFalse(),
                () -> assertThat(
                        blackPawn.movable(blackStart, Square.of(blackTarget), type)).isFalse()
        );
    }

    @ParameterizedTest(name = "{4} 가능 : {0}->{1}, {2}->{3}")
    @CsvSource(value = {"d4:d5:e4:e3:MOVE", "d4:c5:e4:d3:ATTACK", "d4:e5:e4:f3:ATTACK"}, delimiter = ':')
    void moveOrAttackWithNotFirstSquare(String whiteSource, String whiteTarget,
                                        String blackSource, String blackTarget, MoveType type) {
        Square white = Square.of(whiteSource);
        Square black = Square.of(blackSource);
        assertAll(
                () -> assertThat(
                        whitePawn.movable(white, Square.of(whiteTarget), type)).isTrue(),
                () -> assertThat(
                        blackPawn.movable(black, Square.of(blackTarget), type)).isTrue()
        );
    }

    @ParameterizedTest(name = "{4} 불가 : {0}->{1}, {2}->{3}")
    @CsvSource(value = {"d4:d6:e4:e2:MOVE", "d4:c5:e4:d3:MOVE", "d4:e5:e4:f3:MOVE"}, delimiter = ':')
    void invalidMoveOrAttackWithNotFirstSquare(String whiteSource, String whiteTarget,
                                               String blackSource, String blackTarget, MoveType type) {
        Square white = Square.of(whiteSource);
        Square black = Square.of(blackSource);
        assertAll(
                () -> assertThat(
                        whitePawn.movable(white, Square.of(whiteTarget), type)).isFalse(),
                () -> assertThat(
                        blackPawn.movable(black, Square.of(blackTarget), type)).isFalse()
        );
    }
}
