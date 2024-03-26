package chess.chessBoard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessBoard.OriginalChessSpaceGenerator;
import chess.domain.chessBoard.PieceGenerator;
import chess.domain.chessBoard.Space;
import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SpaceTest {

    @Test
    @DisplayName("피스가 없는 지 체크 할 수 있다.")
    void should_check_piece_non_exist() {
        Space space = new Space(new EmptyPiece(), new Position(File.a, Rank.ONE));

        assertThat(space.hasPiece()).isFalse();
    }

    @Test
    @DisplayName("피스가 있는 지 체크 할 수 있다.")
    void should_check_piece_exist() {
        Space space = new Space(new Pawn(Color.WHITE), new Position(File.a, Rank.ONE));

        assertThat(space.hasPiece()).isTrue();
    }

    @Test
    @DisplayName("피스를 움직이면 이동하려는 위치에 해당피스가 이동한다.")
    void should_move_piece() {
        Piece piece1 = new Pawn(Color.WHITE);
        Piece piece2 = new EmptyPiece();
        Space space1 = new Space(piece1, new Position(File.a, Rank.ONE));
        Space space2 = new Space(piece2, new Position(File.a, Rank.TWO));

        space1.movePiece(space2);

        assertThat(space2.getPiece()).isEqualTo(piece1);
    }

    @Test
    @DisplayName("피스를 움직이면 시작 위치의 피스는 비어진다.")
    void should_be_empty_starting_space_when_move() {
        Piece piece1 = new Pawn(Color.WHITE);
        Piece piece2 = new EmptyPiece();
        Space space1 = new Space(piece1, new Position(File.a, Rank.ONE));
        Space space2 = new Space(piece2, new Position(File.a, Rank.TWO));

        space1.movePiece(space2);

        assertThat(space1.hasPiece()).isFalse();
    }

    @Test
    @DisplayName("이동할 위치에 나의 말이 있으면 이동할 수 없다")
    void should_not_move_when_target_space_has_same_color_piece() {
        Piece piece1 = new Pawn(Color.WHITE);
        Piece piece2 = new Pawn(Color.WHITE);
        Space space1 = new Space(piece1, new Position(File.a, Rank.ONE));
        Space space2 = new Space(piece2, new Position(File.a, Rank.TWO));

        assertThatThrownBy(() -> space1.movePiece(space2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 규칙을 위반한 움직임입니다.");
    }

    @Test
    @DisplayName("이동할 위치에 상대 말이 있고 잡을 수 있으면 이동할 수 있다")
    void should_move_when_target_space_has_other_color_piece_and_catchable() {
        Piece piece1 = new Pawn(Color.WHITE);
        Piece piece2 = new Pawn(Color.BLACK);
        Space space1 = new Space(piece1, new Position(File.a, Rank.ONE));
        Space space2 = new Space(piece2, new Position(File.b, Rank.TWO));

        space1.movePiece(space2);

        assertThat(space2.getPiece()).isEqualTo(piece1);
    }

    @Test
    @DisplayName("폰은 반대색의 피스가 대각선한칸에 없다면, 대각선으로 움직일 수 없다")
    void should_not_move_diagonal_when_diagonal_space_has_not_opposite_color() {
        Piece piece1 = new Pawn(Color.WHITE);
        Piece piece2 = new EmptyPiece();
        Space space1 = new Space(piece1, new Position(File.a, Rank.ONE));
        Space space2 = new Space(piece2, new Position(File.b, Rank.TWO));

        assertThatThrownBy(() -> space1.movePiece(space2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 규칙을 위반한 움직임입니다.");
    }
}
