package domain;

import domain.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ScoreMachineTest {

    @DisplayName("검은색 기물의 점수 합계를 계산한다.")
    @Test
    void black_pieces_score_sum() {
        Board board = new Board();
        Queen queen = Queen.Of("Q", Position.Of(3, 3), true);
        Rook rook = Rook.Of("Q", Position.Of(4, 4), true);
        Bishop bishop = Bishop.Of("Q", Position.Of(5, 5), true);

        board.put(queen, Position.Of(3, 3));
        board.put(rook, Position.Of(4, 4));
        board.put(bishop, Position.Of(5, 5));

        ScoreMachine scoreMachine = new ScoreMachine(board.getBoard());
        assertThat(scoreMachine.blackPiecesScore()).isEqualTo(new Score(17));
    }

    @DisplayName("희색 기물의 점수 합계를 계산한다.")
    @Test
    void white_pieces_score_sum() {
        Board board = new Board();
        Queen queen = Queen.Of("Q", Position.Of(3, 3), false);
        Rook rook = Rook.Of("Q", Position.Of(4, 4), false);
        Bishop bishop = Bishop.Of("Q", Position.Of(5, 5), false);

        board.put(queen, Position.Of(3, 3));
        board.put(rook, Position.Of(4, 4));
        board.put(bishop, Position.Of(5, 5));

        ScoreMachine scoreMachine = new ScoreMachine(board.getBoard());
        assertThat(scoreMachine.whitePiecesScore()).isEqualTo(new Score(17));
    }

    @DisplayName("검은 진영의 점수를 계산한다.(같은 세로 줄에 같은 색 폰이 없는 경우)")
    @Test
    void calculate_score_if_not_exist_same_color_pawn() {
        Board board = new Board();
        board.put(King.Of("K", Position.Of(0, 1), true), Position.Of(0, 1));
        board.put(Rook.Of("R", Position.Of(0, 2), true), Position.Of(0, 2));
        board.put(Pawn.Of("P", Position.Of(1, 0), true), Position.Of(1, 0));
        board.put(Pawn.Of("P", Position.Of(1, 2), true), Position.Of(1, 2));
        board.put(Bishop.Of("B", Position.Of(1, 3), true), Position.Of(1, 3));
        board.put(Pawn.Of("P", Position.Of(2, 1), true), Position.Of(2, 1));
        board.put(Queen.Of("Q", Position.Of(2, 4), true), Position.Of(2, 4));

        ScoreMachine scoreMachine = new ScoreMachine(board.getBoard());
        assertThat(scoreMachine.blackPiecesScore()).isEqualTo(new Score(20));
    }

    @DisplayName("Pawn의 기본 점수는 1점, 세로 줄에 같은 색 폰이 있는 경우 0.5점으로 계산한다.")
    @Test
    void calculate_pawn_score() {
        Board board = new Board();
        board.put(Knight.Of("n", Position.Of(4, 5), false), Position.Of(4, 5));
        board.put(Queen.Of("q", Position.Of(4, 6), false), Position.Of(4, 6));
        board.put(Pawn.Of("p", Position.Of(5, 5), false), Position.Of(5, 5));
        board.put(Pawn.Of("p", Position.Of(5, 7), false), Position.Of(5, 7));
        board.put(Pawn.Of("p", Position.Of(6, 5), false), Position.Of(6, 5));
        board.put(Pawn.Of("p", Position.Of(6, 6), false), Position.Of(6, 6));
        board.put(Rook.Of("r", Position.Of(7, 4), false), Position.Of(7, 4));
        board.put(King.Of("k", Position.Of(7, 5), false), Position.Of(7, 5));

        ScoreMachine scoreMachine = new ScoreMachine(board.getBoard());
        assertThat(scoreMachine.whitePiecesScore()).isEqualTo(new Score(19.5));
    }
}