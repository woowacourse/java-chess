package chess.domain.piece.queen;

import chess.domain.board.position.Path;
import chess.domain.board.position.Position;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QueenTest {

    private Piece whiteQueen;
    private Piece blackQueen;

    @BeforeEach
    void setUp() {
        whiteQueen = Queen.getInstanceOf(Owner.WHITE);
        blackQueen = Queen.getInstanceOf(Owner.BLACK);
    }

    @Test
    @DisplayName("BlackQueen 과 WhiteQueen 인스턴스 잘 가져온다.")
    void getInstanceOfTest() {
        assertThat(whiteQueen).isInstanceOf(WhiteQueen.class);
        assertThat(blackQueen).isInstanceOf(BlackQueen.class);
    }

    @Test
    @DisplayName("getInstanceOf의 인자로 NONE을 넘기면 예외가 발생한다.")
    void getInstanceOfThrowExceptionTest() {
        assertThatThrownBy(() -> {
            Queen.getInstanceOf(Owner.NONE);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("Invalid Queen");
    }

    @Test
    @DisplayName("퀸의 최대 이동거리를 반환한다.")
    void maxDistanceTest() {
        //given
        int queenMaxDistance = whiteQueen.maxDistance();

        //then
        assertThat(queenMaxDistance).isEqualTo(7);
    }

    @Test
    @DisplayName("Queen 의 심볼 반환된다.")
    void getSymbolTest() {
        //given
        String whiteQueenSymbol = whiteQueen.getSymbol();
        String blackQueenSymbol = blackQueen.getSymbol();

        //then
        assertThat(whiteQueenSymbol).isEqualTo("q");
        assertThat(blackQueenSymbol).isEqualTo("Q");
    }

    @ParameterizedTest(name = "Queen 의 이동 가능 경로 반환한다.")
    @ValueSource(strings = {"c1", "b1", "a1", "e1", "f1", "g1", "h1", "d2", "d3", "d4", "d5",
            "d6", "d7", "d8", "c2", "b3", "a4", "e2", "f3", "g4", "h5"})
    void movablePathTest(String targetInput) {
        //given
        List<Path> queenPaths = whiteQueen.movablePath(Position.of("d1"));
        List<Position> queenPathsList = queenPaths.stream().flatMap(Path::stream).collect(Collectors.toList());

        //given
        Position target = Position.of(targetInput);

        //then
        assertThat(queenPathsList).contains(target);
    }
}