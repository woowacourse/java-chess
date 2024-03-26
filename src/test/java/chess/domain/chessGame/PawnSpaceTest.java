package chess.domain.chessGame;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static chess.domain.chessGame.PieceFixture.blackPawn;
import static chess.domain.chessGame.PieceFixture.emptyPiece;
import static chess.domain.chessGame.PieceFixture.whitePawn;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PawnSpaceTest {

    @Test
    @DisplayName("첫 이동은 두 칸 움직일 수 있다.")
    void should_move_two_space_when_first_move() {
        Space space1 = new Space(whitePawn, new Position(File.a, Rank.TWO));
        Space space2 = new Space(emptyPiece, new Position(File.a, Rank.FOUR));

        space1.movePiece(space2, List.of(space1, space2));

        assertThat(space2.pieceCharacter()).isEqualTo("p");
    }

    @Test
    @DisplayName("두번째 이동부터는 두 칸 움직일 수 없다.")
    void should_not_move_two_space_when_second_move() {
        Space space1 = new Space(blackPawn, new Position(File.a, Rank.SEVEN));
        Space space2 = new Space(emptyPiece, new Position(File.a, Rank.FIVE));
        Space space3 = new Space(emptyPiece, new Position(File.a, Rank.THREE));

        space1.movePiece(space2, List.of(space1, space2, space3));

        assertThatThrownBy(() -> space2.movePiece(space3, List.of(space1, space2, space3)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 규칙을 위반한 움직임입니다.");
    }

    @Test
    @DisplayName("제자리 이동 할 수 없다")
    void should_not_move_same_position() {
        Space space1 = new Space(whitePawn, new Position(File.a, Rank.ONE));
        Space space2 = new Space(emptyPiece, new Position(File.a, Rank.ONE));

        assertThatThrownBy(() -> space1.movePiece(space2, List.of(space1, space2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 규칙을 위반한 움직임입니다.");
    }
}
