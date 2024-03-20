import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.Position;
import chess.domain.piece.character.Character;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {
    @DisplayName("보드에 있는 piece의 위치를 움직일 수 있다.")
    @Test
    void movePiece() {
        Board board = new Board(BoardFactory.generateStartBoard());
        board.move(Position.of(2, 1), Position.of(3, 1));

        assertThat(board.mapPositionToCharacter()).containsEntry(Position.of(3, 1), Character.WHITE_PAWN);
    }

    @DisplayName("시작 위치에 piece가 없으면 예외가 발생한다.")
    @Test
    void invalidOldPositionMovePiece() {
        Board board = new Board(BoardFactory.generateStartBoard());
        assertThatThrownBy(() -> board.move(Position.of(3, 1), Position.of(2, 2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 기물이 존재하지 않습니다.");
    }

    @DisplayName("이동 경로에 piece가 있으면 예외가 발생한다.")
    @Test
    void betweenPositionHasPiece() {
        Board board = new Board(BoardFactory.generateStartBoard());
        assertThatThrownBy(() -> board.move(Position.of(1, 3), Position.of(3, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동을 가로막는 기물이 존재합니다.");
    }

    @DisplayName("해당 위치에 아군 기물이 있으면 예외가 발생한다.")
    @Test
    void newPositionHasTeamPiece() {
        Board board = new Board(BoardFactory.generateStartBoard());
        assertThatThrownBy(() -> board.move(Position.of(1, 1), Position.of(1, 2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 아군 기물이 존재합니다.");
    }

    @DisplayName("Board에서 위치와 Character를 알 수 있다.")
    @Test
    void mapPositionToCharacter() {
        Board board = new Board(BoardFactory.generateStartBoard());

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

        assertThat(board.mapPositionToCharacter()).isEqualTo(expected);
    }
}
