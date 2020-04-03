package chess.web.dto;

import chess.manager.ChessManager;
import chess.piece.Piece;
import chess.piece.Team;
import chess.service.PieceNameConverter;

public class MoveResponse {
    private boolean kingAlive;
    private String sourcePiece;
    private String targetPiece;
    private Team nowTurn;
    private String deadPiece;
    private TeamScoreDto teamScoreDto;

    public MoveResponse(ChessManager chessManager, MoveRequest moveRequest, Piece deadPiece) {
        this.kingAlive = chessManager.isKingAlive();
        this.sourcePiece = PieceNameConverter.findTokenByPiece(chessManager.findByKey(moveRequest.getSourceKey()));
        this.targetPiece = PieceNameConverter.findTokenByPiece(chessManager.findByKey(moveRequest.getTargetKey()));
        this.nowTurn = chessManager.getCurrentTeam();
        this.deadPiece = PieceNameConverter.findTokenByPiece(deadPiece);
        this.teamScoreDto = new TeamScoreDto(chessManager);
    }

    public boolean isKingAlive() {
        return kingAlive;
    }

    public String getSourcePiece() {
        return sourcePiece;
    }

    public String getTargetPiece() {
        return targetPiece;
    }

    public Team getNowTurn() {
        return nowTurn;
    }

    public String getDeadPiece() {
        return deadPiece;
    }

    public TeamScoreDto getTeamScoreDto() {
        return teamScoreDto;
    }
}
