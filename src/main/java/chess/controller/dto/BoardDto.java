package chess.controller.dto;

import java.util.ArrayList;
import java.util.List;

public class BoardDto {

    private final List<PieceDto> pieces;
    private final int boardSize;
    private final String currentTeam;
    private final boolean checked;
    private final boolean isKingDead;

    public BoardDto(int boardSize, String currentTeam, boolean checked, boolean isKingDead) {
        this(new ArrayList<>(), boardSize, currentTeam, checked, isKingDead);
    }

    public BoardDto(List<PieceDto> pieces, int boardSize, String currentTeam, boolean checked, boolean isKingDead) {
        this.pieces = pieces;
        this.boardSize = boardSize;
        this.currentTeam = currentTeam;
        this.checked = checked;
        this.isKingDead = isKingDead;
    }

    public List<PieceDto> getPieces() {
        return pieces;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public String getCurrentTeam() {
        return currentTeam;
    }

    public boolean getChecked() {
        return checked;
    }

    public boolean getKingDead() {
        return isKingDead;
    }
}
