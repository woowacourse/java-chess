package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.game.CatchPieces;
import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CatchPiecesTest {

    private CatchPieces catchPieces;

    @BeforeEach
    void setUp() {
        catchPieces = new CatchPieces();
    }

    @Test
    @DisplayName("흑 팀의 기물을 추가하는 경우")
    void addPiece_WhenBlackTeam() {
        catchPieces.addPiece(new Pawn(Team.BLACK));

        assertThat(catchPieces.getBlackPieces().size()).isEqualTo(1);
        assertThat(catchPieces.getWhitePieces().size()).isEqualTo(0);
    }

    @Test
    @DisplayName("백 팀의 기물을 추가하는 경우")
    void addPiece_WhenWhiteTeam() {
        catchPieces.addPiece(new Pawn(Team.WHITE));

        assertThat(catchPieces.getBlackPieces().size()).isEqualTo(0);
        assertThat(catchPieces.getWhitePieces().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Black 를 추가하는 경우")
    void addPiece_WhenNONE() {
        catchPieces.addPiece(new Blank());

        assertThat(catchPieces.getBlackPieces().size()).isEqualTo(0);
        assertThat(catchPieces.getWhitePieces().size()).isEqualTo(0);
    }

    @Test
    @DisplayName("왕이 잡혔는지 여부를 확인")
    void isKingCatch() {
        catchPieces.addPiece(new King(Team.BLACK));

        assertThat(catchPieces.isKingCatch()).isTrue();
    }
}