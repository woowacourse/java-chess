import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Team;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {
    @DisplayName("Board에서 위치와 Kind를 알 수 있다.")
    @Test
    void mapPositionToKind() {
        Board board = new Board();

        Map<Position, Character> expected = Map.ofEntries(
                Map.entry(Position.of(1, 1), Character.WHITE_ROOK),
                Map.entry(Position.of(1, 2), Character.WHITE_KNIGHT),
                Map.entry(Position.of(1, 3), Character.WHITE_BISHOP),
                Map.entry(Position.of(1, 4), Character.WHITE_QUEEN),
                Map.entry(Position.of(1, 5), Character.WHITE_KING),
                Map.entry(Position.of(1, 6), Character.WHITE_BISHOP),
                Map.entry(Position.of(1, 7), Character.WHITE_KNIGHT),
                Map.entry(Position.of(1, 8), Character.WHITE_ROOK),

                Map.entry(Position.of(2, 1), Character.WHITE_PAWN),
                Map.entry(Position.of(2, 2), Character.WHITE_PAWN),
                Map.entry(Position.of(2, 3), Character.WHITE_PAWN),
                Map.entry(Position.of(2, 4), Character.WHITE_PAWN),
                Map.entry(Position.of(2, 5), Character.WHITE_PAWN),
                Map.entry(Position.of(2, 6), Character.WHITE_PAWN),
                Map.entry(Position.of(2, 7), Character.WHITE_PAWN),
                Map.entry(Position.of(2, 8), Character.WHITE_PAWN),

                Map.entry(Position.of(7, 1), Character.BLACK_PAWN),
                Map.entry(Position.of(7, 2), Character.BLACK_PAWN),
                Map.entry(Position.of(7, 3), Character.BLACK_PAWN),
                Map.entry(Position.of(7, 4), Character.BLACK_PAWN),
                Map.entry(Position.of(7, 5), Character.BLACK_PAWN),
                Map.entry(Position.of(7, 6), Character.BLACK_PAWN),
                Map.entry(Position.of(7, 7), Character.BLACK_PAWN),
                Map.entry(Position.of(7, 8), Character.BLACK_PAWN),

                Map.entry(Position.of(8, 1), Character.BLACK_ROOK),
                Map.entry(Position.of(8, 2), Character.BLACK_KNIGHT),
                Map.entry(Position.of(8, 3), Character.BLACK_BISHOP),
                Map.entry(Position.of(8, 4), Character.BLACK_QUEEN),
                Map.entry(Position.of(8, 5), Character.BLACK_KING),
                Map.entry(Position.of(8, 6), Character.BLACK_BISHOP),
                Map.entry(Position.of(8, 7), Character.BLACK_KNIGHT),
                Map.entry(Position.of(8, 8), Character.BLACK_ROOK)
        );

        assertThat(board.mapPositionToKind()).isEqualTo(expected);
    }

    @DisplayName("Board를 생성하면 Piece들의 위치가 초기 위치여아한다.")
    @Test
    void startPieceLocation() {
        Board board = new Board();

        List<Piece> pieces = board.pieces();

        assertThat(pieces).containsExactlyInAnyOrder(
                new Rook(Position.of(1, 1), Team.WHITE),
                new Rook(Position.of(1, 8), Team.WHITE),
                new Knight(Position.of(1, 2), Team.WHITE),
                new Knight(Position.of(1, 7), Team.WHITE),
                new Bishop(Position.of(1, 3), Team.WHITE),
                new Bishop(Position.of(1, 6), Team.WHITE),
                new Queen(Position.of(1, 4), Team.WHITE),
                new King(Position.of(1, 5), Team.WHITE),

                new Pawn(Position.of(2, 1), Team.WHITE),
                new Pawn(Position.of(2, 2), Team.WHITE),
                new Pawn(Position.of(2, 3), Team.WHITE),
                new Pawn(Position.of(2, 4), Team.WHITE),
                new Pawn(Position.of(2, 5), Team.WHITE),
                new Pawn(Position.of(2, 6), Team.WHITE),
                new Pawn(Position.of(2, 7), Team.WHITE),
                new Pawn(Position.of(2, 8), Team.WHITE),

                new Pawn(Position.of(7, 1), Team.BLACK),
                new Pawn(Position.of(7, 2), Team.BLACK),
                new Pawn(Position.of(7, 3), Team.BLACK),
                new Pawn(Position.of(7, 4), Team.BLACK),
                new Pawn(Position.of(7, 5), Team.BLACK),
                new Pawn(Position.of(7, 6), Team.BLACK),
                new Pawn(Position.of(7, 7), Team.BLACK),
                new Pawn(Position.of(7, 8), Team.BLACK),

                new Rook(Position.of(8, 1), Team.BLACK),
                new Rook(Position.of(8, 8), Team.BLACK),
                new Knight(Position.of(8, 2), Team.BLACK),
                new Knight(Position.of(8, 7), Team.BLACK),
                new Bishop(Position.of(8, 3), Team.BLACK),
                new Bishop(Position.of(8, 6), Team.BLACK),
                new Queen(Position.of(8, 4), Team.BLACK),
                new King(Position.of(8, 5), Team.BLACK)
        );
    }
}
