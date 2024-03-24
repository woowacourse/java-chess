package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

public class PawnTest {

    @Test
    @DisplayName("첫 이동은 두 칸 움직일 수 있다.")
    void should_move_two_space_when_first_move() {
        Piece piece = new Pawn(Color.WHITE);
        Space space1 = new Space(piece, new Position(File.a, Rank.ONE));
        Space space2 = new Space(new EmptyPiece(), new Position(File.a, Rank.THREE));

        space1.movePiece(space2);

        assertThat(space2.getPiece()).isEqualTo(piece);
    }

    @Test
    @DisplayName("반대색이 대각선한칸에 있다면, 대각선으로 움직일 수 있다")
    void should_move_two_space_when_first_move2() {
        Piece piece1 = new Pawn(Color.WHITE);
        Piece piece2 = new Pawn(Color.BLACK);
        Space space1 = new Space(piece1, new Position(File.a, Rank.ONE));
        Space space2 = new Space(piece2, new Position(File.b, Rank.TWO));

        space1.movePiece(space2);

        assertThat(space2.getPiece()).isEqualTo(piece1);
    }

    @Test
    @DisplayName("두번째 이동부터는 두 칸 움직일 수 없다.")
    void should_not_move_two_space_when_second_move() {
        Piece piece = new Pawn(Color.WHITE);
        Space space1 = new Space(piece, new Position(File.a, Rank.ONE));
        Space space2 = new Space(new EmptyPiece(), new Position(File.a, Rank.THREE));
        Space space3 = new Space(new EmptyPiece(), new Position(File.a, Rank.FIVE));

        space1.movePiece(space2);

        assertThatThrownBy(() -> space2.movePiece(space3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 규칙을 위반한 움직임입니다.");
    }
}
