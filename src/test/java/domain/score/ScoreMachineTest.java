package domain.score;

import domain.Board;
import domain.piece.Position;
import domain.piece.objects.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ScoreMachineTest {

    @DisplayName("검은색 기물의 점수 합계를 계산한다.")
    @Test
    void black_pieces_score_sum() {
        Map<Position, Piece> pieces = new HashMap<Position, Piece>() {{
            put(Position.of("d5"), Queen.of("Q", true));
            put(Position.of("f4"), Rook.of("R", true));
            put(Position.of("f3"), Bishop.of("B", true));
        }};
        Board board = new Board(pieces);
        assertThat(ScoreMachine.blackPiecesScore(board)).isEqualTo(Score.of(17));
    }

    @DisplayName("흰색 기물의 점수 합계를 계산한다.")
    @Test
    void white_pieces_score_sum() {
        Map<Position, Piece> pieces = new HashMap<Position, Piece>() {{
            put(Position.of("d5"), Queen.of("q", false));
            put(Position.of("e4"), Rook.of("r", false));
            put(Position.of("f3"), Bishop.of("b", false));
        }};
        Board board = new Board(pieces);
        assertThat(ScoreMachine.whitePiecesScore(board)).isEqualTo(Score.of(17));
    }

    @DisplayName("검은 진영의 점수를 계산한다.(같은 세로 줄에 같은 색 폰이 없는 경우)")
    @Test
    void calculate_score_if_not_exist_same_color_pawn() {
        Map<Position, Piece> pieces = new HashMap<Position, Piece>() {{
            put(Position.of("b8"), King.of("K", true));
            put(Position.of("c8"), Rook.of("R", true));
            put(Position.of("a7"), Pawn.of("P", true));
            put(Position.of("c7"), Pawn.of("P", true));
            put(Position.of("d7"), Bishop.of("B", true));
            put(Position.of("b6"), Pawn.of("B", true));
            put(Position.of("e6"), Queen.of("B", true));
        }};
        Board board = new Board(pieces);
        assertThat(ScoreMachine.blackPiecesScore(board)).isEqualTo(Score.of(20));
    }

    @DisplayName("Pawn의 기본 점수는 1점, 세로 줄에 같은 색 폰이 있는 경우 0.5점으로 계산한다.")
    @Test
    void calculate_pawn_score() {
        Map<Position, Piece> pieces = new HashMap<Position, Piece>() {{
            put(Position.of("f4"), Knight.of("n", false));
            put(Position.of("g4"), Queen.of("q", false));
            put(Position.of("f3"), Pawn.of("p", false));
            put(Position.of("h3"), Pawn.of("p", false));
            put(Position.of("f2"), Pawn.of("p", false));
            put(Position.of("g2"), Pawn.of("p", false));
            put(Position.of("e1"), Rook.of("r", false));
            put(Position.of("f1"), King.of("k", false));
        }};
        Board board = new Board(pieces);
        assertThat(ScoreMachine.whitePiecesScore(board)).isEqualTo(Score.of(19.5));
    }
}