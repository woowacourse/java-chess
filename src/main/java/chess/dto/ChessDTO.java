package chess.dto;

import java.util.List;

public class ChessDTO {
    private List<String> ranks;
    private String turn;

    public ChessDTO(List<String> ranks, String turn) {
        this.ranks = ranks;
        this.turn = turn;
    }

    public List<String> getRanks() {
        return ranks;
    }

    public String getTurn() {
        return turn;
    }
}
