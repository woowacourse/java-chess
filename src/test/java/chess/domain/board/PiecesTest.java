package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.state.Play;
import chess.domain.state.Ready;
import chess.domain.state.State;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("보유중인 말들 테스트")
class PiecesTest {

    private final Pieces pieces = new Pieces();
    private final State whiteState = new Play(Color.WHITE, new Pieces());
    private final State blackState = new Play(Color.BLACK, new Pieces());

    @BeforeEach
    void setUp() {
        pieces.init();
    }

    @Test
    @DisplayName("초기화 후 점수 테스트")
    void initScore() {
        assertThat(pieces.score(Color.BLACK)).isEqualTo(38.0);

        assertThat(pieces.score(Color.WHITE)).isEqualTo(38.0);
    }

    @Test
    @DisplayName("초기 장군말 위치 테스트")
    void initPosition() {
        final Map<Position, Piece> pieces = this.pieces.pieces();

        assertThat(pieces.get(Position.of("a1"))).isInstanceOf(Rook.class);
        assertThat(pieces.get(Position.of("a8"))).isInstanceOf(Rook.class);
        assertThat(pieces.get(Position.of("h1"))).isInstanceOf(Rook.class);
        assertThat(pieces.get(Position.of("h8"))).isInstanceOf(Rook.class);

        assertThat(pieces.get(Position.of("b1"))).isInstanceOf(Knight.class);
        assertThat(pieces.get(Position.of("b8"))).isInstanceOf(Knight.class);
        assertThat(pieces.get(Position.of("g1"))).isInstanceOf(Knight.class);
        assertThat(pieces.get(Position.of("g8"))).isInstanceOf(Knight.class);

        assertThat(pieces.get(Position.of("c1"))).isInstanceOf(Bishop.class);
        assertThat(pieces.get(Position.of("c8"))).isInstanceOf(Bishop.class);
        assertThat(pieces.get(Position.of("f1"))).isInstanceOf(Bishop.class);
        assertThat(pieces.get(Position.of("f8"))).isInstanceOf(Bishop.class);

        assertThat(pieces.get(Position.of("d1"))).isInstanceOf(Queen.class);
        assertThat(pieces.get(Position.of("d8"))).isInstanceOf(Queen.class);

        assertThat(pieces.get(Position.of("e1"))).isInstanceOf(King.class);
        assertThat(pieces.get(Position.of("e8"))).isInstanceOf(King.class);
    }

    @Test
    @DisplayName("초기 장군말 색상 체크")
    void initColor() {
        final Map<Position, Piece> pieces = this.pieces.pieces();
        for (int i = 0; i < 8; i++) {
            assertThat(pieces.get(Position.of(0, i)).isSameColor(Color.WHITE)).isTrue();
            assertThat(pieces.get(Position.of(7, i)).isSameColor(Color.BLACK)).isTrue();
        }
    }

    @Test
    @DisplayName("말 이동 테스트")
    void movePiece() {
        State state = new Ready();
        state = state.init();
        pieces.movePiece(Position.of("b2"), Position.of("b4"), state);
        assertThat(pieces.pieces().get(Position.of("b2"))).isNull();

        Map<Position, Piece> pieces = this.pieces.pieces();
        assertThat(pieces.get(Position.of("b4"))).isInstanceOf(Pawn.class);
    }

    private void killKingOfBlack() {
        pieces.movePiece(Position.of("b2"), Position.of("b4"), whiteState);
        pieces.movePiece(Position.of("c7"), Position.of("c5"), blackState);

        pieces.movePiece(Position.of("b4"), Position.of("c5"), whiteState);
        pieces.movePiece(Position.of("d7"), Position.of("d6"), blackState);

        pieces.movePiece(Position.of("c5"), Position.of("d6"), whiteState);
        pieces.movePiece(Position.of("b7"), Position.of("b6"), blackState);

        pieces.movePiece(Position.of("d6"), Position.of("d7"), whiteState);
        pieces.movePiece(Position.of("a7"), Position.of("a6"), blackState);
        pieces.movePiece(Position.of("d7"), Position.of("e8"), whiteState);
    }

    @Test
    @DisplayName("폰이 일직선상에 없을때 점수")
    void pawnsNotDuplicateScore() {
        killKingOfBlack();
        assertThat(pieces.score(Color.BLACK)).isEqualTo(36.0);
    }

    @Test
    @DisplayName("폰이 일직선상 있을때 점수")
    void pawnsDuplicateScore() {
        killKingOfBlack();
        assertThat(pieces.score(Color.WHITE)).isEqualTo(37.0);
    }

    @Test
    @DisplayName("왕이 죽었는지 테스트")
    void isKillKing() {
        assertThat(pieces.isKillKing()).isFalse();

        killKingOfBlack();

        assertThat(pieces.isKillKing()).isTrue();
    }

    @Test
    @DisplayName("자신의 기물이 아닌 다른 기물을 움직일때 테스트")
    void validateSourcePiece() {
        assertThatThrownBy(() -> pieces.movePiece(Position.of("a7"), Position.of("a5"), whiteState))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("공격하는 기물이 자신의 기물일때 테스트")
    void validateAttackPiece() {
        pieces.movePiece(Position.of("a2"), Position.of("a3"), whiteState);
        assertThatThrownBy(() -> pieces.movePiece(Position.of("b2"), Position.of("a3"), whiteState))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("빈공간을 이동하려 할때 테스트")
    void name() {
        assertThatThrownBy(() -> pieces.movablePositions(Position.of("b3")))
            .isInstanceOf(IllegalArgumentException.class);
    }
}