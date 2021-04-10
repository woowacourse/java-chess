package chess.domain.piece.rook;

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

class RookTest {
    private Piece whiteRook;
    private Piece blackRook;

    @BeforeEach
    void setUp() {
        whiteRook = Rook.getInstanceOf(Owner.WHITE);
        blackRook = Rook.getInstanceOf(Owner.BLACK);
    }

    @Test
    @DisplayName("BlackRook 과 WhiteRook 인스턴스 잘 가져온다.")
    void getInstanceOfTest() {
        assertThat(whiteRook).isInstanceOf(WhiteRook.class);
        assertThat(blackRook).isInstanceOf(BlackRook.class);
    }

    @Test
    @DisplayName("getInstanceOf의 인자로 NONE을 넘기면 예외가 발생한다.")
    void getInstanceOfThrowExceptionTest() {
        assertThatThrownBy(() -> {
            Rook.getInstanceOf(Owner.NONE);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("Invalid Rook");
    }

    @Test
    @DisplayName("Rook 의 최대 이동거리를 반환한다.")
    void maxDistanceTest() {
        //given
        int RookMaxDistance = blackRook.maxDistance();

        //then
        assertThat(RookMaxDistance).isEqualTo(7);
    }

    @Test
    @DisplayName("Rook 의 심볼 반환된다.")
    void getSymbolTest() {
        //given
        String whiteRookSymbol = whiteRook.getSymbol();
        String blackRookSymbol = blackRook.getSymbol();

        //then
        assertThat(whiteRookSymbol).isEqualTo("r");
        assertThat(blackRookSymbol).isEqualTo("R");
    }

    @ParameterizedTest(name = "Rook 의 이동 가능 경로 반환한다.")
    @ValueSource(strings = {"c4", "b4", "a4", "e4", "f4", "g4", "d3", "d2", "d1", "d5", "d6", "d7", "d8"})
    void movablePathTest(String input) {
        //given
        List<Path> rookPaths = whiteRook.movablePath(Position.of("d4"));
        List<Position> rookPathsList = rookPaths.stream().flatMap(Path::stream).collect(Collectors.toList());
        Position target = Position.of(input);

        //then
        assertThat(rookPathsList).contains(target);
    }
}