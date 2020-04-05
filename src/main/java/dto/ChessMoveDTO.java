package dto;

import chess.progress.Progress;
import chess.team.Team;

public class ChessMoveDTO {
    private final ChessGameScoresDTO chessGameScoresDTO;
    private final Progress progress;
    private final Team turn;

    public ChessMoveDTO(ChessGameScoresDTO chessGameScoresDTO, Progress progress, Team turn) {
        this.chessGameScoresDTO = chessGameScoresDTO;
        this.progress = progress;
        this.turn = turn;
    }
}
