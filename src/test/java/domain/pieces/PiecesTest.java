package domain.pieces;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.pieces.exceptions.IsNotMovableException;
import domain.point.Point;
import domain.team.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PiecesTest {
    private Pieces pieces;

    @BeforeEach
    void init() {
        pieces = Pieces.of(PiecesFactory.create());
    }


    @ParameterizedTest
    @DisplayName("선택한 곳에 움직일 말이 없으면 오류")
    @ValueSource(strings = {"b3, c4, d5, e6"})
    void move_validateExistPiece(String input) {
        Point from = Point.of(input);
        Point to = Point.of("b4");
        assertThatThrownBy(() -> pieces.move(Team.BLACK, from, to))
            .isInstanceOf(IsNotMovableException.class);
    }


    @Test
    @DisplayName("본인 차례에 다른 색 말을 움직이면 오류")
    void move_validateCorrectTurn() {
        Point from = Point.of("a2");
        Point to = Point.of("a3");
        assertThatThrownBy(() -> pieces.move(Team.BLACK, from, to))
            .isInstanceOf(IsNotMovableException.class);
    }

    @Test
    @DisplayName("Target 에 같은 색 말이 있으면 오류")
    void move_validateSameTeamToTarget() {
        Point from = Point.of("a1");
        Point to = Point.of("a2");
        assertThatThrownBy(() -> pieces.move(Team.WHITE, from, to))
            .isInstanceOf(IsNotMovableException.class);
    }

    @Test
    @DisplayName("Pawn 이 움직일 수 없는 방향으로 가면 오류")
    void move_validatePieceMovable_Pawn() {
        Point from = Point.of("a2");
        Point to = Point.of("b3");
        assertThatThrownBy(() -> pieces.move(Team.BLACK, from, to))
            .isInstanceOf(IsNotMovableException.class);
    }

    @Test
    @DisplayName("King 이 움직일 수 없는 방향으로 가면 오류")
    void move_validatePieceMovable_King() {
        pieces.move(Team.WHITE, Point.of("d2"), Point.of("d3"));
        Point from = Point.of("e1");
        Point to = Point.of("c3");
        assertThatThrownBy(() -> pieces.move(Team.WHITE, from, to))
            .isInstanceOf(IsNotMovableException.class);
    }

    @Test
    @DisplayName("Queen 이 움직일 수 없는 방향으로 가면 오류")
    void move_validatePieceMovable_Queen() {
        pieces.move(Team.WHITE, Point.of("d2"), Point.of("d3"));
        pieces.move(Team.WHITE, Point.of("d3"), Point.of("d4"));
        pieces.move(Team.WHITE, Point.of("c2"), Point.of("c3"));
        pieces.move(Team.WHITE, Point.of("c3"), Point.of("c4"));
        Point from = Point.of("d1");
        Point to = Point.of("c3");
        assertThatThrownBy(() -> pieces.move(Team.WHITE, from, to))
            .isInstanceOf(IsNotMovableException.class);
    }

    @Test
    @DisplayName("Bishop 이 움직일 수 없는 방향으로 가면 오류")
    void move_validatePieceMovable_Bishop() {
        pieces.move(Team.WHITE, Point.of("c2"), Point.of("c3"));
        pieces.move(Team.WHITE, Point.of("c3"), Point.of("c4"));
        Point from = Point.of("c1");
        Point to = Point.of("c3");
        assertThatThrownBy(() -> pieces.move(Team.WHITE, from, to))
            .isInstanceOf(IsNotMovableException.class);
    }

    @Test
    @DisplayName("Knight 이 움직일 수 없는 방향으로 가면 오류")
    void move_validatePieceMovable_Knight() {
        Point from = Point.of("b1");
        Point to = Point.of("b3");
        assertThatThrownBy(() -> pieces.move(Team.WHITE, from, to))
            .isInstanceOf(IsNotMovableException.class);
    }


    @Test
    @DisplayName("Rook 이 움직일 수 없는 방향으로 가면 오류")
    void move_validatePieceMovable_Rook() {
        pieces.move(Team.WHITE, Point.of("g2"), Point.of("g3"));
        Point from = Point.of("h1");
        Point to = Point.of("g2");
        assertThatThrownBy(() -> pieces.move(Team.WHITE, from, to))
            .isInstanceOf(IsNotMovableException.class);
    }


}