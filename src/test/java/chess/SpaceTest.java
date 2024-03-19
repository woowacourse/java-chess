package chess;

import static org.assertj.core.api.Assertions.assertThat;

import chess.piece.Color;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.position.File;
import chess.position.Position;
import chess.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SpaceTest {

    @Test
    @DisplayName("피스를 움직일 수 있다")
    void should_move_piece() {
        Piece piece1 = new Pawn(Color.BLACK);
        Piece piece2 = null;
        Space space1 = new Space(piece1, new Position(File.a, Rank.ONE));
        Space space2 = new Space(piece2, new Position(File.a, Rank.ONE));

        space1.movePiece(space2);

        assertThat(space2.pieceCharacter()).isEqualTo(PieceSign.findSign(piece1));
        assertThat(space1.isBlankSpace()).isTrue();
    }

    @Test
    @DisplayName("피스가 없는 지 체크 할 수 있다.")
    void should_check_piece_exist() {
        Space space = new Space(null, new Position(File.a, Rank.ONE));

        assertThat(space.isBlankSpace()).isTrue();
    }
}
