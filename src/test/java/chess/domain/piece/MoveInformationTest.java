package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.Team;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MoveInformationTest {

    private Map<Position, Piece> board;

    @BeforeEach
    void setUp() {
        this.board = new HashMap<>();
    }

    @Test
    void isStartEnd() {
        putPawnIntoBoard(board, "a1", Team.WHITE);

        MoveInformation test = new MoveInformation(board, Position.of("a1"), Position.of("a1"));

        assertThat(test.isStartEnd()).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"b2:c3:true", "c4:c3:true", "c4:b2:false"}, delimiter = ':')
    void isEndAdjacentToStart(String from, String to, boolean expected) {
        Map<Position, Piece> board = new HashMap<>();
        putPawnIntoBoard(board, "b2", Team.WHITE);
        putPawnIntoBoard(board, "c3", Team.WHITE);
        putPawnIntoBoard(board, "c4", Team.WHITE);

        MoveInformation test = new MoveInformation(board, Position.of(from), Position.of(to));
        assertThat(test.isEndAdjacentToStart()).isEqualTo(expected);
    }

    @Test
    void isEndOnDiagonalOfStart() {
        Map<Position, Piece> board = new HashMap<>();
        putPawnIntoBoard(board, "b4", Team.WHITE);
        putPawnIntoBoard(board, "c3", Team.WHITE);
        putPawnIntoBoard(board, "d2", Team.WHITE);
        putPawnIntoBoard(board, "d1", Team.WHITE);

        MoveInformation test = new MoveInformation(board, Position.of("b4"), Position.of("d2"));
        assertThat(test.isEndOnDiagonalOfStart()).isTrue();

        test = new MoveInformation(board, Position.of("b4"), Position.of("d1"));
        assertThat(test.isEndOnDiagonalOfStart()).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"b4:c4:true", "b4:b5:true", "b4:e6:false"}, delimiter = ':')
    void isEndOnStraightLineOfStart(String from, String to, boolean expected) {
        Map<Position, Piece> board = new HashMap<>();
        putPawnIntoBoard(board, "b4", Team.WHITE);
        putPawnIntoBoard(board, "c4", Team.WHITE);
        putPawnIntoBoard(board, "b5", Team.WHITE);
        putPawnIntoBoard(board, "e6", Team.WHITE);

        MoveInformation test = new MoveInformation(board, Position.of(from), Position.of(to));
        assertThat(test.isEndOnStraightLineOfStart()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"b4:d5:true", "d5:b4:true", "b4:c5:false", "c5:d5:false"}, delimiter = ':')
    void isKnightMove(String from, String to, boolean expected) {
        Map<Position, Piece> board = new HashMap<>();
        putPawnIntoBoard(board, "b4", Team.WHITE);
        putPawnIntoBoard(board, "c5", Team.WHITE);
        putPawnIntoBoard(board, "d5", Team.WHITE);

        MoveInformation test = new MoveInformation(board, Position.of(from), Position.of(to));
        assertThat(test.isKnightMove()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"b4:c5:true", "b4:d5:false"}, delimiter = ':')
    void isSameTeamPlacedOnEnd(String from, String to, boolean expected) {
        Map<Position, Piece> board = new HashMap<>();
        putPawnIntoBoard(board, "b4", Team.WHITE);
        putPawnIntoBoard(board, "c5", Team.WHITE);
        putPawnIntoBoard(board, "d5", Team.BLACK);

        MoveInformation test = new MoveInformation(board, Position.of(from), Position.of(to));
        assertThat(test.isSameTeamPlacedOnEnd()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"b4:c5:false", "b4:d6:true"}, delimiter = ':')
    void doAnyPieceExistInBetween(String from, String to, boolean expected) {
        Map<Position, Piece> board = new HashMap<>();
        putPawnIntoBoard(board, "b4", Team.WHITE);
        putPawnIntoBoard(board, "c5", Team.WHITE);
        putPawnIntoBoard(board, "d6", Team.BLACK);

        MoveInformation test = new MoveInformation(board, Position.of(from), Position.of(to));
        assertThat(test.doAnyPieceExistInBetween()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"b4:c5:true", "b4:d6:true", "b4:b8:false"}, delimiter = ':')
    void doAnyPieceExistOnEnd(String from, String to, boolean expected) {
        Map<Position, Piece> board = new HashMap<>();
        putPawnIntoBoard(board, "b4", Team.WHITE);
        putPawnIntoBoard(board, "c5", Team.WHITE);
        putPawnIntoBoard(board, "d6", Team.BLACK);

        MoveInformation test = new MoveInformation(board, Position.of(from), Position.of(to));
        assertThat(test.doAnyPieceExistOnEnd()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"b4:b7:3:true", "b4:b6:3:false", "b4:c3:1:false"}, delimiter = ':')
    void isStraightMoveBy(String from, String to, int moveAmount, boolean expected) {
        Map<Position, Piece> board = new HashMap<>();
        putPawnIntoBoard(board, "b4", Team.WHITE);

        MoveInformation test = new MoveInformation(board, Position.of(from), Position.of(to));
        assertThat(test.isStraightMoveBy(moveAmount)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"b4:b7:3:true", "b4:b6:3:false", "b4:c3:1:false"}, delimiter = ':')
    void isMoveForward(String from, String to, int moveAmount, boolean expected) {
        Map<Position, Piece> board = new HashMap<>();
        putPawnIntoBoard(board, "b4", Team.WHITE);

        MoveInformation test = new MoveInformation(board, Position.of(from), Position.of(to));
        assertThat(test.isStraightMoveBy(moveAmount)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"a2:b7:true", "b4:b6:false"}, delimiter = ':')
    void isStartOnInitialPosition(String from, String to, boolean expected) {
        Map<Position, Piece> board = new HashMap<>();
        putPawnIntoBoard(board, "a2", Team.WHITE);
        putPawnIntoBoard(board, "b4", Team.WHITE);

        MoveInformation test = new MoveInformation(board, Position.of(from), Position.of(to));
        assertThat(test.isStartOnInitialPosition()).isEqualTo(expected);
    }

    @Test
    void isStartOnInitialPosition_폰이_아닌_기물_예외처리() {
        Map<Position, Piece> board = new HashMap<>();
        putKnightIntoBoard(board, "b4", Team.WHITE);

        MoveInformation test = new MoveInformation(board, Position.of("b4"), Position.of("c6"));
        assertThatThrownBy(test::isStartOnInitialPosition)
            .isInstanceOf(UnsupportedOperationException.class);
    }

    private void putPawnIntoBoard(Map<Position, Piece> board, String position, Team pawnTeam) {
        board.put(Position.of(position), new Pawn(pawnTeam));
    }

    private void putKnightIntoBoard(Map<Position, Piece> board, String position, Team pawnTeam) {
        board.put(Position.of(position), new Knight(pawnTeam));
    }
}
