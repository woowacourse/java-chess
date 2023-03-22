package chess.domain.board;

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
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class BoardFactoryTest {

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
    void 체스판을_생성한다() {
        // given
        final Board board = BoardFactory.create();

        // expect
        final List<PieceType> result = generateResult(board);
        assertThat(result).containsExactly(
                ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK,
                PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN,
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN,
                ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK
        );
    }

}