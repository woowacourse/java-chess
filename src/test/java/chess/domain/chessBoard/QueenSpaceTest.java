package chess.domain.chessBoard;

import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QueenSpaceTest {

    @Test
    @DisplayName("상하좌우로 칸 수 제한 없이 움직일 수 있다.")
    void should_move_up_down_left_right_unlimited() {
        Piece piece = new Queen(Color.WHITE);
        Space space1 = new Space(piece, new Position(File.a, Rank.ONE));
        Space space2 = new Space(new EmptyPiece(), new Position(File.a, Rank.THREE));

        space1.movePiece(space2, List.of(space1, space2));

        assertThat(space2.pieceCharacter()).isEqualTo("q");
    }

    @Test
    @DisplayName("대각선 방향으로 칸 수 제한 없이 움직일 수 있다")
    void should_move_diagonal_unlimited() {
        Piece piece = new Queen(Color.WHITE);

        Space space1 = new Space(piece, new Position(File.a, Rank.ONE));
        Space space2 = new Space(new EmptyPiece(), new Position(File.c, Rank.THREE));

        space1.movePiece(space2, List.of(space1, space2));

        assertThat(space2.pieceCharacter()).isEqualTo("q");
    }

    @Test
    @DisplayName("상하좌우와 대각선외에는 움직일 수 없다")
    void should_not_move_not_upDownLeftRight_not_diagonal() {
        Piece piece = new Queen(Color.WHITE);

        Space space1 = new Space(piece, new Position(File.a, Rank.ONE));
        Space space2 = new Space(new EmptyPiece(), new Position(File.b, Rank.THREE));

        assertThatThrownBy(() -> space1.movePiece(space2, List.of(space1, space2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 규칙을 위반한 움직임입니다.");
    }

    @Test
    @DisplayName("제자리 이동 할 수 없다")
    void should_not_move_same_position() {
        Piece piece = new Queen(Color.WHITE);

        Space space1 = new Space(piece, new Position(File.a, Rank.ONE));
        Space space2 = new Space(new EmptyPiece(), new Position(File.a, Rank.ONE));

        assertThatThrownBy(() -> space1.movePiece(space2, List.of(space1, space2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 규칙을 위반한 움직임입니다.");
    }

    @Test
    @DisplayName("이동할 위치에 상대 말이 있고 잡을 수 있으면 이동할 수 있다")
    void should_move_when_target_space_has_other_color_piece_and_catchable() {
        Piece piece1 = new Queen(Color.WHITE);
        Piece piece2 = new Queen(Color.BLACK);
        Space space1 = new Space(piece1, new Position(File.a, Rank.ONE));
        Space space2 = new Space(piece2, new Position(File.c, Rank.THREE));

        space1.movePiece(space2, List.of(space1, space2));

        assertThat(space2.pieceCharacter()).isEqualTo("q");
    }
}
