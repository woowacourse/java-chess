package chess.model.state.finished;

import chess.model.Team;
import chess.model.board.Board;
import chess.model.board.result.GameResult;
import chess.model.piece.Piece;
import java.util.Map;

public final class Status extends Finished {

    private final GameResult gameResult;

    public Status(Board board) {
        super(board);
        this.gameResult = new GameResult(board.getBoard());
    }

    @Override
    public boolean isStatus() {
        return true;
    }

    @Override
    public Map<String, Piece> getBoardForWeb() {
        return board.getBoardForWeb();
    }

    @Override
    public Map<Team, Double> getScore() {
        return gameResult.calculateScore();
    }

    @Override
    public Team getWinner() {
        return gameResult.pickWinnerTeam();
    }
}
