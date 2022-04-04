package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.game.DeadPieces;
import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeadPiecesTest {

    private DeadPieces deadPieces;

    @BeforeEach
    void setUp() {
        deadPieces = new DeadPieces();
    }

    @Test
    @DisplayName("흑 팀의 기물을 추가하는 경우")
    void addPiece_WhenBlackTeam() {
        deadPieces.add(new Pawn(Team.BLACK));

        assertThat(deadPieces.getBlackPieces().size()).isEqualTo(1);
        assertThat(deadPieces.getWhitePieces().size()).isEqualTo(0);
    }

    @Test
    @DisplayName("백 팀의 기물을 추가하는 경우")
    void addPiece_WhenWhiteTeam() {
        deadPieces.add(new Pawn(Team.WHITE));

        assertThat(deadPieces.getBlackPieces().size()).isEqualTo(0);
        assertThat(deadPieces.getWhitePieces().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Black 를 추가하는 경우")
    void addPiece_WhenNONE() {
        deadPieces.add(new Blank());

        assertThat(deadPieces.getBlackPieces().size()).isEqualTo(0);
        assertThat(deadPieces.getWhitePieces().size()).isEqualTo(0);
    }

    @Test
    @DisplayName("왕이 잡혔는지 여부를 확인")
    void isKingCatch() {
        deadPieces.add(new King(Team.BLACK));

        assertThat(deadPieces.isKingDead()).isTrue();
    }
}