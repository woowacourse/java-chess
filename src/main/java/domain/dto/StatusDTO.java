package domain.dto;

import domain.Player;
import domain.Result;
import domain.chessboard.ChessBoard;

public class StatusDTO {

    private final Result result;
    private final Player player;
    private final double score;

    public StatusDTO(final ChessBoard chessBoard) {
        this.result = chessBoard.calculateResult();
        this.player = chessBoard.getCurrentPlayer();
        this.score = chessBoard.calculateScoreByPlayer(player);
    }

    public Result getResult() {
        return result;
    }

    public Player getPlayer() {
        return player;
    }

    public double getScore() {
        return score;
    }
}
