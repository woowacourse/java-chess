package chess.dto;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Team;

public class ScoreDto {
    private final double whiteScore;
    private final double blackScore;
    private final Team winnerTeam;
    
    public ScoreDto(ChessBoard chessBoard) {
        whiteScore = chessBoard.calculateScore(Team.WHITE);
        blackScore = chessBoard.calculateScore(Team.BLACK);
        winnerTeam = chessBoard.winnerTeam();
    }
    
    public double whiteScore() {
        return whiteScore;
    }
    
    public double blackScore() {
        return blackScore;
    }
    
    public Team winnerTeam() {
        return winnerTeam;
    }
}
