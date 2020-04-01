package domain.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.pieces.exceptions.IsNotMovableException;
import domain.point.MovePoint;
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
        MovePoint movePoint = new MovePoint(from, to);
        assertThatThrownBy(() -> pieces.move(Team.BLACK, movePoint))
            .isInstanceOf(IsNotMovableException.class);
    }


    @Test
    @DisplayName("본인 차례에 다른 색 말을 움직이면 오류")
    void move_validateCorrectTurn() {
        Point from = Point.of("a2");
        Point to = Point.of("a3");
        MovePoint movePoint = new MovePoint(from, to);
        assertThatThrownBy(() -> pieces.move(Team.BLACK, movePoint))
            .isInstanceOf(IsNotMovableException.class);
    }

    @Test
    @DisplayName("Target 에 같은 색 말이 있으면 오류")
    void move_validateSameTeamToTarget() {
        Point from = Point.of("a1");
        Point to = Point.of("a2");
        MovePoint movePoint = new MovePoint(from, to);
        assertThatThrownBy(() -> pieces.move(Team.WHITE, movePoint))
            .isInstanceOf(IsNotMovableException.class);
    }

    @Test
    @DisplayName("Pawn 이 움직일 수 없는 방향으로 가면 오류")
    void move_validatePieceMovable_Pawn() {
        Point from = Point.of("a2");
        Point to = Point.of("b3");
        MovePoint movePoint = new MovePoint(from, to);
        assertThatThrownBy(() -> pieces.move(Team.BLACK, movePoint))
            .isInstanceOf(IsNotMovableException.class);
    }

    @Test
    @DisplayName("King 이 움직일 수 없는 방향으로 가면 오류")
    void move_validatePieceMovable_King() {
        pieces.move(Team.WHITE, new MovePoint(Point.of("d2"), Point.of("d3")));
        Point from = Point.of("e1");
        Point to = Point.of("c3");
        MovePoint movePoint = new MovePoint(from, to);
        assertThatThrownBy(() -> pieces.move(Team.WHITE, movePoint))
            .isInstanceOf(IsNotMovableException.class);
    }

    @Test
    @DisplayName("Queen 이 움직일 수 없는 방향으로 가면 오류")
    void move_validatePieceMovable_Queen() {
        pieces.move(Team.WHITE, new MovePoint(Point.of("d2"), Point.of("d3")));
        pieces.move(Team.WHITE, new MovePoint(Point.of("d3"), Point.of("d4")));
        pieces.move(Team.WHITE, new MovePoint(Point.of("c2"), Point.of("c3")));
        pieces.move(Team.WHITE, new MovePoint(Point.of("c3"), Point.of("c4")));
        Point from = Point.of("d1");
        Point to = Point.of("c3");
        MovePoint movePoint = new MovePoint(from, to);
        assertThatThrownBy(() -> pieces.move(Team.WHITE, movePoint))
            .isInstanceOf(IsNotMovableException.class);
    }

    @Test
    @DisplayName("Bishop 이 움직일 수 없는 방향으로 가면 오류")
    void move_validatePieceMovable_Bishop() {
        pieces.move(Team.WHITE, new MovePoint(Point.of("c2"), Point.of("c3")));
        pieces.move(Team.WHITE, new MovePoint(Point.of("c3"), Point.of("c4")));
        Point from = Point.of("c1");
        Point to = Point.of("c3");
        MovePoint movePoint = new MovePoint(from, to);
        assertThatThrownBy(() -> pieces.move(Team.WHITE, movePoint))
            .isInstanceOf(IsNotMovableException.class);
    }

    @Test
    @DisplayName("Knight 이 움직일 수 없는 방향으로 가면 오류")
    void move_validatePieceMovable_Knight() {
        Point from = Point.of("b1");
        Point to = Point.of("b3");
        MovePoint movePoint = new MovePoint(from, to);
        assertThatThrownBy(() -> pieces.move(Team.WHITE, movePoint))
            .isInstanceOf(IsNotMovableException.class);
    }


    @Test
    @DisplayName("Rook 이 움직일 수 없는 방향으로 가면 오류")
    void move_validatePieceMovable_Rook() {
        pieces.move(Team.WHITE, new MovePoint(Point.of("g2"), Point.of("g3")));
        Point from = Point.of("h1");
        Point to = Point.of("g2");
        MovePoint movePoint = new MovePoint(from, to);
        assertThatThrownBy(() -> pieces.move(Team.WHITE, movePoint))
            .isInstanceOf(IsNotMovableException.class);
    }

    @Test
    @DisplayName("Pawn 움직임 확인")
    void move_Pawn() {
        pieces.move(Team.WHITE, new MovePoint(Point.of("g2"), Point.of("g3")));
        pieces.move(Team.BLACK, new MovePoint(Point.of("g7"), Point.of("g6")));
        assertThat(pieces.getPiece(Point.of("g3")).initial).isEqualTo("p");
        assertThat(pieces.getPiece(Point.of("g6")).initial).isEqualTo("P");
    }

    @Test
    @DisplayName("Rook 움직임 확인")
    void move_Rook() {
        // Rook 앞에 있는 Pawn 움직이기
        pieces.move(Team.WHITE, new MovePoint(Point.of("h2"), Point.of("h3")));
        pieces.move(Team.BLACK, new MovePoint(Point.of("h7"), Point.of("h6")));
        pieces.move(Team.WHITE, new MovePoint(Point.of("g2"), Point.of("g3")));
        pieces.move(Team.BLACK, new MovePoint(Point.of("g7"), Point.of("g6")));

        // Rook 움직이기
        pieces.move(Team.WHITE, new MovePoint(Point.of("h1"), Point.of("h2")));
        pieces.move(Team.BLACK, new MovePoint(Point.of("h8"), Point.of("h7")));
        pieces.move(Team.WHITE, new MovePoint(Point.of("h2"), Point.of("g2")));
        pieces.move(Team.BLACK, new MovePoint(Point.of("h7"), Point.of("g7")));

        assertThat(pieces.getPiece(Point.of("g2")).initial).isEqualTo("r");
        assertThat(pieces.getPiece(Point.of("g7")).initial).isEqualTo("R");
    }

    @Test
    @DisplayName("Knight 움직임 확인")
    void move_Knight() {
        pieces.move(Team.WHITE, new MovePoint(Point.of("g1"), Point.of("f3")));
        pieces.move(Team.BLACK, new MovePoint(Point.of("g8"), Point.of("h6")));

        assertThat(pieces.getPiece(Point.of("f3")).initial).isEqualTo("n");
        assertThat(pieces.getPiece(Point.of("h6")).initial).isEqualTo("N");
    }

    @Test
    @DisplayName("Bishop 움직임 확인")
    void move_Bishop() {
        // Bishop 앞에 있는 Pawn 움직이기
        pieces.move(Team.WHITE, new MovePoint(Point.of("e2"), Point.of("e3")));
        pieces.move(Team.BLACK, new MovePoint(Point.of("e7"), Point.of("e6")));

        // Bishop 움직이기
        pieces.move(Team.WHITE, new MovePoint(Point.of("f1"), Point.of("a6")));
        pieces.move(Team.BLACK, new MovePoint(Point.of("f8"), Point.of("a3")));

        assertThat(pieces.getPiece(Point.of("a6")).initial).isEqualTo("b");
        assertThat(pieces.getPiece(Point.of("a3")).initial).isEqualTo("B");
    }

    @Test
    @DisplayName("Queen 움직임 확인")
    void move_Queen() {
        // Queen 앞에 있는 Pawn 움직이기
        pieces.move(Team.WHITE, new MovePoint(Point.of("e2"), Point.of("e3")));
        pieces.move(Team.BLACK, new MovePoint(Point.of("e7"), Point.of("e6")));

        // Queen 움직이기
        pieces.move(Team.WHITE, new MovePoint(Point.of("d1"), Point.of("h5")));
        pieces.move(Team.BLACK, new MovePoint(Point.of("d8"), Point.of("h4")));
        pieces.move(Team.WHITE, new MovePoint(Point.of("h5"), Point.of("h6")));
        pieces.move(Team.BLACK, new MovePoint(Point.of("h4"), Point.of("h3")));

        assertThat(pieces.getPiece(Point.of("h6")).initial).isEqualTo("q");
        assertThat(pieces.getPiece(Point.of("h3")).initial).isEqualTo("Q");
    }

    @Test
    @DisplayName("King 움직임 확인")
    void move_King() {
        // King 앞에 있는 Pawn 움직이기
        pieces.move(Team.WHITE, new MovePoint(Point.of("e2"), Point.of("e3")));
        pieces.move(Team.BLACK, new MovePoint(Point.of("e7"), Point.of("e6")));

        // King 움직이기
        pieces.move(Team.WHITE, new MovePoint(Point.of("e1"), Point.of("e2")));
        pieces.move(Team.BLACK, new MovePoint(Point.of("e8"), Point.of("e7")));
        pieces.move(Team.WHITE, new MovePoint(Point.of("e2"), Point.of("d3")));
        pieces.move(Team.BLACK, new MovePoint(Point.of("e7"), Point.of("d6")));

        assertThat(pieces.getPiece(Point.of("d3")).initial).isEqualTo("k");
        assertThat(pieces.getPiece(Point.of("d6")).initial).isEqualTo("K");
    }
}