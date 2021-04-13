package chess.domain.piece.knight;

import chess.domain.board.position.Path;
import chess.domain.board.position.Position;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KnightTest {

    private Piece whiteKnight;
    private Piece blackKnight;

    @BeforeEach
    void setUp() {
        whiteKnight = Knight.getInstanceOf(Owner.WHITE);
        blackKnight = Knight.getInstanceOf(Owner.BLACK);
    }

    @Test
    @DisplayName("BlackKnight 외 WhiteKnight 인스턴스 잘 가져온다.")
    void getInstanceOfTest() {
        assertThat(whiteKnight).isInstanceOf(WhiteKnight.class);
        assertThat(blackKnight).isInstanceOf(BlackKnight.class);
    }

    @Test
    @DisplayName("getInstanceOf의 인자로 NONE 을 넘기면 예외가 발생한다.")
    void getInstanceOfThrowExceptionTest() {
        assertThatThrownBy(() -> {
            Knight.getInstanceOf(Owner.NONE);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("Invalid Knight");
    }

    @Test
    @DisplayName("Knight 의 최대 이동거리를 반환한다.")
    void maxDistanceTest() {
        //given
        int knightMaxDistance = blackKnight.maxDistance();

        //then
        assertThat(knightMaxDistance).isEqualTo(1);
    }

    @Test
    @DisplayName("Knight 의 심볼 반환된다.")
    void getSymbolTest() {
        //given
        String whiteKnightSymbol = whiteKnight.getSymbol();
        String blackKnightSymbol = blackKnight.getSymbol();

        //then
        assertThat(whiteKnightSymbol).isEqualTo("n");
        assertThat(blackKnightSymbol).isEqualTo("N");
    }

    @Test
    @DisplayName("Knight 의 이동 가능 경로 반환한다.")
    void movablePathTest() {
        //when
        List<Path> knightPaths = blackKnight.movablePath(Position.of("b1"));

        List<Position> knightPathList = knightPaths.stream().flatMap(Path::stream).collect(Collectors.toList());

        assertThat(knightPathList).containsExactly(
                Position.of("c3"),
                Position.of("a3"),
                Position.of("d2")
        );
    }
}