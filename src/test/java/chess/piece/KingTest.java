package chess.piece;

import chess.domain.Point;
import chess.domain.piece.PieceType;
import chess.domain.piece.kind.Empty;
import chess.domain.piece.kind.King;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.Color.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class KingTest {
    @DisplayName("King 생성")
    @Test
    public void create() {
        King king1 = new King(BLACK);
        assertThat(PieceType.findPiece(0, 4)).isEqualTo(king1);
        King king2 = new King(WHITE);
        assertThat(PieceType.findPiece(7, 4)).isEqualTo(king2);
    }

    @DisplayName("King의 가능한 거리 확인")
    @Test
    void checkKingPossibleMove() {
        Point kingPoint = Point.of(4, 4);
        King king = new King(BLACK);

        Empty empty1 = new Empty(NOTHING);
        Point emptyPoint1 = Point.of(5, 4);

        Empty empty2 = new Empty(NOTHING);
        Point emptyPoint2 = Point.of(4, 5);

        Empty empty3 = new Empty(NOTHING);
        Point emptyPoint3 = Point.of(3, 5);

        Empty empty4 = new Empty(NOTHING);
        Point emptyPoint4 = Point.of(5, 3);

        assertDoesNotThrow(() -> king.validateMovableRoute(kingPoint, emptyPoint1, empty1));
        assertDoesNotThrow(() -> king.validateMovableRoute(kingPoint, emptyPoint2, empty2));
        assertDoesNotThrow(() -> king.validateMovableRoute(kingPoint, emptyPoint3, empty3));
        assertDoesNotThrow(() -> king.validateMovableRoute(kingPoint, emptyPoint4, empty4));
    }

    @DisplayName("King의 불가능한 거리 확인")
    @Test
    void checkKingImpossibleMove() {
        Point kingPoint = Point.of(4, 4);
        King king = new King(BLACK);

        Empty empty1 = new Empty(NOTHING);
        Point emptyPoint1 = Point.of(2, 2);

        Empty empty2 = new Empty(NOTHING);
        Point emptyPoint2 = Point.of(2, 6);

        Empty empty3 = new Empty(NOTHING);
        Point emptyPoint3 = Point.of(4, 6);

        Empty empty4 = new Empty(NOTHING);
        Point emptyPoint4 = Point.of(2, 4);

        assertThatThrownBy(() -> king.validateMovableRoute(kingPoint, emptyPoint1, empty1))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> king.validateMovableRoute(kingPoint, emptyPoint2, empty2))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> king.validateMovableRoute(kingPoint, emptyPoint3, empty3))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> king.validateMovableRoute(kingPoint, emptyPoint4, empty4))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
