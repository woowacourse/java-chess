package chess.domain.piece.bishop;

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

class BishopTest {

    private Piece whiteBishop;
    private Piece blackBishop;

    @BeforeEach
    void setUp() {
        whiteBishop = Bishop.getInstanceOf(Owner.WHITE);
        blackBishop = Bishop.getInstanceOf(Owner.BLACK);
    }

    @Test
    @DisplayName("BlackBishop 과 WhiteBishop 인스턴스 잘 가져온다.")
    void getInstanceOfTest() {
        assertThat(whiteBishop).isInstanceOf(WhiteBishop.class);
        assertThat(blackBishop).isInstanceOf(BlackBishop.class);
    }

    @Test
    @DisplayName("getInstanceOf의 인자로 NONE 을 넘기면 예외가 발생한다.")
    void getInstanceOfThrowExceptionTest() {
        assertThatThrownBy(() -> {
            Bishop.getInstanceOf(Owner.NONE);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("Invalid Bishop");
    }

    @Test
    @DisplayName("Bishop 의 최대 이동거리를 반환한다.")
    void maxDistanceTest() {
        //given
        int bishopMaxDistance = whiteBishop.maxDistance();

        //then
        assertThat(bishopMaxDistance).isEqualTo(7);
    }

    @Test
    @DisplayName("Bishop 의 심볼 반환된다.")
    void getSymbolTest() {
        //given
        String whiteBishopSymbol = whiteBishop.getSymbol();
        String blackBishopSymbol = blackBishop.getSymbol();

        //then
        assertThat(whiteBishopSymbol).isEqualTo("b");
        assertThat(blackBishopSymbol).isEqualTo("B");
    }

    @ParameterizedTest(name = "Bishop 의 이동 가능 경로 반환한다.")
    @ValueSource(strings = {"c3", "b2", "a1", "e3", "f2", "g1", "c5", "b6", "a7", "e5", "f6", "g7", "h8"})
    void movablePathTest(String input) {
        //given
        List<Path> bishopPaths = whiteBishop.movablePath(Position.of("d4"));
        List<Position> bishopPathsList = bishopPaths.stream().flatMap(Path::stream).collect(Collectors.toList());
        Position target = Position.of(input);

        //then
        assertThat(bishopPathsList).contains(target);
    }
}