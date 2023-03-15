package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import chess.domain.piece.Bishop;
import chess.domain.piece.Camp;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.point.File;
import chess.domain.point.Point;
import chess.domain.point.Rank;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

class PiecePointsTest {

    @Test
    @DisplayName("64개의 기물이 생성된다.")
    void initialChessBoardTest() {
        PiecePoints piecePoints = new PiecePoints();

        assertThat(piecePoints.getPiecePoint()).hasSize(64);
    }

    @Nested
    @DisplayName("귀족 랭크")
    @TestInstance(Lifecycle.PER_CLASS)
    class White {
        @ParameterizedTest(name = "화이트 진영의 기물이 기본 위치에 배치된다.")
        @MethodSource("initialBoardStateWhite")
        void judgePiecesTest2(Point point, Piece piece) {
            PiecePoints piecePoints = new PiecePoints();
            assertThat(piecePoints.getPiecePoint().get(point))
                    .isEqualTo(piece);
        }

        Stream<Arguments> initialBoardStateWhite() {
            return Stream.of(
                    arguments(new Point(File.A, Rank.ONE), new Rook(Camp.WHITE)),
                    arguments(new Point(File.B, Rank.ONE), new Knight(Camp.WHITE)),
                    arguments(new Point(File.C, Rank.ONE), new Bishop(Camp.WHITE)),
                    arguments(new Point(File.D, Rank.ONE), new Queen(Camp.WHITE)),
                    arguments(new Point(File.E, Rank.ONE), new King(Camp.WHITE)),
                    arguments(new Point(File.F, Rank.ONE), new Bishop(Camp.WHITE)),
                    arguments(new Point(File.G, Rank.ONE), new Knight(Camp.WHITE)),
                    arguments(new Point(File.H, Rank.ONE), new Rook(Camp.WHITE))
            );
        }


        @ParameterizedTest(name = "블랙 진영의 기물이 기본 위치에 배치된다.")
        @MethodSource("initialBoardStateBlack")
        void judgePiecesTest3(Point point, Piece piece) {
            PiecePoints piecePoints = new PiecePoints();

            assertThat(piecePoints.getPiecePoint().get(point))
                    .isEqualTo(piece);
        }

        Stream<Arguments> initialBoardStateBlack() {
            return Stream.of(
                    arguments(new Point(File.A, Rank.EIGHT), new Rook(Camp.BLACK)),
                    arguments(new Point(File.B, Rank.EIGHT), new Knight(Camp.BLACK)),
                    arguments(new Point(File.C, Rank.EIGHT), new Bishop(Camp.BLACK)),
                    arguments(new Point(File.D, Rank.EIGHT), new Queen(Camp.BLACK)),
                    arguments(new Point(File.E, Rank.EIGHT), new King(Camp.BLACK)),
                    arguments(new Point(File.F, Rank.EIGHT), new Bishop(Camp.BLACK)),
                    arguments(new Point(File.G, Rank.EIGHT), new Knight(Camp.BLACK)),
                    arguments(new Point(File.H, Rank.EIGHT), new Rook(Camp.BLACK))
            );
        }
    }


    @ParameterizedTest(name = "폰 기물이 기본 위치에 배치된다. ")
    @CsvSource(value = {"TWO, WHITE", "SEVEN, BLACK"})
    void judgePiecesTest4(Rank rank, Camp camp) {
        PiecePoints piecePoints = new PiecePoints();

        for (File file : File.values()) {
            Point point = new Point(file, rank);
            assertThat(piecePoints.getPiecePoint().get(point))
                    .isEqualTo(new Pawn(camp));
        }
    }

    @ParameterizedTest(name = "빈공간이 존재한다.")
    @EnumSource(names = {"THREE", "FOUR", "FIVE", "SIX"})
    void judgePiecesTest5(Rank rank) {
        PiecePoints piecePoints = new PiecePoints();

        for (File file : File.values()) {
            Point point = new Point(file, rank);
            assertThat(piecePoints.getPiecePoint().get(point))
                    .isEqualTo(new Empty());
        }
    }
}
