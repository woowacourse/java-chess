package chess.dto;

import chess.domain.direction.core.Square;
import chess.domain.piece.core.Piece;

import java.util.Map;

public class ChessMoveDTO {
    private Map<Square, Piece> board;
    private String source;
    private String target;
    private String team;

    public Map<Square, Piece> getBoard() {
        return board;
    }

    public void setBoard(Map<Square, Piece> board) {
        this.board = board;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
