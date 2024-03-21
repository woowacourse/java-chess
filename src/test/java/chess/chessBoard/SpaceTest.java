package chess.chessBoard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.view.PieceSign;
import chess.domain.chessBoard.Space;
import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SpaceTest {

    @Test
    @DisplayName("피스를 움직이면 이동하려는 위치에 해당피스가 있다")
    void should_move_piece() {
        Piece piece1 = new Pawn(Color.WHITE);
        Piece piece2 = new EmptyPiece();
        Space space1 = new Space(piece1, new Position(File.a, Rank.ONE));
        Space space2 = new Space(piece2, new Position(File.a, Rank.TWO));

        space1.movePiece(space2, List.of(space1, space2));

        assertThat(space2.pieceCharacter()).isEqualTo(PieceSign.findSign(piece1));
    }

    @Test
    @DisplayName("피스를 움직이면 이동하려는 위치에 해당피스가 있다")
    void should_be_empty_starting_space_when_move() {
        Piece piece1 = new Pawn(Color.WHITE);
        Piece piece2 = new EmptyPiece();
        Space space1 = new Space(piece1, new Position(File.a, Rank.ONE));
        Space space2 = new Space(piece2, new Position(File.a, Rank.TWO));

        space1.movePiece(space2, List.of(space1, space2));

        assertThat(space1.doesNotHavePiece()).isTrue();
    }

    @Test
    @DisplayName("각 피스의 이동 규칙을 위반한 이동은 할 수 없다")
    void should_not_move_when_violate_move_rule() {
        Piece piece1 = new Pawn(Color.BLACK);
        Piece piece2 = new EmptyPiece();
        Space space1 = new Space(piece1, new Position(File.a, Rank.ONE));
        Space space2 = new Space(piece2, new Position(File.a, Rank.FOUR));

        assertThatThrownBy(() -> space1.movePiece(space2, List.of(space1, space2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 규칙을 위반한 움직임입니다.");
    }

    @Test
    @DisplayName("피스가 없는 지 체크 할 수 있다.")
    void should_check_piece_exist() {
        Space space = new Space(new EmptyPiece(), new Position(File.a, Rank.ONE));

        assertThat(space.doesNotHavePiece()).isTrue();
    }

    @Test
    @DisplayName("이동할 위치에 나의 말이 있으면 이동할 수 없다")
    void should_not_move_when_target_space_has_same_color_piece() {
        Piece piece1 = new Pawn(Color.WHITE);
        Piece piece2 = new Pawn(Color.WHITE);
        Space space1 = new Space(piece1, new Position(File.a, Rank.ONE));
        Space space2 = new Space(piece2, new Position(File.a, Rank.TWO));

        assertThatThrownBy(() -> space1.movePiece(space2, List.of(space1, space2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 피스가 이미 있습니다.");
    }

    @Test
    @DisplayName("이동할 위치에 상대 말이 있고 잡을 수 있으면 이동할 수 있다")
    void should_move_when_target_space_has_other_color_piece_and_catchable() {
        Piece piece1 = new Pawn(Color.WHITE);
        Piece piece2 = new Pawn(Color.BLACK);
        Space space1 = new Space(piece1, new Position(File.a, Rank.ONE));
        Space space2 = new Space(piece2, new Position(File.b, Rank.TWO));

        space1.movePiece(space2, List.of(space1, space2));

        assertThat(space2.pieceCharacter()).isEqualTo("p");
    }

    @Test
    @DisplayName("이동경로에 피스가 있으면 움직일 수 없다")
    void should_not_move_when_route_has_piece() {
        Piece piece1 = new Rook(Color.WHITE);
        Piece piece2 = new Pawn(Color.BLACK);
        Space space1 = new Space(piece1, new Position(File.a, Rank.ONE));
        Space space2 = new Space(piece2, new Position(File.a, Rank.TWO));
        Space space3 = new Space(new EmptyPiece(), new Position(File.a, Rank.THREE));

        assertThatThrownBy(() -> space1.movePiece(space3, List.of(space1, space2, space3)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("루트에 피스가 있습니다.");
    }
}
