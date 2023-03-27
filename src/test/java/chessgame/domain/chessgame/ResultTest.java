package chessgame.domain.chessgame;

import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.piece.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class ResultTest {

    private static final Map<Coordinate, Piece> board = new HashMap<>();

    @BeforeAll
    static void initBoard() {
        board.put(Coordinate.createOnBoard(0, 4), new Rook(Camp.WHITE));
        board.put(Coordinate.createOnBoard(0, 5), new King(Camp.WHITE));
        board.put(Coordinate.createOnBoard(1, 5), new WhitePawn());
        board.put(Coordinate.createOnBoard(1, 6), new WhitePawn());
        board.put(Coordinate.createOnBoard(2, 5), new WhitePawn());
        board.put(Coordinate.createOnBoard(2, 7), new WhitePawn());
        board.put(Coordinate.createOnBoard(3, 5), new Knight(Camp.WHITE));
        board.put(Coordinate.createOnBoard(4, 6), new Queen(Camp.WHITE));

        board.put(Coordinate.createOnBoard(5, 1), new BlackPawn());
        board.put(Coordinate.createOnBoard(5, 4), new Queen(Camp.BLACK));
        board.put(Coordinate.createOnBoard(6, 0), new BlackPawn());
        board.put(Coordinate.createOnBoard(6, 2), new BlackPawn());
        board.put(Coordinate.createOnBoard(6, 3), new Bishop(Camp.BLACK));
        board.put(Coordinate.createOnBoard(7, 1), new King(Camp.BLACK));
        board.put(Coordinate.createOnBoard(7, 2), new Rook(Camp.BLACK));
    }

    @Test
    @DisplayName("화이트 팀의 체스 판의 상태의 점수를 계산할 수 있다")
    void calculateWhiteScore() {
        Result result = new Result();

        Assertions.assertThat(result.calculateTeamAt(board, Camp.WHITE))
                  .isEqualTo(19.5);
    }

    @Test
    @DisplayName("블랙 팀의 체스 판의 상태의 점수를 계산할 수 있다")
    void calculateBlackScore() {
        Result result = new Result();

        Assertions.assertThat(result.calculateTeamAt(board, Camp.BLACK))
                  .isEqualTo(20);
    }
}
