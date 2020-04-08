package chess.dto;

import chess.domain.board.Board;
import chess.domain.piece.PieceColor;

import static chess.util.NullValidator.validateNull;

public class TurnDTO {
    private String currentTeam;

    public TurnDTO(String currentTeam) {
        validateNull(currentTeam);
        this.currentTeam = currentTeam;
    }

    public static TurnDTO from(Board board) {
        validateNull(board);

        String currentTeam = board.getTeam().getName();

        return new TurnDTO(currentTeam);
    }

    public String getCurrentTeam() {
        return this.currentTeam;
    }

    public PieceColor createTeam() {
        return PieceColor.of(this.currentTeam);
    }
}