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
        board.put(Coordinate.fromOnBoard(0, 4), new Rook(Camp.WHITE));
        board.put(Coordinate.fromOnBoard(0, 5), new King(Camp.WHITE));
        board.put(Coordinate.fromOnBoard(1, 5), new WhitePawn());
        board.put(Coordinate.fromOnBoard(1, 6), new WhitePawn());
        board.put(Coordinate.fromOnBoard(2, 5), new WhitePawn());
        board.put(Coordinate.fromOnBoard(2, 7), new WhitePawn());
        board.put(Coordinate.fromOnBoard(3, 5), new Knight(Camp.WHITE));
        board.put(Coordinate.fromOnBoard(4, 6), new Queen(Camp.WHITE));

        board.put(Coordinate.fromOnBoard(5, 1), new BlackPawn());
        board.put(Coordinate.fromOnBoard(5, 4), new Queen(Camp.BLACK));
        board.put(Coordinate.fromOnBoard(6, 0), new BlackPawn());
        board.put(Coordinate.fromOnBoard(6, 2), new BlackPawn());
        board.put(Coordinate.fromOnBoard(6, 3), new Bishop(Camp.BLACK));
        board.put(Coordinate.fromOnBoard(7, 1), new King(Camp.BLACK));
        board.put(Coordinate.fromOnBoard(7, 2), new Rook(Camp.BLACK));
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
