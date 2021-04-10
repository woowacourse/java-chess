package chess.domain.board.position;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import chess.domain.piece.pawn.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PathTest {

    @Test
    @DisplayName("Path 객체 생성된다.")
    void createPathTest() {
        Path path = Path.of(Arrays.asList(
                Position.of("a1"),
                Position.of("a2")
        ));

        assertThat(path).isInstanceOf(Path.class);
    }

    @ParameterizedTest(name = "Path 안에 특정 Position 이 있는지 제대로 검사된다.")
    @CsvSource({"a1, true", "a2, true", "a3, false"})
    void containsTest(String path, boolean isContain) {
        Path paths = Path.of(Arrays.asList(
                Position.of("a1"),
                Position.of("a2")
        ));

        assertThat(paths.contains(Position.of(path))).isEqualTo(isContain);
    }

    @ParameterizedTest(name = "Path 체스말에 의해 필터링 된다.")
    @CsvSource({"a6, true", "a5, true", "a4, false"})
    void filterChessPieceTest(String path, boolean isContain) {
        Board board = BoardInitializer.initiateBoard();
        Piece sourcePiece = Pawn.getInstanceOf(Owner.BLACK);
        Position source = Position.of("a7");

        List<Path> paths = sourcePiece.movablePath(source);

        Path filterPath = Path.filterPaths(paths, source, board);

        assertThat(filterPath.contains(Position.of(path))).isEqualTo(isContain);
    }

    @Test
    @DisplayName("Path 객체 필드를 가져오지않고 stream() 메서드를 바로 사용할 수 있다.")
    void streamTest() {
        Path path = Path.of(Arrays.asList(
                Position.of("a1"),
                Position.of("a2")
        ));

        assertThat(path.stream()).isInstanceOf(Stream.class);
    }
}