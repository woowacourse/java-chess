package chess.dto;

import chess.domain.Board;
import chess.domain.Spot;
import chess.domain.Team;
import chess.domain.piece.Piece;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardDto {
    List<SpotPieceDto> blackTeam;
    List<SpotPieceDto> whiteTeam;

    public BoardDto(Board board) {
        blackTeam = makeSpots(board.getTeamPieces(Team.BLACK));
        whiteTeam = makeSpots(board.getTeamPieces(Team.WHITE));
    }

    private List<SpotPieceDto> makeSpots(Map<Spot, Piece> teamPiece) {
        return teamPiece.entrySet().stream()
                .map(spotPieceEntry -> new SpotPieceDto(spotPieceEntry.getKey().getIndex(), String.valueOf(spotPieceEntry.getValue().getPieceType())))
                .collect(Collectors.toList());
    }

    public List<SpotPieceDto> getBlackTeam() {
        return blackTeam;
    }

    public void setBlackTeam(List<SpotPieceDto> blackTeam) {
        this.blackTeam = blackTeam;
    }

    public List<SpotPieceDto> getWhiteTeam() {
        return whiteTeam;
    }

    public void setWhiteTeam(List<SpotPieceDto> whiteTeam) {
        this.whiteTeam = whiteTeam;
    }
}

