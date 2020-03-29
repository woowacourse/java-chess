package chess.domain.result;

import chess.domain.board.Board;
import chess.domain.piece.Team;

public class GameResult {
    public double calculateScore(Board board, Team team) {
        return board.getBoard().stream()
                .filter(piece -> piece.isSameTeam(team))
                .mapToDouble(PieceType::getScoreOf)
                .sum();
    }

    // count, score를 둘 다 저장해서 count가 2보다 크면 score를 2로 나눠서 더한다.
}
