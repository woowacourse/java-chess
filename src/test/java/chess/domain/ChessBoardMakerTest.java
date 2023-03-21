package chess.domain;

import chess.domain.piece.*;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@TestInstance(Lifecycle.PER_CLASS)
class ChessBoardMakerTest {

    ChessBoard board;

    @BeforeAll
    @DisplayName("32개의 기물이 생성된다.")
    void setUp() {
        board = ChessBoardMaker.create();
    }

    @TestInstance(Lifecycle.PER_CLASS)
    @Nested
    @DisplayName("귀족 랭크")
    class White {
        @ParameterizedTest(name = "화이트 진영의 기물이 기본 위치에 배치된다.")
        @MethodSource("initialBoardStateWhite")
        void judgePiecesTest2(Position position, Piece piece) {
            assertThat(board.getBoard().get(position))
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
            assertThat(board.getBoard().get(position))
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
        for (File file : File.values()) {
            Position position = Position.of(file, rank);
            assertThat(board.getBoard().get(position))
                    .isEqualTo(new Pawn(camp));
        }
    }
}
