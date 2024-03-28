package chess.domain.square.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.EmptySquaresMaker;
import chess.domain.position.File;
import chess.domain.position.PathFinder;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.square.Square;
import chess.domain.square.piece.unified.Knight;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class KnightTest {

    @DisplayName("나이트는 파일 두 칸, 랭크 한 칸 또는 파일 한 칸, 랭크 두 칸만큼 떨어진 칸으로 이동할 수 있다.")
    @ParameterizedTest
    @CsvSource({
            "THIRD, C, FIFTH, D",
            "THIRD, C, FIFTH, B",
            "THIRD, C, FIRST, D",
            "THIRD, C, FIRST, B",
            "THIRD, C, SECOND, A",
            "THIRD, C, FOURTH, A",
            "THIRD, C, FOURTH, E",
            "THIRD, C, SECOND, E",
    })
    void canMoveTwoStepAndOneStep(Rank startRank, File startFile, Rank targetRank, File targetFile) {
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece piece = Knight.from(Color.WHITE);
        board.put(new Position(startRank, startFile), piece);
        PathFinder pathFinder = new PathFinder(
                new Position(startRank, startFile), new Position(targetRank, targetFile));

        final boolean canMove = piece.canArrive(pathFinder, board);

        assertThat(canMove).isTrue();
    }

    @DisplayName("나이트는 파일 두 칸, 랭크 한 칸 또는 파일 한 칸, 랭크 두 칸만큼 떨어진 칸을 공격할 수 있다.")
    @ParameterizedTest
    @CsvSource({
            "THIRD, C, FIFTH, D",
            "THIRD, C, FIFTH, B",
            "THIRD, C, FIRST, D",
            "THIRD, C, FIRST, B",
            "THIRD, C, SECOND, A",
            "THIRD, C, FOURTH, A",
            "THIRD, C, FOURTH, E",
            "THIRD, C, SECOND, E",
    })
    void canAttackTwoStepAndOneStep(Rank startRank, File startFile, Rank targetRank, File targetFile) {
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece attackerPiece = Knight.from(Color.WHITE);
        Piece attackedPiece = Knight.from(Color.BLACK);
        board.put(new Position(startRank, startFile), attackerPiece);
        board.put(new Position(targetRank, targetFile), attackedPiece);
        PathFinder pathFinder = new PathFinder(
                new Position(startRank, startFile), new Position(targetRank, targetFile));

        final boolean canMove = attackerPiece.canArrive(pathFinder, board);

        assertThat(canMove).isTrue();
    }

    @DisplayName("나이트는 파일 두 칸, 랭크 한 칸 또는 파일 한 칸, 랭크 두 칸만큼 떨어진 칸이 아니면 움직일 수 없다.")
    @Test
    void canNotUnlessTwoStepAndOneStep() {
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece piece = Knight.from(Color.WHITE);
        board.put(new Position(Rank.FIRST, File.A), piece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.SECOND, File.A));

        final boolean canMove = piece.canArrive(pathFinder, board);

        assertThat(canMove).isFalse();
    }
}
