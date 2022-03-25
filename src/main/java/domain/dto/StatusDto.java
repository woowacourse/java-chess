package domain.dto;

import domain.ChessBoard;
import domain.piece.property.Team;
import domain.utils.Result;

public final class StatusDto {

    private final Result result;
    private final Team turn;
    private final double score;

    public StatusDto(final ChessBoard chessBoard) {
        result = chessBoard.calculateWinner();
        turn = chessBoard.getCurrentTurn();
        score = chessBoard.calculateTeamScore(turn);
    }

    public Result getResult() {
        return result;
    }

    public Team getTurn() {
        return turn;
    }

    public double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "StatusDto{" +
                "result=" + result +
                ", turn=" + turn +
                ", score=" + score +
                '}';
    }
}
