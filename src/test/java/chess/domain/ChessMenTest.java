package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.ChessPiece;
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
}
