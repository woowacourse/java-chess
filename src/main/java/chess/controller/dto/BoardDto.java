package chess.controller.dto;

import java.util.List;

public class BoardDto {

    private final List<PieceDto> pieces;
    private final int boardSize;
    private final String currentTeam;

    public BoardDto(List<PieceDto> pieces, int boardSize, String currentTeam) {
        this.pieces = pieces;
        this.boardSize = boardSize;
        this.currentTeam = currentTeam;
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
}
