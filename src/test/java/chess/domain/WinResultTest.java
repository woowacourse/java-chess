package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static chess.domain.PiecesUtil.*;
import static org.assertj.core.api.Assertions.assertThat;

public class WinResultTest {

    @Test
    @DisplayName("검정색이 이긴 경우")
    void winBlack() {
        Map<Position, Piece> blackPieces = new HashMap<>();
        blackPieces.put(Position.of(Column.C, Row.RANK_2), BLACK_QUEEN); // 9
        blackPieces.put(Position.of(Column.H, Row.RANK_4), BLACK_PAWN); // 1
        blackPieces.put(Position.of(Column.H, Row.RANK_3), BLACK_PAWN); // 0.5
        blackPieces.put(Position.of(Column.G, Row.RANK_3), BLACK_PAWN); // 0.5
        blackPieces.put(Position.of(Column.C, Row.RANK_6), BLACK_KING); // 1
        Score blackScore = Score.of(blackPieces);

        Map<Position, Piece> whitePieces = new HashMap<>();
        whitePieces.put(Position.of(Column.H, Row.RANK_4), WHITE_PAWN); // 1
        whitePieces.put(Position.of(Column.C, Row.RANK_6), WHITE_KING); // 0
        Score whiteScore = Score.of(whitePieces);

        WinResult winResult = WinResult.of(blackScore, whiteScore);

        assertThat(winResult).isEqualTo(WinResult.BLACK);
    }

    @Test
    @DisplayName("흰색이 이긴 경우")
    void winWhite() {
        Map<Position, Piece> blackPieces = new HashMap<>();
        blackPieces.put(Position.of(Column.C, Row.RANK_2), BLACK_QUEEN); // 9
        blackPieces.put(Position.of(Column.H, Row.RANK_4), BLACK_PAWN); // 1
        blackPieces.put(Position.of(Column.H, Row.RANK_3), BLACK_PAWN); // 0.5
        blackPieces.put(Position.of(Column.G, Row.RANK_3), BLACK_PAWN); // 0.5
        blackPieces.put(Position.of(Column.C, Row.RANK_6), BLACK_KING); // 0
        Score blackScore = Score.of(blackPieces);

        Map<Position, Piece> whitePieces = new HashMap<>();
        whitePieces.put(Position.of(Column.C, Row.RANK_5), WHITE_QUEEN); // 9
        whitePieces.put(Position.of(Column.H, Row.RANK_1), WHITE_PAWN); // 1
        whitePieces.put(Position.of(Column.H, Row.RANK_5), WHITE_BISHOP); // 3
        whitePieces.put(Position.of(Column.G, Row.RANK_5), WHITE_KNIGHT); // 2.5
        whitePieces.put(Position.of(Column.C, Row.RANK_4), WHITE_KING); // 0
        Score whiteScore = Score.of(whitePieces);

        WinResult winResult = WinResult.of(blackScore, whiteScore);

        assertThat(winResult).isEqualTo(WinResult.WHITE);
    }

    @Test
    @DisplayName("비긴 경우")
    void draw() {
        Map<Position, Piece> blackPieces = new HashMap<>();
        blackPieces.put(Position.of(Column.C, Row.RANK_2), BLACK_QUEEN); // 9
        blackPieces.put(Position.of(Column.H, Row.RANK_4), BLACK_PAWN); // 1
        blackPieces.put(Position.of(Column.H, Row.RANK_3), BLACK_BISHOP); // 3
        blackPieces.put(Position.of(Column.G, Row.RANK_3), BLACK_KNIGHT); // 2.5
        blackPieces.put(Position.of(Column.C, Row.RANK_6), BLACK_KING); // 0
        Score blackScore = Score.of(blackPieces);

        Map<Position, Piece> whitePieces = new HashMap<>();
        whitePieces.put(Position.of(Column.D, Row.RANK_2), WHITE_QUEEN); // 9
        whitePieces.put(Position.of(Column.D, Row.RANK_4), WHITE_PAWN); // 1
        whitePieces.put(Position.of(Column.D, Row.RANK_3), WHITE_BISHOP); // 3
        whitePieces.put(Position.of(Column.B, Row.RANK_3), WHITE_KNIGHT); // 2.5
        whitePieces.put(Position.of(Column.B, Row.RANK_6), WHITE_KING); // 0
        Score whiteScore = Score.of(whitePieces);

        WinResult winResult = WinResult.of(blackScore, whiteScore);

        assertThat(winResult).isEqualTo(WinResult.DRAW);
    }
}
