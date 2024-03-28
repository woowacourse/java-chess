package chess.domain.chessGame;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static chess.domain.chessGame.PieceFixture.blackKing;
import static chess.domain.chessGame.PieceFixture.emptyPiece;
import static chess.domain.chessGame.PieceFixture.whiteKing;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class KingSpaceTest {

    @Test
    @DisplayName("상하좌우로 한 칸 움직일 수 있다.")
    void should_move_up_down_left_right() {
        Space space1 = new Space(whiteKing, new Position(File.a, Rank.ONE));
        Space space2 = new Space(emptyPiece, new Position(File.a, Rank.TWO));

        space1.movePiece(space2, List.of(space1, space2));

        assertThat(space2.pieceCharacter()).isEqualTo("k");
    }

    @Test
    @DisplayName("대각선 방향으로 한 칸 움직일 수 있다")
    void should_move_diagonal() {
        Space space1 = new Space(whiteKing, new Position(File.a, Rank.ONE));
        Space space2 = new Space(emptyPiece, new Position(File.b, Rank.TWO));

        space1.movePiece(space2, List.of(space1, space2));

        assertThat(space2.pieceCharacter()).isEqualTo("k");
    }

    @Test
    @DisplayName("두 칸 이상 움직일 수 없다")
    void should_not_move_more_than_two_space() {
        Space space1 = new Space(whiteKing, new Position(File.a, Rank.ONE));
        Space space2 = new Space(emptyPiece, new Position(File.b, Rank.THREE));

        assertThatThrownBy(() -> space1.movePiece(space2, List.of(space1, space2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 규칙을 위반한 움직임입니다.");
    }

    @Test
    @DisplayName("제자리 이동 할 수 없다")
    void should_not_move_same_position() {
        Space space1 = new Space(whiteKing, new Position(File.a, Rank.ONE));
        Space space2 = new Space(emptyPiece, new Position(File.a, Rank.ONE));

        assertThatThrownBy(() -> space1.movePiece(space2, List.of(space1, space2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 규칙을 위반한 움직임입니다.");
    }

    @Test
    @DisplayName("이동할 위치에 상대 말이 있고 잡을 수 있으면 이동할 수 있다")
    void should_move_when_target_space_has_other_color_piece_and_catchable() {
        Space space1 = new Space(whiteKing, new Position(File.a, Rank.ONE));
        Space space2 = new Space(blackKing, new Position(File.b, Rank.TWO));

        space1.movePiece(space2, List.of(space1, space2));

        assertThat(space2.pieceCharacter()).isEqualTo("k");
    }
}
