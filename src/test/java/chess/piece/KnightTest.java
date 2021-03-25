package chess.piece;

import chess.domain.Point;
import chess.domain.piece.PieceType;
import chess.domain.piece.kind.Empty;
import chess.domain.piece.kind.Knight;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.Color.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class KnightTest {
    @DisplayName("Knight 생성")
    @Test
    public void create() {
        Knight knight1 = new Knight(BLACK);
        assertThat(PieceType.findPiece(0, 1)).isEqualTo(knight1);

        Knight knight2 = new Knight(BLACK);
        assertThat(PieceType.findPiece(0, 6)).isEqualTo(knight2);

        Knight knight3 = new Knight(WHITE);
        assertThat(PieceType.findPiece(7, 1)).isEqualTo(knight3);

        Knight knight4 = new Knight(WHITE);
        assertThat(PieceType.findPiece(7, 6)).isEqualTo(knight4);
    }

    @DisplayName("Knight의 가능한 거리 확인")
    @Test
    void checkKnightPossibleMove() {
        Point source = Point.of(4, 4);
        Knight knight = new Knight(BLACK);

        Empty empty1 = new Empty(NOTHING);
        Point emptyPoint1 = Point.of(2, 5);

        Empty empty2 = new Empty(NOTHING);
        Point emptyPoint2 = Point.of(2, 3);

        Empty empty3 = new Empty(NOTHING);
        Point emptyPoint3 = Point.of(3, 6);

        Empty empty4 = new Empty(NOTHING);
        Point emptyPoint4 = Point.of(3, 2);

        Empty empty5 = new Empty(NOTHING);
        Point emptyPoint5 = Point.of(6, 5);

        Empty empty6 = new Empty(NOTHING);
        Point emptyPoint6 = Point.of(6, 3);

        Empty empty7 = new Empty(NOTHING);
        Point emptyPoint7 = Point.of(5, 6);

        Empty empty8 = new Empty(NOTHING);
        Point emptyPoint8 = Point.of(5, 2);

        assertDoesNotThrow(() -> knight.validateMovableRoute(source, emptyPoint1, empty1));
        assertDoesNotThrow(() -> knight.validateMovableRoute(source, emptyPoint2, empty2));
        assertDoesNotThrow(() -> knight.validateMovableRoute(source, emptyPoint3, empty3));
        assertDoesNotThrow(() -> knight.validateMovableRoute(source, emptyPoint4, empty4));
        assertDoesNotThrow(() -> knight.validateMovableRoute(source, emptyPoint5, empty5));
        assertDoesNotThrow(() -> knight.validateMovableRoute(source, emptyPoint6, empty6));
        assertDoesNotThrow(() -> knight.validateMovableRoute(source, emptyPoint7, empty7));
        assertDoesNotThrow(() -> knight.validateMovableRoute(source, emptyPoint8, empty8));
    }

    @DisplayName("Knight의 불가능한 거리 확인")
    @Test
    void checkKnightImpossibleMove() {
        Point source = Point.of(4, 4);
        Knight knight = new Knight(BLACK);

        Empty empty1 = new Empty(NOTHING);
        Point emptyPoint1 = Point.of(2, 2);

        Empty empty2 = new Empty(NOTHING);
        Point emptyPoint2 = Point.of(2, 6);

        Empty empty3 = new Empty(NOTHING);
        Point emptyPoint3 = Point.of(4, 6);

        Empty empty4 = new Empty(NOTHING);
        Point emptyPoint4 = Point.of(2, 4);

        assertThatThrownBy(() -> knight.validateMovableRoute(source, emptyPoint1, empty1));
        assertThatThrownBy(() -> knight.validateMovableRoute(source, emptyPoint2, empty2));
        assertThatThrownBy(() -> knight.validateMovableRoute(source, emptyPoint3, empty3));
        assertThatThrownBy(() -> knight.validateMovableRoute(source, emptyPoint4, empty4));
    }
}
