package domain.board;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.PieceType;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        BoardCreator boardCreator = new BoardCreator();
        board = boardCreator.create();
    }

    @Test
    @DisplayName("해당 위치의 기물을 가져온다.")
    void findPieceByPosition() {
        Position position = new Position(File.A, Rank.ONE);

        Piece piece = board.findPieceByPosition(position);

        assertThat(piece).isEqualTo(new Piece(PieceType.ROOK, Color.WHITE));
    }

    @Test
    @DisplayName("해당 위치에 기물을 둔다.")
    void placePiece() {
        Position position = new Position(File.A, Rank.ONE);
        Piece expected = new Piece(PieceType.ROOK, Color.WHITE);

        board.placePieceByPosition(expected, position);

        Piece actual = board.findPieceByPosition(position);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("해당 위치의 기물을 없앤다.")
    void displacePiece() {
        Position position = new Position(File.A, Rank.ONE);

        board.displacePieceByPosition(position);

        Piece actual = board.findPieceByPosition(position);
        Piece expected = new Piece(PieceType.NONE, Color.NONE);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("해당 위치에 다른 기물이 없는 경우 참을 반환한다.")
    void isNotBlocked_True() {
        Position source = new Position(File.A, Rank.TWO);
        Position target = new Position(File.A, Rank.FOUR);

        boolean actual = board.isNotBlocked(source, target);

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("해당 위치에 다른 기물이 있는 경우 거짓을 반환한다.")
    void isNotBlocked_False() {
        Position source = new Position(File.A, Rank.ONE);
        Position target = new Position(File.A, Rank.THREE);

        boolean actual = board.isNotBlocked(source, target);

        assertThat(actual).isFalse();
    }
}
