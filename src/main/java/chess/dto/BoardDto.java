package chess.dto;

import chess.domain.Board;
import chess.domain.Spot;
import chess.domain.Team;
import chess.domain.piece.Piece;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardDto {
    List<spotPieceDto> blackTeam;
    List<spotPieceDto> whiteTeam;

    public BoardDto(Board board) {
        blackTeam = makeSpots(board.getTeamPieces(Team.BLACK));
        whiteTeam = makeSpots(board.getTeamPieces(Team.WHITE));
    }

    private List<spotPieceDto> makeSpots(Map<Spot, Piece> teamPiece) {
        return teamPiece.entrySet().stream()
                .map(spotPieceEntry -> new spotPieceDto(spotPieceEntry.getKey().getIndex(), String.valueOf(spotPieceEntry.getValue().getPieceType())))
                .collect(Collectors.toList());
    }

    public List<spotPieceDto> getBlackTeam() {
        return blackTeam;
    }

    public void setBlackTeam(List<spotPieceDto> blackTeam) {
        this.blackTeam = blackTeam;
    }

    public List<spotPieceDto> getWhiteTeam() {
        return whiteTeam;
    }

    public void setWhiteTeam(List<spotPieceDto> whiteTeam) {
        this.whiteTeam = whiteTeam;
    }
}

