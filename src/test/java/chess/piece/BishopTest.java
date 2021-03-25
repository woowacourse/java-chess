package chess.piece;

import chess.domain.Point;
import chess.domain.piece.PieceType;
import chess.domain.piece.kind.Bishop;
import chess.domain.piece.kind.Empty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.Color.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class BishopTest {
    @DisplayName("Bishop 생성")
    @Test
    public void create() {
        Bishop bishop1 = new Bishop(BLACK);
        assertThat(PieceType.findPiece(0, 2)).isEqualTo(bishop1);
        Bishop bishop2 = new Bishop(BLACK);
        assertThat(PieceType.findPiece(0, 5)).isEqualTo(bishop2);
        Bishop bishop3 = new Bishop(WHITE);
        assertThat(PieceType.findPiece(7, 2)).isEqualTo(bishop3);
        Bishop bishop4 = new Bishop(WHITE);
        assertThat(PieceType.findPiece(7, 5)).isEqualTo(bishop4);
    }

    @DisplayName("Bishop의 가능한 방향 확인")
    @Test
    void checkBishopPossibleMove() {
        Bishop bishop = new Bishop(BLACK);
        Point source = Point.of(4, 4);

        Point emptyPoint1 = Point.of(5, 5);
        Point emptyPoint2 = Point.of(3, 3);
        Point emptyPoint3 = Point.of(3, 5);
        Point emptyPoint4 = Point.of(5, 3);

        Empty empty1 = new Empty(NOTHING);
        Empty empty2 = new Empty(NOTHING);
        Empty empty3 = new Empty(NOTHING);
        Empty empty4 = new Empty(NOTHING);

        assertDoesNotThrow(() -> bishop.validateMovableRoute(source, emptyPoint1, empty1));
        assertDoesNotThrow(() -> bishop.validateMovableRoute(source, emptyPoint2, empty2));
        assertDoesNotThrow(() -> bishop.validateMovableRoute(source, emptyPoint3, empty3));
        assertDoesNotThrow(() -> bishop.validateMovableRoute(source, emptyPoint4, empty4));
    }

    @DisplayName("Bishop의 불가능한 방향 확인")
    @Test
    void checkBishopImpossibleMove() {

        Bishop bishop = new Bishop(BLACK);
        Point source = Point.of(0, 2);

        Point emptyPoint = Point.of(5, 2);

        Empty empty = new Empty(NOTHING);

        assertThatThrownBy(() -> bishop.validateMovableRoute(source, emptyPoint, empty))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
