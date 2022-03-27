package chess.domain.game;

import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class ActivePiecesTest {

    private ActivePieces activePieces;

    @BeforeEach
    void setUp() {
        List<Piece> pieces = new ArrayList<>(List.of(
                Pawn.of(WHITE, 0), new King(WHITE), Rook.ofLeft(WHITE)));
        activePieces = new ActivePieces(pieces);
    }

    @Test
    void 위치에_해당되는_체스말을_찾아준다() {
        Piece actual = activePieces.findByPosition(Position.of("a1"));
        Piece expected = Rook.ofLeft(WHITE);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 위치에_해당되는_체스말이_없으면_예외발생() {
        assertThatThrownBy(() -> activePieces.findByPosition(Position.of("d4")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 체스 말이 존재하지 않습니다.");
    }

    @Test
    void 해당_체스말을_제거() {
        activePieces.delete(Rook.ofLeft(WHITE));

        List<Piece> actual = activePieces.findAll();
        List<Piece> expected = List.of(Pawn.of(WHITE, 0), new King(WHITE));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 해당_위치에_체스말이_존재하면_참() {
        boolean actual = activePieces.isOccupied(Position.of("a1"));

        assertThat(actual).isTrue();
    }

    @Test
    void 해당_위치에_체스말이_없으면_거짓() {
        boolean actual = activePieces.isOccupied(Position.of("d5"));

        assertThat(actual).isFalse();
    }

    @Test
    void 킹의_개수를_반환() {
        int actual = activePieces.countKings();

        assertThat(actual).isEqualTo(1);
    }

    @Test
    void 점수_계산() {
        double actual = activePieces.calculateScore(WHITE);
        double expected = 5 + 1;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 같은_열에_존재하는_복수의_폰은_점수가_절반으로_계산() {
        List<Piece> pieces = new ArrayList<>(List.of(
                Pawn.of(WHITE, 0), Pawn.of(WHITE, 0), Pawn.of(WHITE, 0)));
        ActivePieces  activePieces = new ActivePieces(pieces);

        double actual = activePieces.calculateScore(WHITE);

        assertThat(actual).isEqualTo(1.5);
    }
}