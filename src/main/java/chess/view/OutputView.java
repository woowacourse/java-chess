package chess.view;

import chess.domain.piece.Team;

import java.util.List;
import java.util.Map;

public interface OutputView {
    void printBoard(List<String> positions, Map<String, String> board);

    void printWinner(Team team);

    void printStatus(double calculateScore, Team team);
}