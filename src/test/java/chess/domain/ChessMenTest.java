package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.piece.ChessPiece;
import chess.domain.piece.King;
import chess.domain.piece.Rook;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessMenTest {

    @Test
    @DisplayName("주어진 위치에 존재하는 체스 피스를 삭제한다.")
    void removeChessPieceAt() {
        Team team = Team.BLACK;
        Rook rook1 = new Rook(team, ChessBoardPosition.of("c3"));
        Rook rook2 = new Rook(team, ChessBoardPosition.of("d5"));
        List<ChessPiece> chessPieces = new ArrayList<>();
        chessPieces.add(rook1);
        chessPieces.add(rook2);
        ChessMen chessMen = new ChessMen(chessPieces);
        ChessBoardPosition targetPosition = ChessBoardPosition.of("d5");
        chessMen.removeChessPieceAt(targetPosition);
        assertThat(chessMen).containsOnly(rook1);
    }

    @Test
    @DisplayName("킹이 존재하는 경우 true 반환한다.")
    void isKingDead() {
        Team team = Team.BLACK;
        King king = new King(team, ChessBoardPosition.of("c3"));
        List<ChessPiece> chessPieces = new ArrayList<>();
        chessPieces.add(king);
        ChessMen chessMen = new ChessMen(chessPieces);
        assertFalse(chessMen.isKingDead());
    }

    @Test
    @DisplayName("킹이 없는 경우 false 반환한다.")
    void isKingDead2() {
        List<ChessPiece> chessPieces = new ArrayList<>();
        ChessMen chessMen = new ChessMen(chessPieces);
        assertTrue(chessMen.isKingDead());
    }
}
