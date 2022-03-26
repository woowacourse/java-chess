package chess.domain;

import static chess.domain.piece.Team.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessBoardTest {

    @DisplayName("모든 피스들의 합은 38이다.")
    @Test
    public void calculateScore() {
        //given
        ChessBoard chessBoard = new ChessBoard();

        //when
        double score = chessBoard.calculateByTeam(WHITE);

        //then
        assertThat(score).isEqualTo(38);
    }

    @DisplayName("폰이 같은 file에 2개 이상인 경우 0.5로 계산한다.")
    @Test
    public void calculatePawnByHalf() {
        //given
        ChessBoard chessBoard = new ChessBoard();

        chessBoard.move(Command.from("move a2 a4"));
        chessBoard.move(Command.from("move b7 b5"));
        chessBoard.move(Command.from("move a4 b5"));

        //when
        double score = chessBoard.calculateByTeam(WHITE);

        //then
        assertThat(score).isEqualTo(37);
    }
}
