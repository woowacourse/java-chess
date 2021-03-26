package chess.domain.team;

import chess.domain.Position;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class PiecePositionTest {
    private PiecePosition whitePiecePosition;

    @BeforeEach
    void setUp() {
        whitePiecePosition = PiecePosition.initWhitePosition();
    }

    @Test
    @DisplayName("위치에 따른 기물을 요청하면, 해당 위치의 기물을 얻을 수 있다.")
    void choose_piece_test() {
        final Piece piece = whitePiecePosition.choosePiece(Position.of("d1"));
        assertThat(piece).isInstanceOf(Queen.class);
    }

    @Test
    @DisplayName("위치에 없는 기물을 요청하면, 예외를 발생시킨다..")
    void choose_piece_test_exception() {
        assertThatCode(() -> whitePiecePosition.choosePiece(Position.of("a8")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 기물이 없습니다.");
    }

    @Test
    @DisplayName("위치에 있는 기물을 이동시키면, 위치 정보가 업데이트 된다.")
    void move_piece_test() {
        whitePiecePosition.movePiece(Position.of("b2"), Position.of("b4"));
        assertThat(whitePiecePosition.choosePiece(Position.of("b4"))).isInstanceOf(Pawn.class);
    }

    @Test
    @DisplayName("위치에 기물이 있다면, 참을 반환한다.")
    void have_piece_test() {
        assertThat(whitePiecePosition.havePiece(Position.of("b2"))).isTrue();
        assertThat(whitePiecePosition.havePiece(Position.of("b3"))).isFalse();
    }

    @Test
    @DisplayName("위치에 따라 기물을 삭제할 수 있다.")
    void delete_piece_test() {
        whitePiecePosition.deletePiece(Position.of("b2"));
        assertThatCode(() -> whitePiecePosition.choosePiece(Position.of("b2")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 기물이 없습니다.");
    }
}
