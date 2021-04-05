package chess.domain.history;

import chess.domain.board.ChessBoard;
import chess.domain.board.Coordinate;
import chess.domain.piece.TeamType;

import java.util.List;

public class Histories {

    private final List<History> histories;

    public Histories(List<History> histories) {
        this.histories = histories;
    }

    public void restoreChessBoardAsLatest(ChessBoard chessBoard) {
        histories.forEach(history -> updateChessBoardByEachHistory(chessBoard, history));
    }

    private void updateChessBoardByEachHistory(ChessBoard chessBoard, History history) {
        Coordinate source = Coordinate.from(history.getSource());
        Coordinate destination = Coordinate.from(history.getDestination());
        TeamType teamType = TeamType.valueOf(history.getTeamType());
        chessBoard.move(source, destination, teamType);
    }

    public TeamType findNextTeamType() {
        if (histories.isEmpty()) {
            return TeamType.WHITE;
        }
        int historyCounts = histories.size();
        String teamTypeValue = histories.get(historyCounts - 1)
                .getTeamType();
        return TeamType.valueOf(teamTypeValue)
                .findOppositeTeam();
    }
}
