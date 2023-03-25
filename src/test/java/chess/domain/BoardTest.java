package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.*;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BoardTest {

    private final Board board = BoardFactory.generateBoard();

    @Test
    void 흰색_폰을_찾을_수_있다() {
        assertThat(board.getPiece(Position.of(File.A, Rank.SEVEN))).isInstanceOf(Pawn.class);
    }

    @Test
    void 검정색_폰을_찾을_수_있다() {
        assertThat(board.getPiece(Position.of(File.A, Rank.TWO))).isInstanceOf(Pawn.class);
    }

    @Test
    void 흰색_룩을_찾을_수_있다() {
        assertThat(board.getPiece(Position.of(File.A, Rank.EIGHT))).isInstanceOf(Rook.class);
    }

    @Test
    void 검정색_룩을_찾을_수_있다() {
        assertThat(board.getPiece(Position.of(File.A, Rank.ONE))).isInstanceOf(Rook.class);
    }

    @Test
    void 흰색_나이트를_찾을_수_있다() {
        assertThat(board.getPiece(Position.of(File.B, Rank.EIGHT))).isInstanceOf(Knight.class);
    }

    @Test
    void 검정색_나이트를_찾을_수_있다() {
        assertThat(board.getPiece(Position.of(File.B, Rank.ONE))).isInstanceOf(Knight.class);
    }

    @Test
    void 흰색_비숍을_찾을_수_있다() {
        assertThat(board.getPiece(Position.of(File.C, Rank.EIGHT))).isInstanceOf(Bishop.class);
    }

    @Test
    void 검정색_비숍을_찾을_수_있다() {
        assertThat(board.getPiece(Position.of(File.C, Rank.ONE))).isInstanceOf(Bishop.class);
    }

    @Test
    void 흰색_퀸을_찾을_수_있다() {
        assertThat(board.getPiece(Position.of(File.D, Rank.EIGHT))).isInstanceOf(Queen.class);
    }

    @Test
    void 검정색_퀸을_찾을_수_있다() {
        assertThat(board.getPiece(Position.of(File.D, Rank.ONE))).isInstanceOf(Queen.class);
    }

    @Test
    void 흰색_킹을_찾을_수_있다() {
        assertThat(board.getPiece(Position.of(File.E, Rank.EIGHT))).isInstanceOf(King.class);
    }

    @Test
    void 검정색_킹을_찾을_수_있다() {
        assertThat(board.getPiece(Position.of(File.E, Rank.ONE))).isInstanceOf(King.class);
    }

    @Test
    void 기물을_목표_위치로_이동할_수_있다() {
        final File sourceFile = File.getFile("a");
        final Rank sourceRank = Rank.getRank(2);
        final File targetFile = File.getFile("a");
        final Rank targetRank = Rank.getRank(3);

        board.move(Position.of(sourceFile, sourceRank),
                Position.of(targetFile, targetRank));

        assertThat(board.getBoard().get(Position.of(sourceFile, sourceRank)))
                .isEqualTo(new Empty(Type.EMPTY, Side.NEUTRALITY));
        assertThat(board.getBoard().get(Position.of(targetFile, targetRank)))
                .isEqualTo(new Pawn(Type.PAWN, Side.WHITE));
    }

    @Test
    void 해당_위치의_진영을_찾을_수_있다() {
        Assertions.assertThat(board.findSideByPosition(Position.of(File.getFile("a"), Rank.getRank(2))))
                .isEqualTo(Side.WHITE);
    }

    @Test
    void 움직일_수_있는_위치들을_찾을_수_있다() {
        final Position startPosition = Position.of(File.getFile(2), Rank.getRank(2));
        final Position upPosition = Position.of(File.getFile(2), Rank.getRank(3));
        final Position doubleUpPosition = Position.of(File.getFile(2), Rank.getRank(4));

        Assertions.assertThat(board.findMovablePositions(startPosition))
                .isEqualTo(List.of(upPosition, doubleUpPosition));
    }

    @Test
    void 점수를_계산할_수_있다() {
        Assertions.assertThat(board.calculateScore(Side.WHITE)).isEqualTo(38);
    }

    @Test
    void 승리자를_계산할_수_있다() {
        Assertions.assertThat(board.calculateWinner()).isEqualTo(Side.NEUTRALITY);
    }
}