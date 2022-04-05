package chess.model;

import static chess.model.ChessGame.*;
import static chess.model.File.*;
import static chess.model.Rank.*;
import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.model.boardinitializer.PieceArrangement;
import chess.model.boardinitializer.DefaultArrangement;
import chess.model.piece.King;
import chess.model.piece.Piece;
import chess.model.piece.Rook;

public class ChessGameTest {
    @DisplayName("킹이 잡힐 경우 isFinished는 true를 반환한다.")
    @Test
    void move_return_true_when_king_captured() {
        ChessGame chessGame = new ChessGame(new Turn(), new kingCaptureTestInitializer());

        chessGame.move(Position.of(A, TWO), Position.of(A, THREE));
        assertThat(chessGame.isFinished()).isTrue();
    }

    @DisplayName("체스 게임이 끝나면 move를 호출할 수 없다.")
    @Test
    void cannot_move_after_finished() {
        //given
        ChessGame chessGame = new ChessGame(new Turn(), new kingCaptureTestInitializer());

        //when
        chessGame.move(Position.of(A, TWO), Position.of(A, THREE));

        //then
        assertThatThrownBy(() -> chessGame.move(Position.of(A, THREE), Position.of(A, FOUR))
        ).isInstanceOf(IllegalStateException.class)
            .hasMessage(ERROR_NOT_MOVABLE_CHESS_FINISHED);
    }

    @DisplayName("킹이 잡힐 경우 룩 한 개 남은 승자의 점수 5점을 반환한다.")
    @Test
    void score_is_5_when_king_captured() {
        ChessGame chessGame = new ChessGame(new Turn(), new kingCaptureTestInitializer());
        chessGame.move(Position.of(A, TWO), Position.of(A, THREE));
        double actual = chessGame.getScore();
        assertThat(actual).isEqualTo(5);
    }

    @Test
    @DisplayName("첫판에 점수를 계산하면 38점이 나온다")
    void when_first_turn_cal_score_then_38() {
        ChessGame chessGame = new ChessGame(new Turn(), new DefaultArrangement());
        double score = chessGame.getScore();
        assertThat(score).isEqualTo(38.0);
    }

    public static class kingCaptureTestInitializer implements PieceArrangement {

        @Override
        public Map<Position, Piece> apply() {
            Map<Position, Piece> result = new HashMap<>();
            result.put(Position.of(A, TWO), Rook.colorOf(PieceColor.WHITE));
            result.put(Position.of(A, THREE), King.colorOf(PieceColor.BLACK));
            return result;
        }
    }
}
