package chess.domain.history;

import chess.domain.board.Cell;
import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardGenerator;
import chess.domain.board.Coordinate;
import chess.domain.piece.TeamType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class HistoriesTest {

    @DisplayName("히스토리의 가장 마지막 기물의 피스 색상의 반대가 다음 턴의 팀 타입이다.")
    @Test
    void findNextTeamType() {
        List<History> historyList = Arrays.asList(new History("d2", "c1", "BLACK"),
                new History("d3", "a2", "WHITE"));
        Histories histories = new Histories(historyList);

        TeamType nextTeamType = histories.findNextTeamType();

        assertThat(nextTeamType).isEqualTo(TeamType.BLACK);
    }

    @DisplayName("히스토리 이력대로 ChessBoard가 변경된다.")
    @Test
    void restoreLatestChessBoard() {
        List<History> historyList = Arrays.asList(new History("a2", "a3", "WHITE"),
                new History("a7", "a6", "BLACK"));
        Histories histories = new Histories(historyList);
        Map<Coordinate, Cell> cells = ChessBoardGenerator.generateDefaultChessBoard();
        ChessBoard chessBoard = new ChessBoard(cells);

        histories.restoreChessBoardAsLatest(chessBoard);
        Cell a3 = cells.get(Coordinate.from("a3"));
        Cell a6 = cells.get(Coordinate.from("a6"));

        assertThat(a3.isEmpty()).isFalse();
        assertThat(a6.isEmpty()).isFalse();
    }
}
