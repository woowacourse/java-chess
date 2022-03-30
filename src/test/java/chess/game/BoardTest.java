package chess.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.piece.Color;
import chess.piece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BoardTest {

    @ParameterizedTest(name = "from : {0}, to : {1}")
    @CsvSource(value = {"a3,a4", "a6,a5", "g6,g5", "d3,d4", "d6,e5"})
    @DisplayName("현재 위치에 말이 존재하는지 검증한다.")
    void existPieceInFromPosition(final String from, final String to) {
        final Board board = new Board(BoardInitializer.getBoard());

        assertThatThrownBy(() -> board.movePiece(Position.of(from), Position.of(to), Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 말이 존재하지 않습니다.");
    }

    @ParameterizedTest(name = "from : {0}, to : {1}")
    @CsvSource(value = {"a1,a2", "d2,d1"})
    @DisplayName("이동할 위치에 말이 존재하는지 검증한다.")
    void existPieceInToPosition(final String from, final String to) {
        final Board board = new Board(BoardInitializer.getBoard());

        assertThatThrownBy(() -> board.movePiece(Position.of(from), Position.of(to), Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 위치에 같은색의 말이 존재합니다.");
    }

    @Test
    @DisplayName("폰은 상대말이 가로막고 있을 때 전진할 수 없다.")
    void pawnCannotAttackForward() {
        final Board board = new Board(BoardInitializer.getBoard());
        board.movePiece(Position.of("a2"), Position.of("a4"), Color.WHITE);
        board.movePiece(Position.of("a4"), Position.of("a5"), Color.WHITE);
        board.movePiece(Position.of("a5"), Position.of("a6"), Color.WHITE);

        assertThatThrownBy(() -> board.movePiece(Position.of("a6"), Position.of("a7"), Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 상대말이 있을 때 직진할 수 없습니다.");
    }

    @Test
    @DisplayName("룩이 전진해서 상대 말을 잡는다.")
    void rookAttackForward() {
        final Board board = new Board(BoardInitializer.getBoard());
        board.movePiece(Position.of("a2"), Position.of("a4"), Color.WHITE);
        board.movePiece(Position.of("a1"), Position.of("a3"), Color.WHITE);
        board.movePiece(Position.of("a3"), Position.of("d3"), Color.WHITE);
        board.movePiece(Position.of("d3"), Position.of("d7"), Color.WHITE);

        assertThat(board.getValue().get(Position.of("d7"))).isInstanceOf(Rook.class);
    }

    @Test
    @DisplayName("남아있는 말에 따라 점수를 계산한다.")
    void score() {
        final Board board = new Board(BoardInitializer.getBoard());
        final Score boardScore = board.calculateBoardScore();

        assertThat(boardScore.getScore().get(Color.BLACK)).isEqualTo(38);
    }

    @Test
    @DisplayName("폰을 잃은 팀의 점수를 계산한다.")
    void scorePawnLoss() {
        final Board board = new Board(BoardInitializer.getBoard());
        board.movePiece(Position.of("a2"), Position.of("a4"), Color.WHITE);
        board.movePiece(Position.of("a4"), Position.of("a5"), Color.WHITE);
        board.movePiece(Position.of("a5"), Position.of("a6"), Color.WHITE);
        board.movePiece(Position.of("a6"), Position.of("b7"), Color.WHITE);

        final Score boardScore = board.calculateBoardScore();

        assertThat(boardScore.getScore().get(Color.BLACK)).isEqualTo(37);
    }

    @Test
    @DisplayName("룩을 잃은 팀의 점수를 계산한다.")
    void scoreRookLoss() {
        final Board board = new Board(BoardInitializer.getBoard());
        board.movePiece(Position.of("b1"), Position.of("a3"), Color.WHITE);
        board.movePiece(Position.of("a3"), Position.of("c4"), Color.WHITE);
        board.movePiece(Position.of("c4"), Position.of("b6"), Color.WHITE);
        board.movePiece(Position.of("b6"), Position.of("a8"), Color.WHITE);

        final Score boardScore = board.calculateBoardScore();

        assertThat(boardScore.getScore().get(Color.BLACK)).isEqualTo(33);
    }
    
    @Test
    @DisplayName("나이트를 잃은 팀의 점수를 계산한다.")
    void scoreKnightLoss() {
        final Board board = new Board(BoardInitializer.getBoard());
        board.movePiece(Position.of("b1"), Position.of("c3"), Color.WHITE);
        board.movePiece(Position.of("c3"), Position.of("a4"), Color.WHITE);
        board.movePiece(Position.of("a4"), Position.of("c5"), Color.WHITE);
        board.movePiece(Position.of("c5"), Position.of("a6"), Color.WHITE);
        board.movePiece(Position.of("a6"), Position.of("b8"), Color.WHITE);

        final Score boardScore = board.calculateBoardScore();

        assertThat(boardScore.getScore().get(Color.BLACK)).isEqualTo(35.5);
    }

    @Test
    @DisplayName("비숍를 잃은 팀의 점수를 계산한다.")
    void scoreBishopLoss() {
        final Board board = new Board(BoardInitializer.getBoard());
        board.movePiece(Position.of("b1"), Position.of("c3"), Color.WHITE);
        board.movePiece(Position.of("c3"), Position.of("b5"), Color.WHITE);
        board.movePiece(Position.of("b5"), Position.of("d6"), Color.WHITE);
        board.movePiece(Position.of("d6"), Position.of("c8"), Color.WHITE);

        final Score boardScore = board.calculateBoardScore();

        assertThat(boardScore.getScore().get(Color.BLACK)).isEqualTo(35);
    }

    @Test
    @DisplayName("퀸을 잃은 팀의 점수를 계산한다.")
    void scoreQueenLoss() {
        final Board board = new Board(BoardInitializer.getBoard());
        board.movePiece(Position.of("b1"), Position.of("a3"), Color.WHITE);
        board.movePiece(Position.of("a3"), Position.of("c4"), Color.WHITE);
        board.movePiece(Position.of("c4"), Position.of("a5"), Color.WHITE);
        board.movePiece(Position.of("a5"), Position.of("c6"), Color.WHITE);
        board.movePiece(Position.of("c6"), Position.of("d8"), Color.WHITE);

        final Score boardScore = board.calculateBoardScore();

        assertThat(boardScore.getScore().get(Color.BLACK)).isEqualTo(29);
    }

    @Test
    @DisplayName("같은 열에 폰이 2개 있을 경우 점수를 계산한다.")
    void scoreTwoPawnsOnSameColumn() {
        final Board board = new Board(BoardInitializer.getBoard());
        board.movePiece(Position.of("a2"), Position.of("a4"), Color.WHITE);
        board.movePiece(Position.of("a4"), Position.of("a5"), Color.WHITE);
        board.movePiece(Position.of("a5"), Position.of("a6"), Color.WHITE);
        board.movePiece(Position.of("a6"), Position.of("b7"), Color.WHITE);

        final Score boardScore = board.calculateBoardScore();

        assertThat(boardScore.getScore().get(Color.WHITE)).isEqualTo(37);
    }

    @Test
    @DisplayName("같은 열에 폰이 3개 있을 경우 점수를 계산한다.")
    void scoreThreePawnsOnSameColumn() {
        final Board board = new Board(BoardInitializer.getBoard());
        board.movePiece(Position.of("b2"), Position.of("b4"), Color.WHITE);
        board.movePiece(Position.of("b4"), Position.of("b5"), Color.WHITE);
        board.movePiece(Position.of("b5"), Position.of("b6"), Color.WHITE);
        board.movePiece(Position.of("b6"), Position.of("a7"), Color.WHITE);

        board.movePiece(Position.of("c2"), Position.of("c4"), Color.WHITE);
        board.movePiece(Position.of("c4"), Position.of("c5"), Color.WHITE);
        board.movePiece(Position.of("c5"), Position.of("c6"), Color.WHITE);
        board.movePiece(Position.of("c6"), Position.of("b7"), Color.WHITE);
        board.movePiece(Position.of("b7"), Position.of("a8"), Color.WHITE);

        final Score boardScore = board.calculateBoardScore();

        assertThat(boardScore.getScore().get(Color.WHITE)).isEqualTo(36.5);
    }
}
