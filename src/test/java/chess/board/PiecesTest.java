package chess.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PiecesTest {

    private final Pieces pieces = new Pieces();

    @BeforeEach
    void setUp() {
        pieces.init();
    }

    @Test
    @DisplayName("초기화 후 점수 테스트")
    void initScore() {
        assertThat(pieces.score(Color.BLACK)).isEqualTo(38);

        assertThat(pieces.score(Color.WHITE)).isEqualTo(38);
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
        State state = new State();
        pieces.movePiece(Position.of("b2"), Position.of("b4"), state);
        assertThat(pieces.positionIsBlank(Position.of("b2"))).isTrue();

        Map<Position, Piece> pieces = this.pieces.pieces();
        assertThat(pieces.get(Position.of("b4"))).isInstanceOf(Pawn.class);
    }

    private void killKingOfBlack() {
        State state = new State();
        pieces.movePiece(Position.of("b2"), Position.of("b4"), state);
        pieces.movePiece(Position.of("c7"), Position.of("c5"), state);

        pieces.movePiece(Position.of("b4"), Position.of("c5"), state);
        pieces.movePiece(Position.of("d7"), Position.of("d6"), state);

        pieces.movePiece(Position.of("c5"), Position.of("d6"), state);
        pieces.movePiece(Position.of("b7"), Position.of("b6"), state);

        pieces.movePiece(Position.of("d6"), Position.of("d7"), state);
        pieces.movePiece(Position.of("a7"), Position.of("a6"), state);

        pieces.movePiece(Position.of("d7"), Position.of("e8"), state);
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
}