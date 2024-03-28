package chess.domain.chessGame;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static chess.domain.chessGame.PieceFixture.blackRook;
import static chess.domain.chessGame.PieceFixture.emptyPiece;
import static chess.domain.chessGame.PieceFixture.whiteRook;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RookSpaceTest {

    @Test
    @DisplayName("상하좌우로 칸 수 제한 없이 움직일 수 있다.")
    void should_move_up_down_left_right_unlimited() {
        Space space1 = new Space(whiteRook, new Position(File.a, Rank.ONE));
        Space space2 = new Space(emptyPiece, new Position(File.a, Rank.THREE));

        space1.movePiece(space2, List.of(space1, space2));

        assertThat(space2.pieceCharacter()).isEqualTo("r");
    }

    @Test
    @DisplayName("제자리 이동 할 수 없다")
    void should_not_move_same_position() {
        Space space1 = new Space(whiteRook, new Position(File.a, Rank.ONE));
        Space space2 = new Space(emptyPiece, new Position(File.a, Rank.ONE));

        assertThatThrownBy(() -> space1.movePiece(space2, List.of(space1, space2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 규칙을 위반한 움직임입니다.");
    }

    @Test
    @DisplayName("이동할 위치에 상대 말이 있고 잡을 수 있으면 이동할 수 있다")
    void should_move_when_target_space_has_other_color_piece_and_catchable() {
        Space space1 = new Space(whiteRook, new Position(File.a, Rank.ONE));
        Space space2 = new Space(blackRook, new Position(File.a, Rank.THREE));

        space1.movePiece(space2, List.of(space1, space2));

        assertThat(space2.pieceCharacter()).isEqualTo("r");
    }
}
