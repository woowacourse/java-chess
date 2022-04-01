package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinResultTest {

    @Test
    @DisplayName("검정색이 이긴 경우")
    void winBlack() {
        Map<Position, Piece> blackPieces = new HashMap<>();
        blackPieces.put(Position.of(Column.C, Row.RANK_2), new Queen(Color.BLACK)); // 9
        blackPieces.put(Position.of(Column.H, Row.RANK_4), new Pawn(Color.BLACK)); // 1
        blackPieces.put(Position.of(Column.H, Row.RANK_3), new Pawn(Color.BLACK)); // 0.5
        blackPieces.put(Position.of(Column.G, Row.RANK_3), new Pawn(Color.BLACK)); // 0.5
        blackPieces.put(Position.of(Column.C, Row.RANK_6), new King(Color.BLACK)); // 1
        Score blackScore = Score.of(blackPieces);

        Map<Position, Piece> whitePieces = new HashMap<>();
        whitePieces.put(Position.of(Column.H, Row.RANK_4), new Pawn(Color.WHITE)); // 1
        whitePieces.put(Position.of(Column.C, Row.RANK_6), new King(Color.WHITE)); // 0
        Score whiteScore = Score.of(whitePieces);

        WinResult winResult = WinResult.of(blackScore, whiteScore);

        assertThat(winResult).isEqualTo(WinResult.BLACK);
    }

    @Test
    @DisplayName("흰색이 이긴 경우")
    void winWhite() {
        Map<Position, Piece> blackPieces = new HashMap<>();
        blackPieces.put(Position.of(Column.C, Row.RANK_2), new Queen(Color.BLACK)); // 9
        blackPieces.put(Position.of(Column.H, Row.RANK_4), new Pawn(Color.BLACK)); // 1
        blackPieces.put(Position.of(Column.H, Row.RANK_3), new Pawn(Color.BLACK)); // 0.5
        blackPieces.put(Position.of(Column.G, Row.RANK_3), new Pawn(Color.BLACK)); // 0.5
        blackPieces.put(Position.of(Column.C, Row.RANK_6), new King(Color.BLACK)); // 0
        Score blackScore = Score.of(blackPieces);

        Map<Position, Piece> whitePieces = new HashMap<>();
        whitePieces.put(Position.of(Column.C, Row.RANK_5), new Queen(Color.WHITE)); // 9
        whitePieces.put(Position.of(Column.H, Row.RANK_1), new Pawn(Color.WHITE)); // 1
        whitePieces.put(Position.of(Column.H, Row.RANK_5), new Bishop(Color.WHITE)); // 3
        whitePieces.put(Position.of(Column.G, Row.RANK_5), new Knight(Color.WHITE)); // 2.5
        whitePieces.put(Position.of(Column.C, Row.RANK_4), new King(Color.WHITE)); // 0
        Score whiteScore = Score.of(whitePieces);

        WinResult winResult = WinResult.of(blackScore, whiteScore);

        assertThat(winResult).isEqualTo(WinResult.WHITE);
    }


    @Test
    @DisplayName("비긴 경우")
    void draw() {
        Map<Position, Piece> blackPieces = new HashMap<>();
        blackPieces.put(Position.of(Column.C, Row.RANK_2), new Queen(Color.BLACK)); // 9
        blackPieces.put(Position.of(Column.H, Row.RANK_4), new Pawn(Color.BLACK)); // 1
        blackPieces.put(Position.of(Column.H, Row.RANK_3), new Bishop(Color.BLACK)); // 3
        blackPieces.put(Position.of(Column.G, Row.RANK_3), new Knight(Color.BLACK)); // 2.5
        blackPieces.put(Position.of(Column.C, Row.RANK_6), new King(Color.BLACK)); // 0
        Score blackScore = Score.of(blackPieces);

        Map<Position, Piece> whitePieces = new HashMap<>();
        whitePieces.put(Position.of(Column.D, Row.RANK_2), new Queen(Color.WHITE)); // 9
        whitePieces.put(Position.of(Column.D, Row.RANK_4), new Pawn(Color.WHITE)); // 1
        whitePieces.put(Position.of(Column.D, Row.RANK_3), new Bishop(Color.WHITE)); // 3
        whitePieces.put(Position.of(Column.B, Row.RANK_3), new Knight(Color.WHITE)); // 2.5
        whitePieces.put(Position.of(Column.B, Row.RANK_6), new King(Color.WHITE)); // 0
        Score whiteScore = Score.of(whitePieces);

        WinResult winResult = WinResult.of(blackScore, whiteScore);

        assertThat(winResult).isEqualTo(WinResult.DRAW);
    }
}
