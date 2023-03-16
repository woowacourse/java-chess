package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Position;
import chess.domain.Team;
import chess.domain.pieces.EmptyPiece;
import chess.domain.pieces.Rook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SquareTest {

    private Square square;

    @BeforeEach
    void setUp() {
        var position = new Position(1, 1);
        var piece = new EmptyPiece();

        square = new Square(position, piece);
    }

    @Test
    @DisplayName("포지션이 같으면 같은 Square이다.")
    void equalsTest() {
        var newSquare = new Square(new Position(1, 1), new EmptyPiece());

        assertThat(square.equals(newSquare)).isTrue();
    }

    @Test
    @DisplayName("새로운 Piece를 받아 교체한다.")
    void replacePieceTest() {
        var rook = new Rook(Team.BLACK);

        var replacedSquare = square.replacePiece(rook);

        assertThat(replacedSquare.getPiece()).isInstanceOf(Rook.class);
    }

    @Test
    @DisplayName("piece가 존재하지 않으면 true를 반환한다.")
    void isEmptyPiece_true() {
        assertThat(square.isEmptyPiece()).isTrue();
    }

    @Test
    @DisplayName("piece가 존재하면 false를 반환한다.")
    void isEmptyPiece_false() {
        var replacedSquare = square.replacePiece(new Rook(Team.BLACK));
        assertThat(replacedSquare.isEmptyPiece()).isFalse();
    }
}
