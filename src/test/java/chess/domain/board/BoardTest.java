package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Piece;
import chess.domain.player.TeamType;
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

    @DisplayName("move 명령 - 보드에 현재 위치의 기물이 존재하면, 반환한다. - 백팀")
    @Test
    void findPieceOnBoard_WhiteTeam() {
        String currentCoordinate = "b2";
        TeamType teamType = TeamType.WHITE;

        Piece piece = BOARD.find(currentCoordinate, teamType);

        assertThat(piece.getName()).isEqualTo("p");
        assertThat(piece.isTeamOf(teamType)).isTrue();
    }

    @DisplayName("move 명령 - 보드에 현재 위치의 기물이 존재하면, 반환한다. - 흑팀")
    @Test
    void findPieceOnBoard_BlackTeam() {
        String currentCoordinate = "c8";
        TeamType teamType = TeamType.BLACK;

        Piece piece = BOARD.find(currentCoordinate, teamType);

        assertThat(piece.getName()).isEqualTo("B");
        assertThat(piece.isTeamOf(teamType)).isTrue();
    }

    @DisplayName("move 명령 - 보드 현재 위치의 기물이 존재하지 않으면, 예외가 발생한다. - 백팀")
    @Test
    void findPieceOnBoard_WhiteTeam_EmptyCell() {
        String currentCoordinate = "b3";
        TeamType teamType = TeamType.WHITE;

        assertThatThrownBy(() -> BOARD.find(currentCoordinate, teamType))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("말이 존재하지 않습니다.");
    }

    @DisplayName("move 명령 - 보드 현재 위치에 자신의 기물이 존재하지 않으면, 예외가 발생한다. - 백팀")
    @Test
    void findPieceOnBoard_WhiteTeam_NotMyPiece() {
        String currentCoordinate = "e7";
        TeamType teamType = TeamType.WHITE;

        assertThatThrownBy(() -> BOARD.find(currentCoordinate, teamType))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("자신의 말이 아닙니다.");
    }

    @DisplayName("move 명령 - 보드 현재 위치의 기물이 존재하지 않으면, 예외가 발생한다. - 흑팀")
    @Test
    void findPieceOnBoard_BlackTeam_EmptyCell() {
        String currentCoordinate = "e5";
        TeamType teamType = TeamType.BLACK;

        assertThatThrownBy(() -> BOARD.find(currentCoordinate, teamType))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("말이 존재하지 않습니다.");
    }

    @DisplayName("move 명령 - 보드 현재 위치에 자신의 기물이 존재하지 않으면, 예외가 발생한다. - 흑팀")
    @Test
    void findPieceOnBoard_BlackTeam_NotMyPiece() {
        String currentCoordinate = "a1";
        TeamType teamType = TeamType.BLACK;

        assertThatThrownBy(() -> BOARD.find(currentCoordinate, teamType))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("자신의 말이 아닙니다.");
    }
}
