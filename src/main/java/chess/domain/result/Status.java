package chess.domain.result;

import chess.domain.board.Board;
import chess.domain.player.Player;
import chess.domain.position.File;

import java.util.HashMap;
import java.util.Map;

public class Status {
    private Map<Player, Double> status;

    private Status() {
        status = new HashMap<>();
    }

    public static Status of(Board board) {
        Status status = new Status();
        status.update(board);
        return status;
    }

    public void update(Board board) {
        double blackSum = board.getPlayerSumWithoutPawn(Player.BLACK);
        double whiteSum = board.getPlayerSumWithoutPawn(Player.WHITE);
        for (File file : File.values()) {
            blackSum += board.getPawnPointsByFile(file, Player.BLACK);
            whiteSum += board.getPawnPointsByFile(file, Player.WHITE);
        }
        status.put(Player.BLACK, blackSum);
        status.put(Player.WHITE, whiteSum);
    }

    public Map<Player, Double> getStatus() {
        return status;
    }

    public Player getWinner() {
        if (status.get(Player.WHITE) > status.get(Player.BLACK)) {
            return Player.WHITE;
        }
        return Player.BLACK;
    }
}
