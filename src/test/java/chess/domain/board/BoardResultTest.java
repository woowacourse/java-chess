package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class BoardResultTest {

    @Test
    void 기물들의_점수를_계산할_수_있다() {
        // given
        final Board board = BoardFactory.create();
        final BoardResult boardResult = new BoardResult(board.getBoard());

        // when
        final double blackScore = boardResult.calculatePoints(Color.BLACK);

        // then
        assertThat(blackScore).isEqualTo(38.0);
    }

    @Test
    void 폰이_세로줄에_여러_개_있는_경우_0_5점으로_계산한다() {
        // given
        final Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of(File.A, Rank.ONE), Pawn.from(Color.BLACK));
        board.put(Position.of(File.A, Rank.TWO), Pawn.from(Color.BLACK));
        board.put(Position.of(File.A, Rank.THREE), Pawn.from(Color.BLACK));
        board.put(Position.of(File.A, Rank.FOUR), Pawn.from(Color.BLACK));
        board.put(Position.of(File.A, Rank.FIVE), Pawn.from(Color.WHITE));
        final BoardResult boardResult = new BoardResult(board);

        // when
        final double points = boardResult.calculatePoints(Color.BLACK);

        // then
        assertThat(points).isEqualTo(2);
    }

    @Test
    void 승자를_계산할_수_있다() {
        // given
        final Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of(File.A, Rank.ONE), King.from(Color.BLACK));
        board.put(Position.of(File.A, Rank.TWO), King.from(Color.WHITE));
        board.put(Position.of(File.E, Rank.THREE), Queen.from(Color.BLACK));
        board.put(Position.of(File.E, Rank.FOUR), Knight.from(Color.BLACK));
        board.put(Position.of(File.E, Rank.FIVE), Queen.from(Color.WHITE));
        board.put(Position.of(File.A, Rank.SIX), Bishop.from(Color.WHITE));
        final BoardResult boardResult = new BoardResult(board);

        // when
        final Color winner = boardResult.calculateWinner();

        // then
        // 백- 9(퀸) + 3(나이트) == 12 vs 11.5 == 2.5(비숍) + 9(퀸) -흑
        //                          백 승
        assertThat(winner).isEqualTo(Color.WHITE);
    }

    @Test
    void 킹이_잡히면_패배한다() {
        // given
        final Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of(File.A, Rank.ONE), King.from(Color.BLACK));
        board.put(Position.of(File.E, Rank.THREE), Queen.from(Color.BLACK));
        final BoardResult boardResult = new BoardResult(board);

        // when
        final Color winner = boardResult.calculateWinner();

        // then
        // 백- 킹 X vs 흑- 킹 O
        // 흑 승
        assertThat(winner).isEqualTo(Color.BLACK);
    }

    @Test
    void 남은_기물의_점수가_같다면_승자는_없다() {
        // given
        final Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of(File.A, Rank.ONE), King.from(Color.WHITE));
        board.put(Position.of(File.B, Rank.ONE), King.from(Color.BLACK));
        board.put(Position.of(File.C, Rank.ONE), Pawn.from(Color.WHITE));
        board.put(Position.of(File.D, Rank.ONE), Pawn.from(Color.BLACK));
        final BoardResult boardResult = new BoardResult(board);

        // when
        final Color winner = boardResult.calculateWinner();

        // then
        // 백- 킹1 폰1 vs 흑- 킹1 폰1
        // 무승부
        assertThat(winner).isEqualTo(Color.EMPTY);
    }
}