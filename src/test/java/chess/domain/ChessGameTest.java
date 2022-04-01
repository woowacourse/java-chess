package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.AbstractPiece;
import chess.domain.piece.Queen;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessGameTest {

    @Test
    @DisplayName("점수를 잘 계산하는지")
    void calculateScore() {
        Map<Position, AbstractPiece> pieces = new HashMap<>();
        pieces.put(Position.of(Column.C, Row.RANK_2), new Queen(Color.BLACK)); // 9
        pieces.put(Position.of(Column.C, Row.RANK_3), new King(Color.BLACK)); // 0
        pieces.put(Position.of(Column.H, Row.RANK_4), new Pawn(Color.BLACK)); // 1
        pieces.put(Position.of(Column.H, Row.RANK_3), new Pawn(Color.BLACK)); // 0.5
        pieces.put(Position.of(Column.G, Row.RANK_3), new Pawn(Color.BLACK)); // 0.5
        pieces.put(Position.of(Column.C, Row.RANK_6), new Pawn(Color.BLACK)); // 1
        pieces.put(Position.of(Column.C, Row.RANK_1), new King(Color.WHITE)); // 0
        pieces.put(Position.of(Column.A, Row.RANK_2), new Pawn(Color.WHITE)); // 0.5
        pieces.put(Position.of(Column.B, Row.RANK_2), new Pawn(Color.WHITE)); // 0.5

        ChessGame chessGame = new ChessGame(pieces);
        Map<Color, Score> scoreByColor = chessGame.calculateScore();

        assertAll(
                () -> assertThat(scoreByColor.get(Color.BLACK).getValue()).isEqualTo(12),
                () -> assertThat(scoreByColor.get(Color.WHITE).getValue()).isEqualTo(1)
        );
    }

    @Test
    @DisplayName("게임이 실행중이다.")
    void isRunning() {
        Map<Position, AbstractPiece> pieces = new HashMap<>();
        pieces.put(Position.of(Column.C, Row.RANK_2), new Queen(Color.BLACK));
        pieces.put(Position.of(Column.C, Row.RANK_3), new King(Color.BLACK));
        pieces.put(Position.of(Column.H, Row.RANK_4), new Pawn(Color.BLACK));
        pieces.put(Position.of(Column.C, Row.RANK_1), new King(Color.WHITE));
        pieces.put(Position.of(Column.A, Row.RANK_2), new Pawn(Color.WHITE));
        pieces.put(Position.of(Column.B, Row.RANK_2), new Pawn(Color.WHITE));

        ChessGame chessGame = new ChessGame(pieces);

        assertThat(chessGame.isRunning()).isTrue();
    }

    @Test
    @DisplayName("King 이 잡혀서 게임이 종료됐다.")
    void isFinished() {
        Map<Position, AbstractPiece> pieces = new HashMap<>();
        pieces.put(Position.of(Column.C, Row.RANK_2), new Queen(Color.BLACK));
        pieces.put(Position.of(Column.C, Row.RANK_3), new King(Color.BLACK));
        pieces.put(Position.of(Column.H, Row.RANK_4), new Pawn(Color.BLACK));
        pieces.put(Position.of(Column.A, Row.RANK_2), new Pawn(Color.WHITE));
        pieces.put(Position.of(Column.B, Row.RANK_2), new Pawn(Color.WHITE));

        ChessGame chessGame = new ChessGame(pieces);

        assertThat(chessGame.isRunning()).isFalse();
    }
}
