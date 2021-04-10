package chess.domain.piece.king;

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

class KingTest {

    private Piece whiteKing;
    private Piece blackKing;

    @BeforeEach
    void setUp() {
        whiteKing = King.getInstanceOf(Owner.WHITE);
        blackKing = King.getInstanceOf(Owner.BLACK);
    }

    @Test
    @DisplayName("BlackKing 과 WhiteKing 인스턴스 잘 가져온다.")
    void getInstanceOfTest() {
        assertThat(whiteKing).isInstanceOf(WhiteKing.class);
        assertThat(blackKing).isInstanceOf(BlackKing.class);
    }

    @Test
    @DisplayName("getInstanceOf의 인자로 NONE 을 넘기면 예외가 발생한다.")
    void getInstanceOfThrowExceptionTest() {
        assertThatThrownBy(() -> {
            King.getInstanceOf(Owner.NONE);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("Invalid King");
    }

    @Test
    @DisplayName("King 의 최대 이동거리를 반환한다.")
    void maxDistanceTest() {
        //given
        int kingMaxDistance = whiteKing.maxDistance();

        //then
        assertThat(kingMaxDistance).isEqualTo(1);
    }

    @Test
    @DisplayName("King 의 심볼 반환된다.")
    void getSymbolTest() {
        //given
        String whiteKingSymbol = whiteKing.getSymbol();
        String blackKingSymbol = blackKing.getSymbol();

        //then
        assertThat(whiteKingSymbol).isEqualTo("k");
        assertThat(blackKingSymbol).isEqualTo("K");
    }

    @ParameterizedTest(name = "King 의 이동 가능 경로 반환한다.")
    @ValueSource(strings = {"d4", "e4", "f4", "d3", "f3", "d2", "e2", "f2"})
    void movablePathTest(String input) {
        //given
        List<Path> kingPaths = whiteKing.movablePath(Position.of("e3"));
        List<Position> kingPathsList = kingPaths.stream().flatMap(Path::stream).collect(Collectors.toList());
        Position target = Position.of(input);

        //then
        assertThat(kingPathsList).contains(target);
    }
}