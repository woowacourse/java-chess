package chess.controller.state;

import chess.model.Scores;
import chess.model.board.ScoreCalculator;
import chess.model.piece.PieceColor;
import chess.model.position.Position;
import chess.service.ChessService;

public class Status extends ProgressState {

    public Status(final ChessService chessService) {
        super(chessService);
    }

    @Override
    public Scores calculateScores() {
        return chessService.calculateScoreAll(new ScoreCalculator());
    }

    @Override
    public void executeAndSave(final Position source, final Position target) {
        // 상태 정보를 표현할 때 사용할 수 없음
    }

    @Override
    public boolean hasGame() {
        throw new UnsupportedOperationException("지원하지 않는 기능입니다.");
    }

    @Override
    public PieceColor findCurrentPlayer() {
        throw new UnsupportedOperationException("지원하지 않는 기능입니다.");
    }

    @Override
    public boolean isStatus() {
        return true;
    }
}
