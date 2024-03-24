package chess.domain.square.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.EmptySquaresMaker;
import chess.domain.position.File;
import chess.domain.position.Path;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.square.Square;
import chess.domain.square.piece.unified.Knight;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KnightTest {
    @DisplayName("나이트는 파일 두칸, 랭크 한칸만큼 떨어진 칸으로 갈 수 있다.")
    @Test
    void canTwoFileOneRankMoveTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece piece = Knight.from(Color.WHITE);
        board.put(new Position(Rank.FIRST, File.A), piece);
        Path path = new Path(new Position(Rank.FIRST, File.A), new Position(Rank.SECOND, File.C));

        // when & then
        assertThat(piece.canArrive(path, board))
                .isTrue();
    }

    @DisplayName("나이트는 파일 한칸, 랭크 두칸만큼 떨어진 칸으로 갈 수 있다.")
    @Test
    void canOneFileTwoRankMoveTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece piece = Knight.from(Color.WHITE);
        board.put(new Position(Rank.FIRST, File.A), piece);
        Path path = new Path(new Position(Rank.FIRST, File.A), new Position(Rank.THIRD, File.B));

        // when & then
        assertThat(piece.canArrive(path, board))
                .isTrue();
    }

    @DisplayName("나이트는 정해진 규칙이 아닌 칸으로 움직일 수 없다.")
    @Test
    void canNotMoveTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece piece = Knight.from(Color.WHITE);
        board.put(new Position(Rank.FIRST, File.A), piece);
        Path path = new Path(new Position(Rank.FIRST, File.A), new Position(Rank.SECOND, File.A));

        // when & then
        assertThat(piece.canArrive(path, board))
                .isFalse();
    }

    @DisplayName("나이트는 파일 두칸, 랭크 한칸만큼 떨어진 칸을 공격할 수 있다.")
    @Test
    void canTwoFileOneRankAttackTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece attackerPiece = Knight.from(Color.WHITE);
        Piece attackedPiece = Knight.from(Color.BLACK);
        board.put(new Position(Rank.FIRST, File.A), attackerPiece);
        board.put(new Position(Rank.SECOND, File.C), attackedPiece);

        Path path = new Path(new Position(Rank.FIRST, File.A), new Position(Rank.SECOND, File.C));

        // when & then
        assertThat(attackerPiece.canArrive(path, board))
                .isTrue();
    }

    @DisplayName("나이트는 파일 한칸, 랭크 두칸만큼 떨어진 칸을 공격할 수 있다.")
    @Test
    void canOneFileTwoRankAttackTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece attackerPiece = Knight.from(Color.WHITE);
        Piece attackedPiece = Knight.from(Color.BLACK);
        board.put(new Position(Rank.FIRST, File.A), attackerPiece);
        board.put(new Position(Rank.THIRD, File.B), attackedPiece);

        Path path = new Path(new Position(Rank.FIRST, File.A), new Position(Rank.THIRD, File.B));

        // when & then
        assertThat(attackerPiece.canArrive(path, board))
                .isTrue();
    }

    @DisplayName("나이트는 정해진 규칙이 아닌 칸으로 공격할 수 없다.")
    @Test
    void canNotAttackTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece attackerPiece = Knight.from(Color.WHITE);
        Piece attackedPiece = Knight.from(Color.BLACK);
        board.put(new Position(Rank.FIRST, File.A), attackerPiece);
        board.put(new Position(Rank.SECOND, File.A), attackedPiece);

        Path path = new Path(new Position(Rank.FIRST, File.A), new Position(Rank.SECOND, File.A));

        // when & then
        assertThat(attackerPiece.canArrive(path, board))
                .isFalse();
    }
}
