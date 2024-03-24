package domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import domain.piece.Color;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Rook;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = Board.create();
    }

    @Test
    @DisplayName("해당 위치의 기물을 가져온다.")
    void findPieceByPosition() {
        Position position = Position.generate(File.A, Rank.ONE);

        Piece piece = board.findPieceByPosition(position);

        assertThat(piece).isEqualTo(new Rook(Color.WHITE));
    }

    @Test
    @DisplayName("해당 위치로 기물을 옮긴다.")
    void placePiece() {
        Position source = Position.generate(File.A, Rank.TWO);
        Position target = Position.generate(File.A, Rank.THREE);
        Piece expected = new Pawn(Color.WHITE);

        board.movePiece(source, target);

        Piece actual = board.findPieceByPosition(target);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("해당 위치에 다른 기물이 있는 경우 참을 반환한다.")
    void isNotBlocked_True() {
        Position source = Position.generate(File.A, Rank.ONE);
        Position target = Position.generate(File.A, Rank.THREE);

        boolean actual = board.isBlocked(source, target);

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("해당 위치에 다른 기물이 없는 경우 거짓을 반환한다.")
    void isNotBlocked_False() {
        Position source = Position.generate(File.A, Rank.TWO);
        Position target = Position.generate(File.A, Rank.FOUR);

        boolean actual = board.isBlocked(source, target);

        assertThat(actual).isFalse();
    }
}
