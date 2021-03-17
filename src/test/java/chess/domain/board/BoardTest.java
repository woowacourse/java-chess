package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Piece;
import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BoardTest {
    private static final Board BOARD = Board.getInstance();

    private static Stream<Arguments> getDefaultBlackPieces() {
        BOARD.initialize();
        return Stream.of(
            Arguments.of(BOARD.find(new Coordinate(File.A, Rank.EIGHT)), "R"),
            Arguments.of(BOARD.find(new Coordinate(File.B, Rank.EIGHT)), "N"),
            Arguments.of(BOARD.find(new Coordinate(File.C, Rank.EIGHT)), "B"),
            Arguments.of(BOARD.find(new Coordinate(File.D, Rank.EIGHT)), "Q"),
            Arguments.of(BOARD.find(new Coordinate(File.E, Rank.EIGHT)), "K"),
            Arguments.of(BOARD.find(new Coordinate(File.F, Rank.EIGHT)), "B"),
            Arguments.of(BOARD.find(new Coordinate(File.G, Rank.EIGHT)), "N"),
            Arguments.of(BOARD.find(new Coordinate(File.H, Rank.EIGHT)), "R")
            );
    }

    private static Stream<Arguments> getDefaultWhitePieces() {
        BOARD.initialize();
        return Stream.of(
            Arguments.of(BOARD.find(new Coordinate(File.A, Rank.ONE)), "r"),
            Arguments.of(BOARD.find(new Coordinate(File.B, Rank.ONE)), "n"),
            Arguments.of(BOARD.find(new Coordinate(File.C, Rank.ONE)), "b"),
            Arguments.of(BOARD.find(new Coordinate(File.D, Rank.ONE)), "q"),
            Arguments.of(BOARD.find(new Coordinate(File.E, Rank.ONE)), "k"),
            Arguments.of(BOARD.find(new Coordinate(File.F, Rank.ONE)), "b"),
            Arguments.of(BOARD.find(new Coordinate(File.G, Rank.ONE)), "n"),
            Arguments.of(BOARD.find(new Coordinate(File.H, Rank.ONE)), "r")
        );
    }

    @BeforeEach
    void setup() {
        BOARD.initialize();
    }

    @DisplayName("8 * 8 의 빈 체스 판 싱글톤 테스트")
    @Test
    void singleton() {
        assertThat(BOARD).isSameAs(Board.getInstance());
    }

    @DisplayName("initialize 메서드는")
    @Nested
    class Context_initialize {

        private Piece getPiece(File file, Rank rank) {
            return BOARD.find(new Coordinate(file, rank));
        }

        @DisplayName("7Rank를 모두 흑의 폰으로 초기화한다")
        @Test
        void boardInitialization_BlackPawn() {
            boolean isAllBlackPawn = Arrays.stream(File.values())
                .map(file -> getPiece(file, Rank.SEVEN).getName())
                .allMatch(name -> name.equals("P"));

            assertThat(isAllBlackPawn).isTrue();
        }

        @DisplayName("2Rank는 모두 백의 폰으로 초기화한다.")
        @Test
        void boardInitialization_WhitePawn() {
            boolean isAllWhitePawn = Arrays.stream(File.values())
                .map(file -> getPiece(file, Rank.TWO).getName())
                .allMatch(name -> name.equals("p"));

            assertThat(isAllWhitePawn).isTrue();
        }
    }

    @DisplayName("초기 기물 배치 - 8Rank 흑의 초기 기물들 배치")
    @MethodSource("getDefaultBlackPieces")
    @ParameterizedTest
    void boardInitialization_BlackPieces(Piece piece, String pieceName) {
        assertThat(piece.getName()).isEqualTo(pieceName);
    }

    @DisplayName("초기 기물 배치 - 1Rank 백의 초기 기물들 배치")
    @MethodSource("getDefaultWhitePieces")
    @ParameterizedTest
    void boardInitialization_WhitePieces(Piece piece, String pieceName) {
        assertThat(piece.getName()).isEqualTo(pieceName);
    }
}