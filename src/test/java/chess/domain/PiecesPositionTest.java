package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import chess.domain.piece.Bishop;
import chess.domain.piece.Camp;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
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

class PiecesPositionTest {

    @Test
    @DisplayName("32개의 기물만 생성된다.")
    void initialChessBoardTest() {
        PiecesPosition piecesPosition = new PiecesPosition();

        assertThat(piecesPosition.getPiecesPosition()).hasSize(32);
    }

    @Nested
    @DisplayName("귀족 랭크")
    @TestInstance(Lifecycle.PER_CLASS)
    class White {
        @ParameterizedTest(name = "화이트 진영의 기물이 기본 위치에 배치된다.")
        @MethodSource("initialBoardStateWhite")
        void judgePiecesTest2(Position position, Piece piece) {
            PiecesPosition piecesPosition = new PiecesPosition();
            assertThat(piecesPosition.getPiecesPosition().get(position))
                    .isEqualTo(piece);
        }

        Stream<Arguments> initialBoardStateWhite() {
            return Stream.of(
                    arguments(Position.of(File.A, Rank.ONE), new Rook(Camp.WHITE)),
                    arguments(Position.of(File.B, Rank.ONE), new Knight(Camp.WHITE)),
                    arguments(Position.of(File.C, Rank.ONE), new Bishop(Camp.WHITE)),
                    arguments(Position.of(File.D, Rank.ONE), new Queen(Camp.WHITE)),
                    arguments(Position.of(File.E, Rank.ONE), new King(Camp.WHITE)),
                    arguments(Position.of(File.F, Rank.ONE), new Bishop(Camp.WHITE)),
                    arguments(Position.of(File.G, Rank.ONE), new Knight(Camp.WHITE)),
                    arguments(Position.of(File.H, Rank.ONE), new Rook(Camp.WHITE))
            );
        }


        @ParameterizedTest(name = "블랙 진영의 기물이 기본 위치에 배치된다.")
        @MethodSource("initialBoardStateBlack")
        void judgePiecesTest3(Position position, Piece piece) {
            PiecesPosition piecesPosition = new PiecesPosition();

            assertThat(piecesPosition.getPiecesPosition().get(position))
                    .isEqualTo(piece);
        }

        Stream<Arguments> initialBoardStateBlack() {
            return Stream.of(
                    arguments(Position.of(File.A, Rank.EIGHT), new Rook(Camp.BLACK)),
                    arguments(Position.of(File.B, Rank.EIGHT), new Knight(Camp.BLACK)),
                    arguments(Position.of(File.C, Rank.EIGHT), new Bishop(Camp.BLACK)),
                    arguments(Position.of(File.D, Rank.EIGHT), new Queen(Camp.BLACK)),
                    arguments(Position.of(File.E, Rank.EIGHT), new King(Camp.BLACK)),
                    arguments(Position.of(File.F, Rank.EIGHT), new Bishop(Camp.BLACK)),
                    arguments(Position.of(File.G, Rank.EIGHT), new Knight(Camp.BLACK)),
                    arguments(Position.of(File.H, Rank.EIGHT), new Rook(Camp.BLACK))
            );
        }
    }


    @ParameterizedTest(name = "폰 기물이 기본 위치에 배치된다.")
    @CsvSource(value = {"TWO, WHITE", "SEVEN, BLACK"})
    void judgePiecesTest4(Rank rank, Camp camp) {
        PiecesPosition piecesPosition = new PiecesPosition();

        for (File file : File.values()) {
            Position position = Position.of(file, rank);
            assertThat(piecesPosition.getPiecesPosition().get(position))
                    .isEqualTo(new Pawn(camp));
        }
    }

    @ParameterizedTest(name = "빈공간이 존재한다.")
    @EnumSource(names = {"THREE", "FOUR", "FIVE", "SIX"})
    void judgePiecesTest5(Rank rank) {
        PiecesPosition piecesPosition = new PiecesPosition();

        for (File file : File.values()) {
            Position position = Position.of(file, rank);
            assertThat(piecesPosition.isPieceExist(position)).isFalse();
        }
    }

    @Test
    @DisplayName("위치로 기물을 이동할 수 있다.")
    void movePieceByPositionTest() {
        Position whitePawnPosition = Position.of(File.A, Rank.TWO);
        Position emptyPosition = Position.of(File.A, Rank.FOUR);

        PiecesPosition piecesPosition = new PiecesPosition();

        piecesPosition.movePiece(whitePawnPosition, emptyPosition);

        assertThat(piecesPosition.peekPiece(emptyPosition)).isInstanceOf(Pawn.class);
    }

    @Test
    @DisplayName("기물이 이동한 뒤, 이전 위치에는 빈 값이 된다.")
    void cleanUpPositionTest() {
        Position whitePawnPosition = Position.of(File.A, Rank.TWO);
        PiecesPosition piecesPosition = new PiecesPosition();
        assertThat(piecesPosition.peekPiece(whitePawnPosition)).isInstanceOf(Pawn.class);

        piecesPosition.movePiece(whitePawnPosition, Position.of(File.A, Rank.FOUR));

        assertThat(piecesPosition.isPieceExist(whitePawnPosition)).isFalse();
    }
}
