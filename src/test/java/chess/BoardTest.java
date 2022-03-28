package chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.piece.Color;
import chess.piece.Queen;
import chess.piece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Map;

class BoardTest {

    @ParameterizedTest(name = "moveCommand : {0}")
    @ValueSource(strings = {"a3 a4", "g3 g4", "a6 a5", "g6 g5", "d3 d4", "d6 e5"})
    @DisplayName("현재 위치에 말이 존재하는지 검증한다.")
    void existPieceInFromPosition(final String actual) {
        final Board board = Board.create();

        assertThatThrownBy(() -> board.move(MoveCommand.of(actual), Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 말이 존재하지 않습니다.");
    }

    @ParameterizedTest(name = "moveCommand : {0}")
    @ValueSource(strings = {"a1 a2", "g1 g2", "a7 a8", "g7 g8", "d2 d1", "d7 d8"})
    @DisplayName("이동할 위치에 말이 존재하는지 검증한다.")
    void existPieceInToPosition(final String actual) {
        final Board board = Board.create();

        assertThatThrownBy(() -> board.move(MoveCommand.of(actual), Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 위치에 같은색의 말이 존재합니다.");
    }

    @Test
    @DisplayName("폰은 상대말이 가로막고 있을 때 전진할 수 없다.")
    void pawnCannotAttackForward() {
        final Board board = Board.create();
        board.move(MoveCommand.of("a2 a4"), Color.WHITE);
        board.move(MoveCommand.of("a4 a5"), Color.WHITE);
        board.move(MoveCommand.of("a5 a6"), Color.WHITE);

        assertThatThrownBy(() -> board.move(MoveCommand.of("a6 a7"), Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 상대말이 있을 때 직진할 수 없습니다.");
    }

    @Test
    @DisplayName("폰이 공격하는 경우가 아닐 때 대각선 이동시 예외를 발생한다.")
    void pawnCannotMoveDiagonalIfEmpty() {
        final Board board = Board.create();

        assertThatThrownBy(() -> board.move(MoveCommand.of("a2 b3"), Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 상대말이 존재하지 않을 때 대각선으로 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("룩이 전진해서 상대 말을 잡는다.")
    void rookAttackForward() {
        final Board board = Board.create();
        board.move(MoveCommand.of("a2 a4"), Color.WHITE);
        board.move(MoveCommand.of("a1 a3"), Color.WHITE);
        board.move(MoveCommand.of("a3 d3"), Color.WHITE);
        board.move(MoveCommand.of("d3 d7"), Color.WHITE);

        assertThat(board.getValue().get(Position.of("d7"))).isInstanceOf(Rook.class);
    }

    @Test
    @DisplayName("남아있는 말에 따라 점수를 계산한다.")
    void score() {
        final Board board = Board.create();
        final Map<Color, Double> boardScore = board.getBoardScore();

        assertThat(boardScore.get(Color.BLACK)).isEqualTo(38);
    }

    @Test
    @DisplayName("폰을 잃은 팀의 점수를 계산한다.")
    void scorePawnLoss() {
        final Board board = Board.create();
        board.move(MoveCommand.of("a2 a4"), Color.WHITE);
        board.move(MoveCommand.of("a4 a5"), Color.WHITE);
        board.move(MoveCommand.of("a5 a6"), Color.WHITE);
        board.move(MoveCommand.of("a6 b7"), Color.WHITE);

        final Map<Color, Double> boardScore = board.getBoardScore();

        assertThat(boardScore.get(Color.BLACK)).isEqualTo(37);
    }

    @Test
    @DisplayName("룩을 잃은 팀의 점수를 계산한다.")
    void scoreRookLoss() {
        final Board board = Board.create();
        board.move(MoveCommand.of("b1 a3"), Color.WHITE);
        board.move(MoveCommand.of("a3 c4"), Color.WHITE);
        board.move(MoveCommand.of("c4 b6"), Color.WHITE);
        board.move(MoveCommand.of("b6 a8"), Color.WHITE);

        final Map<Color, Double> boardScore = board.getBoardScore();

        assertThat(boardScore.get(Color.BLACK)).isEqualTo(33);
    }
    
    @Test
    @DisplayName("나이트를 잃은 팀의 점수를 계산한다.")
    void scoreKnightLoss() {
        final Board board = Board.create();
        board.move(MoveCommand.of("b1 c3"), Color.WHITE);
        board.move(MoveCommand.of("c3 a4"), Color.WHITE);
        board.move(MoveCommand.of("a4 c5"), Color.WHITE);
        board.move(MoveCommand.of("c5 a6"), Color.WHITE);
        board.move(MoveCommand.of("a6 b8"), Color.WHITE);

        final Map<Color, Double> boardScore = board.getBoardScore();

        assertThat(boardScore.get(Color.BLACK)).isEqualTo(35.5);
    }

    @Test
    @DisplayName("비숍를 잃은 팀의 점수를 계산한다.")
    void scoreBishopLoss() {
        final Board board = Board.create();
        board.move(MoveCommand.of("b1 c3"), Color.WHITE);
        board.move(MoveCommand.of("c3 b5"), Color.WHITE);
        board.move(MoveCommand.of("b5 d6"), Color.WHITE);
        board.move(MoveCommand.of("d6 c8"), Color.WHITE);

        final Map<Color, Double> boardScore = board.getBoardScore();

        assertThat(boardScore.get(Color.BLACK)).isEqualTo(35);
    }

    @Test
    @DisplayName("퀸을 잃은 팀의 점수를 계산한다.")
    void scoreQueenLoss() {
        final Board board = Board.create();
        board.move(MoveCommand.of("b1 a3"), Color.WHITE);
        board.move(MoveCommand.of("a3 c4"), Color.WHITE);
        board.move(MoveCommand.of("c4 a5"), Color.WHITE);
        board.move(MoveCommand.of("a5 c6"), Color.WHITE);
        board.move(MoveCommand.of("c6 d8"), Color.WHITE);

        final Map<Color, Double> boardScore = board.getBoardScore();

        assertThat(boardScore.get(Color.BLACK)).isEqualTo(29);
    }

    @Test
    @DisplayName("같은 열에 폰이 2개 있을 경우 점수를 계산한다.")
    void scoreTwoPawnsOnSameColumn() {
        final Board board = Board.create();
        board.move(MoveCommand.of("a2 a4"), Color.WHITE);
        board.move(MoveCommand.of("a4 a5"), Color.WHITE);
        board.move(MoveCommand.of("a5 a6"), Color.WHITE);
        board.move(MoveCommand.of("a6 b7"), Color.WHITE);

        final Map<Color, Double> boardScore = board.getBoardScore();

        assertThat(boardScore.get(Color.WHITE)).isEqualTo(37);
    }

    @Test
    @DisplayName("같은 열에 폰이 3개 있을 경우 점수를 계산한다.")
    void scoreThreePawnsOnSameColumn() {
        final Board board = Board.create();
        board.move(MoveCommand.of("b2 b4"), Color.WHITE);
        board.move(MoveCommand.of("b4 b5"), Color.WHITE);
        board.move(MoveCommand.of("b5 b6"), Color.WHITE);
        board.move(MoveCommand.of("b6 a7"), Color.WHITE);

        board.move(MoveCommand.of("c2 c4"), Color.WHITE);
        board.move(MoveCommand.of("c4 c5"), Color.WHITE);
        board.move(MoveCommand.of("c5 c6"), Color.WHITE);
        board.move(MoveCommand.of("c6 b7"), Color.WHITE);
        board.move(MoveCommand.of("b7 a8"), Color.WHITE);

        final Map<Color, Double> boardScore = board.getBoardScore();

        assertThat(boardScore.get(Color.WHITE)).isEqualTo(36.5);
    }
}
