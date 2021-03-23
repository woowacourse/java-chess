package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.TeamType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ChessBoardGeneratorTest {

    private final Map<Coordinate, Cell> cells = ChessBoardGenerator.generateDefaultChessBoard();

    private static Stream<Arguments> getDefaultBlackPieces() {
        return Stream.of(
                Arguments.of(Coordinate.from("a8"), "R"),
                Arguments.of(Coordinate.from("b8"), "N"),
                Arguments.of(Coordinate.from("c8"), "B"),
                Arguments.of(Coordinate.from("d8"), "Q"),
                Arguments.of(Coordinate.from("e8"), "K"),
                Arguments.of(Coordinate.from("f8"), "B"),
                Arguments.of(Coordinate.from("g8"), "N"),
                Arguments.of(Coordinate.from("h8"), "R")
        );
    }

    private static Stream<Arguments> getDefaultWhitePieces() {
        return Stream.of(
                Arguments.of(Coordinate.from("a1"), "r"),
                Arguments.of(Coordinate.from("b1"), "n"),
                Arguments.of(Coordinate.from("c1"), "b"),
                Arguments.of(Coordinate.from("d1"), "q"),
                Arguments.of(Coordinate.from("e1"), "k"),
                Arguments.of(Coordinate.from("f1"), "b"),
                Arguments.of(Coordinate.from("g1"), "n"),
                Arguments.of(Coordinate.from("h1"), "r")
        );
    }

    @DisplayName("8 * 8 크기의 체스 보드를 생성한다.")
    @Test
    void generate() {
        int chessBoardSize = File.values().length * Rank.values().length;

        assertThat(cells).hasSize(chessBoardSize);
    }

    @DisplayName("초기 흑의 기물들이 초기화된다.")
    @ParameterizedTest
    @MethodSource("getDefaultBlackPieces")
    void initializeBlackPieces(Coordinate blackPieceCoordinate, String pieceName) {
        Piece piece = cells.get(blackPieceCoordinate).getPiece();
        String name = piece.getName();

        assertThat(name).isEqualTo(pieceName);
    }

    @DisplayName("초기 백의 기물들이 초기화된다.")
    @ParameterizedTest
    @MethodSource("getDefaultWhitePieces")
    void initializeWhitePieces(Coordinate blackPieceCoordinate, String pieceName) {
        Piece piece = cells.get(blackPieceCoordinate).getPiece();
        String name = piece.getName();

        assertThat(name).isEqualTo(pieceName);
    }

    @DisplayName("Rank 7와 Rank 2의 기물들은 각각 흑과 백의 Pawn들로 초기화된다.")
    @Test
    void initializeDefaultPawns() {
        boolean isAllBlackPawns = checkPawnsAt(TeamType.BLACK, Rank.SEVEN);
        boolean isAllWhitePawns = checkPawnsAt(TeamType.WHITE, Rank.TWO);

        assertThat(isAllBlackPawns).isTrue();
        assertThat(isAllWhitePawns).isTrue();
    }

    private boolean checkPawnsAt(TeamType teamType, Rank rank) {
        return Arrays.stream(File.values())
                .map(file -> new Coordinate(file, rank))
                .map(cells::get)
                .allMatch(cell -> cell.isTeamOf(teamType) && cell.hasPawn());
    }
}
