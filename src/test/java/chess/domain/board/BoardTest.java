package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("보드 테스트")
public class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
        board.init();
    }

    @Test
    @DisplayName("끝났는지 확인")
    void finished() {
        assertThat(board.isFinish()).isFalse();

        killKingOfBlack();

        assertThat(board.isFinish()).isTrue();
    }

    private void killKingOfBlack() {
        board.movePiece("b2", "b4");
        board.movePiece("c7", "c5");

        board.movePiece("b4", "c5");
        board.movePiece("d7", "d6");

        board.movePiece("c5", "d6");
        board.movePiece("b7", "b6");

        board.movePiece("d6", "d7");
        board.movePiece("a7", "a6");

        board.movePiece("d7", "e8");
    }

    private void killKingOfWhite() {
        board.movePiece("c2", "c4");
        board.movePiece("b7", "b5");

        board.movePiece("d2", "d3");
        board.movePiece("b5", "c4");

        board.movePiece("a2", "a3");
        board.movePiece("c4", "d3");

        board.movePiece("a3", "a4");
        board.movePiece("d3", "d2");

        board.movePiece("a4", "a5");
        board.movePiece("d2", "e1");
    }

    @Test
    @DisplayName("폰이 일직선에 없을때 점수 확인")
    void pawnsNotDuplicateScore() {
        killKingOfBlack();
        assertThat(board.score(Color.BLACK)).isEqualTo(36.0);

        board = new Board();
        board.init();
        killKingOfWhite();
        assertThat(board.score(Color.BLACK)).isEqualTo(37.0);
    }

    @Test
    @DisplayName("폰이 일직선에 있을때 점수 확인")
    void pawnsDuplicateScore() {
        killKingOfBlack();
        assertThat(board.score(Color.WHITE)).isEqualTo(37.0);

        board = new Board();
        board.init();
        killKingOfWhite();
        assertThat(board.score(Color.WHITE)).isEqualTo(36.0);
    }

    @Test
    @DisplayName("게임이 끝난 뒤 말을 진행하려 할때 테스트")
    void finishAndMove() {
        killKingOfBlack();
        assertThatThrownBy(() -> board.movePiece("a1", "a2"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("빈 공간을 움직이려 할때 테스트")
    void moveBlank() {
        assertThatThrownBy(() -> board.movePiece("a3", "a4"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("상대방의 말을 움직이려 할때 테스트")
    void moveOpponentPiece() {
        assertThatThrownBy(() -> board.movePiece("c7", "c6"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("상대방의 말을 움직이려 할때 테스트")
    void myPieceAttack() {
        board.movePiece("a2", "a3");
        board.movePiece("a7", "a6");
        assertThatThrownBy(() -> board.movePiece("b2", "a3"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Black 킹이 죽은 후 승자 확인")
    void winnerIsWhite() {
        killKingOfBlack();

        assertThat(board.winner()).isEqualTo(Color.WHITE);
    }

    @Test
    @DisplayName("White 킹이 죽은 후 승자 확인")
    void winnerIsBlack() {
        killKingOfWhite();

        assertThat(board.winner()).isEqualTo(Color.BLACK);
    }

    @Test
    @DisplayName("게임이 끝나지 않을때 승자 확인")
    void name() {
        assertThat(board.winner()).isNull();
    }

    @Test
    @DisplayName("종속성 확인하기")
    void pieces() {
        Map<Position, Piece> pieces = board.pieces();

        assertThat(pieces).isEqualTo(board.pieces());

        board.movePiece("a2", "a4");

        assertThat(pieces).isNotEqualTo(board.pieces());
    }

    @Test
    @DisplayName("이동가능한 경로 테스트")
    void movablePosition() {
        List<Position> positions = board.movablePositions("b1");
        List<Position> targetPositions = Arrays.asList(Position.of("a3"), Position.of("c3"));
        assertThat(positions).hasSize(2);
        for (Position position : targetPositions) {
            assertThat(positions).contains(position);
        }
    }

    @Test
    @DisplayName("플레이어 이름이 정상적으로 입력되는지 테스트")
    void Players() {
        Board board = new Board("white", "black");
        Players players = board.players();
        assertThat(players.getBlackPlayer()).isEqualTo("black");
        assertThat(players.getWhitePlayer()).isEqualTo("white");
    }

    @Test
    @DisplayName("턴을 확인하는 테스트")
    void turn() {
        assertThat(board.turn()).isEqualTo(Color.WHITE);
        board.movePiece("b2", "b3");
        assertThat(board.turn()).isEqualTo(Color.BLACK);
    }
}
