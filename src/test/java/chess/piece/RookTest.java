package chess.piece;

import chess.domain.Point;
import chess.domain.piece.PieceType;
import chess.domain.piece.kind.Empty;
import chess.domain.piece.kind.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.Color.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class RookTest {
    @DisplayName("Rook 생성")
    @Test
    public void create() {
        Rook rook1 = new Rook(BLACK);
        assertThat(PieceType.findPiece(0, 0)).isEqualTo(rook1);
        Rook rook2 = new Rook(BLACK);
        assertThat(PieceType.findPiece(0, 7)).isEqualTo(rook2);
        Rook rook3 = new Rook(WHITE);
        assertThat(PieceType.findPiece(7, 0)).isEqualTo(rook3);
        Rook rook4 = new Rook(WHITE);
        assertThat(PieceType.findPiece(7, 7)).isEqualTo(rook4);
    }

    @DisplayName("Rook의 가능한 방향 확인")
    @Test
    void checkRookPossibleMove() {
        Point source = Point.of(4, 4);
        Rook rook = new Rook(BLACK);

        Empty empty = new Empty(NOTHING);
        Point emptyPoint1 = Point.of(5, 4);
        Point emptyPoint2 = Point.of(4, 5);
        Point emptyPoint3 = Point.of(3, 4);
        Point emptyPoint4 = Point.of(4, 3);

        assertDoesNotThrow(() -> rook.validateMovableRoute(source, emptyPoint1, empty));
        assertDoesNotThrow(() -> rook.validateMovableRoute(source, emptyPoint2, empty));
        assertDoesNotThrow(() -> rook.validateMovableRoute(source, emptyPoint3, empty));
        assertDoesNotThrow(() -> rook.validateMovableRoute(source, emptyPoint4, empty));
    }

    @DisplayName("Rook의 불가능한 방향 확인")
    @Test
    void checkRookImpossibleMove() {
        Point source = Point.of(4, 4);
        Rook rook = new Rook(BLACK);

        Empty empty = new Empty(NOTHING);

        Point emptyPoint1 = Point.of(3, 3);
        Point emptyPoint2 = Point.of(5, 5);
        Point emptyPoint3 = Point.of(5, 3);
        Point emptyPoint4 = Point.of(3, 5);

        assertThatThrownBy(() -> rook.validateMovableRoute(source, emptyPoint1, empty))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> rook.validateMovableRoute(source, emptyPoint2, empty))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> rook.validateMovableRoute(source, emptyPoint3, empty))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> rook.validateMovableRoute(source, emptyPoint4, empty))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
