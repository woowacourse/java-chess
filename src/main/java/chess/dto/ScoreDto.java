package chess.dto;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Team;

public class ScoreDto {
    private final double whiteScore;
    private final double blackScore;
    private final String winnerTeam;
    
    public ScoreDto(ChessBoard chessBoard) {
        whiteScore = chessBoard.calculateScore(Team.WHITE);
        blackScore = chessBoard.calculateScore(Team.BLACK);
        winnerTeam = chessBoard.winnerTeam().name().toUpperCase();
    }
    
    public double whiteScore() {
        return whiteScore;
    }
    
    public double blackScore() {
        return blackScore;
    }
    
    public String winnerTeam() {
        return winnerTeam;
    }
}
