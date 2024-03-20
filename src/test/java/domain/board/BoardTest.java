package domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.Type;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

    @Test
    @DisplayName("해당 위치의 기물을 가져온다.")
    void findPieceByPosition() {
        Position position = new Position(File.A, Rank.ONE);
        Board board = Board.create();

        Piece piece = board.findPieceByPosition(position);

        assertThat(piece).isEqualTo(new Piece(Type.ROOK, Color.WHITE));
    }
}
