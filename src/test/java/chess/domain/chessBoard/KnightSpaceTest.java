package chess.domain.chessBoard;

import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class KnightSpaceTest {

    @Test
    @DisplayName("한칸 이동+한칸 대각선 이동을 할 수 있다(성공)")
    void should_move_one_straight_one_diagonal() {
        Piece piece = new Knight(Color.WHITE);

        Space space1 = new Space(piece, new Position(File.a, Rank.ONE));
        Space space2 = new Space(new EmptyPiece(), new Position(File.b, Rank.THREE));

        space1.movePiece(space2, List.of(space1, space2));

        assertThat(space2.pieceCharacter()).isEqualTo("n");
    }

    @Test
    @DisplayName("한칸 이동+한칸 대각선 이동을 할 수 있다(실패)")
    void should_not_move_not_one_straight_not_one_diagonal() {
        Piece piece = new Knight(Color.WHITE);

        Space space1 = new Space(piece, new Position(File.a, Rank.ONE));
        Space space2 = new Space(new EmptyPiece(), new Position(File.b, Rank.TWO));

        assertThatThrownBy(() -> space1.movePiece(space2, List.of(space1, space2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 규칙을 위반한 움직임입니다.");
    }

    @Test
    @DisplayName("피스를 뛰어넘어 이동할 수 있다")
    void should_move_over_piece() {
        Piece piece1 = new Knight(Color.WHITE);
        Piece piece2 = new Knight(Color.WHITE);
        Space space1 = new Space(piece1, new Position(File.a, Rank.ONE));
        Space space2 = new Space(piece2, new Position(File.b, Rank.TWO));
        Space space3 = new Space(new EmptyPiece(), new Position(File.b, Rank.THREE));

        space1.movePiece(space3, List.of(space1, space2, space3));

        assertThat(space3.pieceCharacter()).isEqualTo("n");
    }

    @Test
    @DisplayName("제자리 이동 할 수 없다")
    void should_not_move_same_position() {
        Piece piece = new Knight(Color.WHITE);

        Space space1 = new Space(piece, new Position(File.a, Rank.ONE));
        Space space2 = new Space(new EmptyPiece(), new Position(File.a, Rank.ONE));

        assertThatThrownBy(() -> space1.movePiece(space2, List.of(space1, space2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 규칙을 위반한 움직임입니다.");
    }

    @Test
    @DisplayName("이동할 위치에 상대 말이 있고 잡을 수 있으면 이동할 수 있다")
    void should_move_when_target_space_has_other_color_piece_and_catchable() {
        Piece piece1 = new Knight(Color.WHITE);
        Piece piece2 = new Knight(Color.BLACK);
        Space space1 = new Space(piece1, new Position(File.a, Rank.ONE));
        Space space2 = new Space(piece2, new Position(File.b, Rank.THREE));

        space1.movePiece(space2, List.of(space1, space2));

        assertThat(space2.pieceCharacter()).isEqualTo("n");
    }
}
