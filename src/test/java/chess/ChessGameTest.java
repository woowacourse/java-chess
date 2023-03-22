package chess;

import static chess.domain.piece.PieceType.BISHOP;
import static chess.domain.piece.PieceType.EMPTY;
import static chess.domain.piece.PieceType.KING;
import static chess.domain.piece.PieceType.KNIGHT;
import static chess.domain.piece.PieceType.PAWN;
import static chess.domain.piece.PieceType.QUEEN;
import static chess.domain.piece.PieceType.ROOK;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ChessGameTest {

    private static List<PieceType> generateResult(final Board board) {
        final List<Rank> ranks = Arrays.stream(Rank.values())
                .collect(Collectors.toList());
        Collections.reverse(ranks);
        final Map<Position, Piece> result = board.getBoard();
        return ranks.stream()
                .flatMap(file -> Arrays.stream(File.values()).map(rank -> Position.of(rank, file)))
                .map(result::get)
                .map(Piece::type)
                .collect(Collectors.toList());
    }

    @Test
    void 루이로페즈_모던_슈타이니츠_바리에이션_으로_게임을_진행한다() {
        // given
        final Board board = BoardFactory.create();

        // when
        board.move("e2", "e4");
        board.move("e7", "e5");
        board.move("g1", "f3");
        board.move("b8", "c6");
        board.move("f1", "b5");
        board.move("a7", "a6");
        board.move("b5", "a4");
        board.move("d7", "d6");

        // then
        final List<PieceType> result = generateResult(board);
        assertThat(result).containsExactly(
                ROOK, EMPTY, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK,
                EMPTY, PAWN, PAWN, EMPTY, EMPTY, PAWN, PAWN, PAWN,
                PAWN, EMPTY, KNIGHT, PAWN, EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY, PAWN, EMPTY, EMPTY, EMPTY,
                BISHOP, EMPTY, EMPTY, EMPTY, PAWN, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, KNIGHT, EMPTY, EMPTY,
                PAWN, PAWN, PAWN, PAWN, EMPTY, PAWN, PAWN, PAWN,
                ROOK, KNIGHT, BISHOP, QUEEN, KING, EMPTY, EMPTY, ROOK
        );
    }
}
