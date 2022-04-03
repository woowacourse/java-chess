package chess.domain.game;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

class WinnerTest {

    @Test
    @DisplayName("블랙 킹이 없을 경우 화이트가 우승한다.")
    void isWhiteWin() {
        Map<Position, Piece> initialPieces = new HashMap<>();
        initialPieces.put(Position.valueOf(File.A, Rank.ONE), new King(Color.WHITE));

        Winner winner = new Winner(initialPieces);

        assertThat(winner.getColor()).isEqualTo(Color.WHITE);
    }

    @Test
    @DisplayName("화이트 킹이 없을 경우 블랙이 우승한다.")
    void isBlackWin() {
        Map<Position, Piece> initialPieces = new HashMap<>();
        initialPieces.put(Position.valueOf(File.A, Rank.ONE), new King(Color.BLACK));

        Winner winner = new Winner(initialPieces);

        assertThat(winner.getColor()).isEqualTo(Color.BLACK);
    }

    @Test
    @DisplayName("킹이 모두 살아있을 경우 NONE을 반환한다")
    void isNotExistWinner() {
        Map<Position, Piece> initialPieces = new HashMap<>();
        initialPieces.put(Position.valueOf(File.A, Rank.ONE), new King(Color.BLACK));
        initialPieces.put(Position.valueOf(File.A, Rank.TWO), new King(Color.WHITE));

        Winner winner = new Winner(initialPieces);

        assertThat(winner.getColor()).isEqualTo(Color.NONE);
    }
}