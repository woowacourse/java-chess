package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static chess.domain.PiecesUtil.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ChessGameTest {

    @Test
    @DisplayName("점수를 잘 계산하는지")
    void calculateScore() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(Position.of(Column.C, Row.RANK_2), BLACK_QUEEN); // 9
        pieces.put(Position.of(Column.C, Row.RANK_3), BLACK_KING); // 0
        pieces.put(Position.of(Column.H, Row.RANK_4), BLACK_PAWN); // 1
        pieces.put(Position.of(Column.H, Row.RANK_3), BLACK_PAWN); // 0.5
        pieces.put(Position.of(Column.G, Row.RANK_3), BLACK_PAWN); // 0.5
        pieces.put(Position.of(Column.C, Row.RANK_6), BLACK_PAWN); // 1
        pieces.put(Position.of(Column.C, Row.RANK_1), WHITE_KING); // 0
        pieces.put(Position.of(Column.A, Row.RANK_2), WHITE_PAWN); // 0.5
        pieces.put(Position.of(Column.B, Row.RANK_2), WHITE_PAWN); // 0.5

        ChessGame chessGame = new ChessGame(pieces, PieceColor.WHITE);
        Map<PieceColor, Score> scoreByColor = chessGame.calculateScoreByColor();

        assertAll(
                () -> assertThat(scoreByColor.get(PieceColor.BLACK).getValue()).isEqualTo(12),
                () -> assertThat(scoreByColor.get(PieceColor.WHITE).getValue()).isEqualTo(1)
        );
    }

    @Test
    @DisplayName("게임이 실행중이다.")
    void isRunning() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(Position.of(Column.C, Row.RANK_2), BLACK_QUEEN);
        pieces.put(Position.of(Column.C, Row.RANK_3), BLACK_KING);
        pieces.put(Position.of(Column.H, Row.RANK_4), BLACK_PAWN);
        pieces.put(Position.of(Column.C, Row.RANK_1), WHITE_KING);
        pieces.put(Position.of(Column.A, Row.RANK_2), WHITE_PAWN);
        pieces.put(Position.of(Column.B, Row.RANK_2), WHITE_PAWN);

        ChessGame chessGame = new ChessGame(pieces, PieceColor.WHITE);

        assertThat(chessGame.isRunning()).isTrue();
    }

    @Test
    @DisplayName("King 이 잡혀서 게임이 종료됐다.")
    void isFinished() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(Position.of(Column.C, Row.RANK_2), BLACK_QUEEN);
        pieces.put(Position.of(Column.C, Row.RANK_3), BLACK_KING);
        pieces.put(Position.of(Column.H, Row.RANK_4), BLACK_PAWN);
        pieces.put(Position.of(Column.A, Row.RANK_2), WHITE_PAWN);
        pieces.put(Position.of(Column.B, Row.RANK_2), WHITE_PAWN);

        ChessGame chessGame = new ChessGame(pieces, PieceColor.WHITE);

        assertThat(chessGame.isRunning()).isFalse();
    }
}
