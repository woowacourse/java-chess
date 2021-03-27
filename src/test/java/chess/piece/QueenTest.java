package chess.piece;

import chess.domain.Point;
import chess.domain.piece.PieceType;
import chess.domain.piece.kind.Empty;
import chess.domain.piece.kind.Queen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.Color.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class QueenTest {
    @DisplayName("Queen 생성")
    @Test
    public void create() {
        Queen queen1 = new Queen(BLACK);
        assertThat(PieceType.findPiece(0, 3)).isEqualTo(queen1);
        Queen queen2 = new Queen(WHITE);
        assertThat(PieceType.findPiece(7, 3)).isEqualTo(queen2);
    }

    @DisplayName("Queen 이동 확인")
    @Test
    void checkQueenPossibleMove() {
        Point source = Point.of(4, 4);
        Queen queen = new Queen(BLACK);

        Empty empty1 = new Empty(NOTHING);
        Point emptyPoint1 = Point.of(4, 3);
        Empty empty2 = new Empty(NOTHING);
        Point emptyPoint2 = Point.of(4, 5);
        Empty empty3 = new Empty(NOTHING);
        Point emptyPoint3 = Point.of(3, 3);
        Empty empty4 = new Empty(NOTHING);
        Point emptyPoint4 = Point.of(5, 5);

        assertDoesNotThrow(() -> queen.validateMovableRoute(source, emptyPoint1, empty1));
        assertDoesNotThrow(() -> queen.validateMovableRoute(source, emptyPoint2, empty2));
        assertDoesNotThrow(() -> queen.validateMovableRoute(source, emptyPoint3, empty3));
        assertDoesNotThrow(() -> queen.validateMovableRoute(source, emptyPoint4, empty4));
    }

    @DisplayName("Queen의 불가능한 위치 확인")
    @Test
    void checkQueenImpossibleMove() {
        Point source = Point.of(4, 4);
        Queen queen = new Queen(BLACK);

        Empty empty1 = new Empty(NOTHING);
        Point emptyPoint1 = Point.of(2, 3);

        Empty empty2 = new Empty(NOTHING);
        Point emptyPoint2 = Point.of(6, 5);

        Empty empty3 = new Empty(NOTHING);
        Point emptyPoint3 = Point.of(2, 5);

        Empty empty4 = new Empty(NOTHING);
        Point emptyPoint4 = Point.of(6, 3);

        assertThatThrownBy(() -> queen.validateMovableRoute(source, emptyPoint1, empty1))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> queen.validateMovableRoute(source, emptyPoint2, empty2))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> queen.validateMovableRoute(source, emptyPoint3, empty3))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> queen.validateMovableRoute(source, emptyPoint4, empty4))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
