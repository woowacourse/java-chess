package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Pieces;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("폰 테스트")
class PawnTest {

    private Piece whitePawn;
    private Piece blackPawn;
    private Map<Position, Piece> pieces;

    @BeforeEach
    void setUp() {
        whitePawn = new Pawn(Color.WHITE, Position.of("b2"));
        blackPawn = new Pawn(Color.BLACK, Position.of("c7"));

        Pieces pieces = new Pieces();
        pieces.init();

        this.pieces = pieces.pieces();
    }

    @Test
    @DisplayName("1칸 전진 테스트")
    void moveOne() {
        assertThatCode(() -> whitePawn.move(Position.of("b3"), pieces)).doesNotThrowAnyException();

        assertThatCode(() -> blackPawn.move(Position.of("c6"), pieces)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("전진 위치에 기물이 있을 경우 예외 발생")
    void moveOneException() {
        Position positionB3 = Position.of("b3");
        pieces.put(positionB3, new Pawn(Color.BLACK, positionB3));
        assertThatThrownBy(() -> whitePawn.move(positionB3, pieces)).isInstanceOf(IllegalArgumentException.class);

        pieces.put(positionB3, new Pawn(Color.WHITE, positionB3));
        assertThatThrownBy(() -> whitePawn.move(positionB3, pieces)).isInstanceOf(IllegalArgumentException.class);

        Position positionC6 = Position.of("c6");
        pieces.put(positionC6, new Pawn(Color.BLACK, positionC6));
        assertThatThrownBy(() -> blackPawn.move(positionC6, pieces)).isInstanceOf(IllegalArgumentException.class);

        pieces.put(positionC6, new Pawn(Color.WHITE, positionC6));
        assertThatThrownBy(() -> blackPawn.move(positionC6, pieces)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("2칸 전진 테스트")
    void moveTwo() {
        assertThatCode(() -> whitePawn.move(Position.of("b4"), pieces)).doesNotThrowAnyException();

        assertThatCode(() -> blackPawn.move(Position.of("c5"), pieces)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("첫 이동이 아닌데 2칸을 전진할 경우 예외 발생")
    void moveNotFirstTwoException() {
        whitePawn = new Pawn(Color.WHITE, Position.of("b3"));
        assertThatThrownBy(() -> whitePawn.move(Position.of("b5"), pieces)).isInstanceOf(IllegalArgumentException.class);

        blackPawn = new Pawn(Color.BLACK, Position.of("c6"));
        assertThatThrownBy(() -> blackPawn.move(Position.of("c4"), pieces)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("2칸 이동 시 경로 중간에 기물이 있을 경우 예외 발생")
    void moveTwoObstacleException() {
        pieces.put(Position.of("b3"), new Pawn(Color.BLACK, Position.of("b3")));
        assertThatThrownBy(() -> whitePawn.move(Position.of("b4"), pieces)).isInstanceOf(IllegalArgumentException.class);

        pieces.put(Position.of("b3"), new Pawn(Color.WHITE, Position.of("b3")));
        assertThatThrownBy(() -> whitePawn.move(Position.of("b4"), pieces)).isInstanceOf(IllegalArgumentException.class);

        pieces.put(Position.of("c6"), new Pawn(Color.WHITE, Position.of("c6")));
        assertThatThrownBy(() -> blackPawn.move(Position.of("c5"), pieces)).isInstanceOf(IllegalArgumentException.class);

        pieces.put(Position.of("c6"), new Pawn(Color.BLACK, Position.of("c6")));
        assertThatThrownBy(() -> blackPawn.move(Position.of("c5"), pieces)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("2칸 이동하는 위치에 기물이 있는 경우 예외 발생")
    void moveTwoTargetPositionObstacleException() {
        pieces.put(Position.of("b4"), new Pawn(Color.BLACK, Position.of("b4")));
        assertThatThrownBy(() -> whitePawn.move(Position.of("b4"), pieces)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("1칸 대각선에 있는 상대방 기물 잡기 테스트")
    void moveDiagonal() {
        pieces.put(Position.of("c3"), new Pawn(Color.BLACK ,Position.of("c3")));
        pieces.put(Position.of("a3"), new Pawn(Color.BLACK ,Position.of("a3")));
        assertThatCode(() -> whitePawn.move(Position.of("a3"), pieces)).doesNotThrowAnyException();
        assertThatCode(() -> whitePawn.move(Position.of("c3"), pieces)).doesNotThrowAnyException();

        pieces.put(Position.of("b6"), new Pawn(Color.WHITE ,Position.of("b6")));
        pieces.put(Position.of("d6"), new Pawn(Color.WHITE ,Position.of("d6")));
        assertThatCode(() -> blackPawn.move(Position.of("b6"), pieces)).doesNotThrowAnyException();
        assertThatCode(() -> blackPawn.move(Position.of("d6"), pieces)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("1칸 대각선 이동시 타겟 위치가 블랭크일 경우 이동 불가")
    void moveDiagonalException() {
        assertThatThrownBy(() -> whitePawn.move(Position.of("a3"), pieces)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> whitePawn.move(Position.of("c3"), pieces)).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> blackPawn.move(Position.of("b6"), pieces)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> blackPawn.move(Position.of("d6"), pieces)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("그 외 이동 에러 검사")
    void validate() {
        whitePawn = new Pawn(Color.WHITE, Position.of("b4"));
        assertThatThrownBy(() -> whitePawn.move(Position.of("b3"), pieces)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> whitePawn.move(Position.of("b2"), pieces)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> whitePawn.move(Position.of("a3"), pieces)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> whitePawn.move(Position.of("c3"), pieces)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> whitePawn.move(Position.of("a4"), pieces)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> whitePawn.move(Position.of("c4"), pieces)).isInstanceOf(IllegalArgumentException.class);

        blackPawn = new Pawn(Color.BLACK, Position.of("c5"));
        assertThatThrownBy(() -> blackPawn.move(Position.of("c6"), pieces)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> blackPawn.move(Position.of("c7"), pieces)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> blackPawn.move(Position.of("b6"), pieces)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> blackPawn.move(Position.of("d6"), pieces)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> blackPawn.move(Position.of("b5"), pieces)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> blackPawn.move(Position.of("d5"), pieces)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("점수 반환 테스트")
    void score() {
        assertThat(new Pawn(Color.BLACK, Position.of("a1")).score()).isEqualTo(1);
    }

    @Test
    @DisplayName("흰색일때 심볼 테스트")
    void whiteSymbol() {
        Piece piece = new Pawn(Color.WHITE, Position.of("a1"));
        assertThat(piece.symbol()).isEqualTo("p");
    }

    @Test
    @DisplayName("검정색일때 심볼 테스트")
    void blackSymbol() {
        Piece piece = new Pawn(Color.BLACK, Position.of("a1"));
        assertThat(piece.symbol()).isEqualTo("P");
    }

    @Test
    @DisplayName("이동이 가능한 위치 테스트 - 초기위치")
    void movablePositions() {
        List<Position> movablePositions = Arrays.asList(
            Position.of("b4"),
            Position.of("b3")
        );

        List<Position> positions = whitePawn.movablePositions(pieces);
        assertThat(positions).hasSize(movablePositions.size());
        for (Position position : positions) {
            assertThat(positions).contains(position);
        }
    }
    
    @Test
    @DisplayName("이동이 가능한 위치 테스트 - 초기위치 X")
    void movablePositions2() {
        pieces.put(Position.of("b5"), new Pawn(Color.WHITE, Position.of("b5")));
        List<Position> movablePositions = Arrays.asList(
            Position.of("b5"),
            Position.of("c5")
        );
        blackPawn = blackPawn.move(Position.of("c6"), pieces);
        List<Position> positions = blackPawn.movablePositions(pieces);
        assertThat(positions).hasSize(movablePositions.size());
        for (Position position : positions) {
            assertThat(positions).contains(position);
        }
    }
}