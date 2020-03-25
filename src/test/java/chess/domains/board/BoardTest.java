package chess.domains.board;

import chess.domains.piece.PieceColor;
import chess.domains.piece.Rook;
import chess.domains.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    @DisplayName("입력한 위치의 말을 찾는 기능 확인")
    @Test
    void findPiece_1() {
        Board board = new Board();
        PlayingPiece piece = board.findPiece("a1");

        PlayingPiece playingPiece = new PlayingPiece(Position.ofPositionName("a1"), new Rook(PieceColor.WHITE));
        assertThat(piece).isEqualTo(playingPiece);
    }
}