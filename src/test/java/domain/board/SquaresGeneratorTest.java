package domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.None;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.position.File;
import domain.position.Position;
import domain.position.PositionGenerator;
import domain.position.Rank;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class SquaresGeneratorTest {

    @Test
    @DisplayName("64개의 칸을 생성한다.")
    void generate_SquaresSize() {
        SquaresGenerator squaresGenerator = new SquaresGenerator();

        Map<Position, Piece> squares = squaresGenerator.generate();

        assertThat(squares).hasSize(64);
    }

    @Test
    @DisplayName("게임 시작 시 폰은 A2 B2 C2 D2 E2 F2 G2 H2에 위치한다.")
    void generate_Pawn() {
        SquaresGenerator squaresGenerator = new SquaresGenerator();
        Map<Position, Piece> squares = squaresGenerator.generate();

        Piece actual1 = squares.get(PositionGenerator.generate(File.A, Rank.TWO));
        Piece actual2 = squares.get(PositionGenerator.generate(File.B, Rank.TWO));
        Piece actual3 = squares.get(PositionGenerator.generate(File.C, Rank.TWO));
        Piece actual4 = squares.get(PositionGenerator.generate(File.D, Rank.TWO));
        Piece actual5 = squares.get(PositionGenerator.generate(File.E, Rank.TWO));
        Piece actual6 = squares.get(PositionGenerator.generate(File.F, Rank.TWO));
        Piece actual7 = squares.get(PositionGenerator.generate(File.G, Rank.TWO));
        Piece actual8 = squares.get(PositionGenerator.generate(File.H, Rank.TWO));

        assertAll(() -> {
            assertThat(actual1).isInstanceOf(Pawn.class);
            assertThat(actual2).isInstanceOf(Pawn.class);
            assertThat(actual3).isInstanceOf(Pawn.class);
            assertThat(actual4).isInstanceOf(Pawn.class);
            assertThat(actual5).isInstanceOf(Pawn.class);
            assertThat(actual6).isInstanceOf(Pawn.class);
            assertThat(actual7).isInstanceOf(Pawn.class);
            assertThat(actual8).isInstanceOf(Pawn.class);
        });
    }

    @Test
    @DisplayName("게임 시작 시 룩은 A1 A8 H1 H8에 위치한다.")
    void generate_Rook() {
        SquaresGenerator squaresGenerator = new SquaresGenerator();
        Map<Position, Piece> squares = squaresGenerator.generate();

        Piece actual1 = squares.get(PositionGenerator.generate(File.A, Rank.ONE));
        Piece actual2 = squares.get(PositionGenerator.generate(File.A, Rank.EIGHT));
        Piece actual3 = squares.get(PositionGenerator.generate(File.A, Rank.ONE));
        Piece actual4 = squares.get(PositionGenerator.generate(File.A, Rank.EIGHT));

        assertAll(() -> {
            assertThat(actual1).isInstanceOf(Rook.class);
            assertThat(actual2).isInstanceOf(Rook.class);
            assertThat(actual3).isInstanceOf(Rook.class);
            assertThat(actual4).isInstanceOf(Rook.class);
        });
    }

    @Test
    @DisplayName("게임 시작 시 나이트는 B1 B8 G1 G8에 위치한다.")
    void generate_Knight() {
        SquaresGenerator squaresGenerator = new SquaresGenerator();
        Map<Position, Piece> squares = squaresGenerator.generate();

        Piece actual1 = squares.get(PositionGenerator.generate(File.B, Rank.ONE));
        Piece actual2 = squares.get(PositionGenerator.generate(File.B, Rank.EIGHT));
        Piece actual3 = squares.get(PositionGenerator.generate(File.G, Rank.ONE));
        Piece actual4 = squares.get(PositionGenerator.generate(File.G, Rank.EIGHT));

        assertAll(() -> {
            assertThat(actual1).isInstanceOf(Knight.class);
            assertThat(actual2).isInstanceOf(Knight.class);
            assertThat(actual3).isInstanceOf(Knight.class);
            assertThat(actual4).isInstanceOf(Knight.class);
        });
    }

    @Test
    @DisplayName("게임 시작 시 비숍은 C1 C8 F1 F8에 위치한다.")
    void generate_Bishop() {
        SquaresGenerator squaresGenerator = new SquaresGenerator();
        Map<Position, Piece> squares = squaresGenerator.generate();

        Piece actual1 = squares.get(PositionGenerator.generate(File.C, Rank.ONE));
        Piece actual2 = squares.get(PositionGenerator.generate(File.C, Rank.EIGHT));
        Piece actual3 = squares.get(PositionGenerator.generate(File.F, Rank.ONE));
        Piece actual4 = squares.get(PositionGenerator.generate(File.F, Rank.EIGHT));

        assertAll(() -> {
            assertThat(actual1).isInstanceOf(Bishop.class);
            assertThat(actual2).isInstanceOf(Bishop.class);
            assertThat(actual3).isInstanceOf(Bishop.class);
            assertThat(actual4).isInstanceOf(Bishop.class);
        });
    }

    @Test
    @DisplayName("게임 시작 시 퀸은 D1 D8에 위치한다.")
    void generate_Queen() {
        SquaresGenerator squaresGenerator = new SquaresGenerator();
        Map<Position, Piece> squares = squaresGenerator.generate();

        Piece actual1 = squares.get(PositionGenerator.generate(File.D, Rank.ONE));
        Piece actual2 = squares.get(PositionGenerator.generate(File.D, Rank.EIGHT));

        assertAll(() -> {
            assertThat(actual1).isInstanceOf(Queen.class);
            assertThat(actual2).isInstanceOf(Queen.class);
        });
    }

    @Test
    @DisplayName("게임 시작 시 킹은 E1 E8에 위치한다.")
    void generate_King() {
        SquaresGenerator squaresGenerator = new SquaresGenerator();
        Map<Position, Piece> squares = squaresGenerator.generate();

        Piece actual1 = squares.get(PositionGenerator.generate(File.E, Rank.ONE));
        Piece actual2 = squares.get(PositionGenerator.generate(File.E, Rank.EIGHT));

        assertAll(() -> {
            assertThat(actual1).isInstanceOf(King.class);
            assertThat(actual2).isInstanceOf(King.class);
        });
    }

    @ParameterizedTest
    @EnumSource(names = {"A", "B", "C", "D", "E", "F", "G", "H"})
    @DisplayName("게임 시작 시 랭크 3, 4, 5, 6은 비어있다.")
    void generate_None(File file) {
        SquaresGenerator squaresGenerator = new SquaresGenerator();
        Map<Position, Piece> squares = squaresGenerator.generate();

        Piece actual1 = squares.get(PositionGenerator.generate(file, Rank.THREE));
        Piece actual2 = squares.get(PositionGenerator.generate(file, Rank.FOUR));
        Piece actual3 = squares.get(PositionGenerator.generate(file, Rank.FIVE));
        Piece actual4 = squares.get(PositionGenerator.generate(file, Rank.SIX));

        assertAll(() -> {
            assertThat(actual1).isInstanceOf(None.class);
            assertThat(actual2).isInstanceOf(None.class);
            assertThat(actual3).isInstanceOf(None.class);
            assertThat(actual4).isInstanceOf(None.class);
        });
    }
}
