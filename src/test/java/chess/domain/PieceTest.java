package chess.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class PieceTest {

    @Nested
    @DisplayName("생성자는 ")
    class Constructor {

        @Test
        @DisplayName("폰을 생성한다.")
        void constructPawn() {
            assertThatCode(() -> new Pawn(Color.BLACK))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("룩을 생성한다.")
        void constructRook() {
            assertThatCode(() -> new Rook(Color.BLACK))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("나이트를 생성한다.")
        void constructKnight() {
            assertThatCode(() -> new Knight(Color.BLACK))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("비숍을 생성한다.")
        void constructBishop() {
            assertThatCode(() -> new Bishop(Color.BLACK))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("퀸을 생성한다.")
        void constructQueen() {
            assertThatCode(() -> new Queen(Color.BLACK))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("킹을 생성한다.")
        void constructKing() {
            assertThatCode(() -> new King(Color.BLACK))
                    .doesNotThrowAnyException();
        }
    }
    
//    @Test
//    @DisplayName("해당 말이 갈 수있는 방향으로 위치리스트를 반환한다.")
//    void getMovablePositions{
//        Rook rook = new Rook(Color.BLACK);
//        Map<Direction, List<Position>> positions = rook.getMovablePositions(new Position("d4"));
//    }
}
