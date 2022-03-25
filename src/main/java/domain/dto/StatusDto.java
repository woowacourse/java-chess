package domain.dto;

import domain.ChessBoard;
import domain.piece.property.TeamColor;
import domain.utils.Result;

public class StatusDto {

    private final Result result;
    private final TeamColor turn;
    private final double score;

    public StatusDto(final ChessBoard chessBoard) {
        result = chessBoard.calculateWinner();
        turn = chessBoard.getCurrentTurn();
        score = chessBoard.calculateTeamScore(turn);
    }

    public Result getResult() {
        return result;
    }

    public TeamColor getTurn() {
        return turn;
    }

    public double getScore() {
        return score;
    }
}
