package domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import domain.piece.Color;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Rook;
import domain.position.File;
import domain.position.Position;
import domain.position.PositionGenerator;
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
        Position position = PositionGenerator.generate(File.A, Rank.ONE);

        Piece piece = board.findPieceByPosition(position);

        assertThat(piece).isEqualTo(new Rook(Color.WHITE));
    }

    @Test
    @DisplayName("해당 위치로 기물을 옮긴다.")
    void movePiece() {
        Position source = PositionGenerator.generate(File.A, Rank.TWO);
        Position target = PositionGenerator.generate(File.A, Rank.THREE);
        Piece expected = new Pawn(Color.WHITE);

        board.movePiece(source, target);

        Piece actual = board.findPieceByPosition(target);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("경로에 다른 기물이 있는 경우 참을 반환한다. - 직선 경로")
    void isBlocked_Straight_True() {
        Position source = PositionGenerator.generate(File.A, Rank.ONE);
        Position target = PositionGenerator.generate(File.A, Rank.THREE);

        boolean actual = board.isBlocked(source, target);

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("경로에 다른 기물이 없는 경우 거짓을 반환한다. - 직선 경로")
    void isBlocked_Straight_False() {
        Position source = PositionGenerator.generate(File.A, Rank.TWO);
        Position target = PositionGenerator.generate(File.A, Rank.FOUR);

        boolean actual = board.isBlocked(source, target);

        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("경로에 다른 기물이 있는 경우 참을 반환한다. - 대각선 경로")
    void isBlocked_Diagonal_True() {
        Position source = PositionGenerator.generate(File.C, Rank.ONE);
        Position target = PositionGenerator.generate(File.H, Rank.SIX);

        boolean actual = board.isBlocked(source, target);

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("경로에 다른 기물이 없는 경우 거짓을 반환한다. - 대각선 경로")
    void isBlocked_Diagonal_False() {
        board.movePiece(PositionGenerator.generate(File.D, Rank.TWO), PositionGenerator.generate(File.D, Rank.THREE));
        Position source = PositionGenerator.generate(File.C, Rank.ONE);
        Position target = PositionGenerator.generate(File.H, Rank.SIX);

        boolean actual = board.isBlocked(source, target);

        assertThat(actual).isFalse();
    }
}
