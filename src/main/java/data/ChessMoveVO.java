package data;

import chess.progress.Progress;

public class ChessMoveVO {
    private final ChessGameScoresVO chessGameScoresVO;
    private final Progress progress;

    public ChessMoveVO(ChessGameScoresVO chessGameScoresVO, Progress progress) {
        this.chessGameScoresVO = chessGameScoresVO;
        this.progress = progress;
    }

    public ChessGameScoresVO getChessGameScoresVO() {
        return chessGameScoresVO;
    }

    public Progress getProgress() {
        return progress;
    }
}
