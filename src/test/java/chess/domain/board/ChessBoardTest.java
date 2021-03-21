package chess.domain.board;

import chess.domain.piece.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ChessBoardTest {
    private static ChessBoard ChessBOARD;

//    private static Stream<Arguments> getDefaultBlackPieces() {
//        ChessBOARD = new ChessBoard(ChessBoardGenerator.generateEmptyBoard());
//        ChessBOARD.initializeDefaultPieces();
//        return Stream.of(
//                Arguments.of(ChessBOARD.find(Coordinate.from("a8")), "R"),
//                Arguments.of(ChessBOARD.find(Coordinate.from("b8")), "N"),
//                Arguments.of(ChessBOARD.find(Coordinate.from("c8")), "B"),
//                Arguments.of(ChessBOARD.find(Coordinate.from("d8")), "Q"),
//                Arguments.of(ChessBOARD.find(Coordinate.from("e8")), "K"),
//                Arguments.of(ChessBOARD.find(Coordinate.from("f8")), "B"),
//                Arguments.of(ChessBOARD.find(Coordinate.from("g8")), "N"),
//                Arguments.of(ChessBOARD.find(Coordinate.from("h8")), "R")
//        );
//    }
//
//    private static Stream<Arguments> getDefaultWhitePieces() {
//        ChessBOARD = new ChessBoard(ChessBoardGenerator.generateEmptyBoard());
//        ChessBOARD.initializeDefaultPieces();
//        return Stream.of(
//                Arguments.of(ChessBOARD.find(Coordinate.from("a1")), "r"),
//                Arguments.of(ChessBOARD.find(Coordinate.from("b1")), "n"),
//                Arguments.of(ChessBOARD.find(Coordinate.from("c1")), "b"),
//                Arguments.of(ChessBOARD.find(Coordinate.from("d1")), "q"),
//                Arguments.of(ChessBOARD.find(Coordinate.from("e1")), "k"),
//                Arguments.of(ChessBOARD.find(Coordinate.from("f1")), "b"),
//                Arguments.of(ChessBOARD.find(Coordinate.from("g1")), "n"),
//                Arguments.of(ChessBOARD.find(Coordinate.from("h1")), "r")
//        );
//    }

    @BeforeEach
    void setup() {
        ChessBOARD = new ChessBoard(ChessBoardGenerator.generateEmptyBoard());
        ChessBOARD.initializeDefaultPieces();
    }
//
//    @DisplayName("초기 기물 배치 - 8Rank 흑의 초기 기물들 배치")
//    @MethodSource("getDefaultBlackPieces")
//    @ParameterizedTest
//    void boardInitialization_BlackPieces(Cell cell, String pieceName) {
//        assertThat(cell.getPiece().getName()).isEqualTo(pieceName);
//    }
//
//    @DisplayName("초기 기물 배치 - 1Rank 백의 초기 기물들 배치")
//    @MethodSource("getDefaultWhitePieces")
//    @ParameterizedTest
//    void boardInitialization_WhitePieces(Cell cell, String pieceName) {
//        assertThat(cell.getPiece().getName()).isEqualTo(pieceName);
//    }
//
//    @DisplayName("move 명령 - 보드에 현재 위치의 기물이 존재하면, 반환한다. - 백팀")
//    @Test
//    void findPieceOnBoard_WhiteTeam() {
//        String currentCoordinate = "b2";
//        TeamType teamType = TeamType.WHITE;
//
//        Cell cell = ChessBOARD.find(Coordinate.from(currentCoordinate));
//        Piece piece = cell.getPiece();
//
//        assertThat(piece.getName()).isEqualTo("p");
//        assertThat(piece.isTeamOf(teamType)).isTrue();
//    }
//
//    @DisplayName("move 명령 - 보드에 현재 위치의 기물이 존재하면, 반환한다. - 흑팀")
//    @Test
//    void findPieceOnBoard_BlackTeam() {
//        String currentCoordinate = "c8";
//        TeamType teamType = TeamType.BLACK;
//
//        Cell cell = ChessBOARD.find(Coordinate.from(currentCoordinate));
//        Piece piece = cell.getPiece();
//
//        assertThat(piece.getName()).isEqualTo("B");
//        assertThat(piece.isTeamOf(teamType)).isTrue();
//    }

    @DisplayName("점수 계산")
    @Test
    void calculateScores() {
        ChessBOARD = new ChessBoard(ChessBoardGenerator.generateEmptyBoard());
        Map<Coordinate, Cell> cells = ChessBOARD.getCells();


        cells.get(Coordinate.from("b8")).put(new King(TeamType.BLACK));
        cells.get(Coordinate.from("c8")).put(new Rook(TeamType.BLACK));
        cells.get(Coordinate.from("a7")).put(new Pawn(TeamType.BLACK));
        cells.get(Coordinate.from("c7")).put(new Pawn(TeamType.BLACK));
        cells.get(Coordinate.from("d7")).put(new Bishop(TeamType.BLACK));
        cells.get(Coordinate.from("b6")).put(new Pawn(TeamType.BLACK));
        cells.get(Coordinate.from("e6")).put(new Queen(TeamType.BLACK));

        cells.get(Coordinate.from("f4")).put(new Knight(TeamType.WHITE));

        cells.get(Coordinate.from("g4")).put(new Queen(TeamType.WHITE));

        cells.get(Coordinate.from("f3")).put(new Pawn(TeamType.WHITE));
        cells.get(Coordinate.from("h3")).put(new Pawn(TeamType.WHITE));
        cells.get(Coordinate.from("f2")).put(new Pawn(TeamType.WHITE));
        cells.get(Coordinate.from("g2")).put(new Pawn(TeamType.WHITE));
        cells.get(Coordinate.from("e1")).put(new Rook(TeamType.WHITE));
        cells.get(Coordinate.from("f1")).put(new King(TeamType.WHITE));

        Result result = ChessBOARD.calculateScores();

        assertThat(result.getBlackTeamScore()).isEqualTo(20);
        assertThat(result.getWhiteTeamScore()).isEqualTo(19.5);
    }

    @DisplayName("양 팀의 킹이 모두 살아있을 때")
    @Test
    void allKingsAlive() {
        assertThat(ChessBOARD.isKingCheckmate()).isFalse();
    }

    @DisplayName("흑 팀의 킹이 죽었을 때")
    @Test
    void blackKingDied() {
        Map<Coordinate, Cell> cells = ChessBOARD.getCells();
        cells.remove(Coordinate.from("e8"));
        assertThat(ChessBOARD.isKingCheckmate()).isTrue();
    }

    @DisplayName("백 팀의 킹이 죽었을 때")
    @Test
    void whiteKingDied() {
        Map<Coordinate, Cell> cells = ChessBOARD.getCells();
        cells.remove(Coordinate.from("e1"));
        assertThat(ChessBOARD.isKingCheckmate()).isTrue();
    }

    @DisplayName("initialize 메서드는")
    @Nested
    class Context_initialize {

        private Piece getPiece(File file, Rank rank) {
            return ChessBOARD.getCells().get(new Coordinate(file, rank)).getPiece();
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
}
