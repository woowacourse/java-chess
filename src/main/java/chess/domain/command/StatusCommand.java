package chess.domain.command;

import chess.domain.Game;
import chess.domain.piece.PieceColor;
import chess.domain.result.Result;
import chess.domain.result.Score;

public class StatusCommand extends BasicCommand {

    Result result;

    public StatusCommand(final Game game) {
        super(game);
        result = game.getResult();
    }

    public Score getWhiteScore() {
        return result.calculateTotalScore(PieceColor.WHITE);
    }

    public Score getBlackScore() {
        return result.calculateTotalScore(PieceColor.BLACK);
    }

    public PieceColor getPrevailPlayer() {
        if (getWhiteScore().isTieWith(getBlackScore())) {
            return PieceColor.NOTHING;
        }
        if (getWhiteScore().moreThan(getBlackScore())) {
            return PieceColor.WHITE;
        }
        return PieceColor.BLACK;
    }

    @Override
    public void execute(String input) {
        if (!game.isRunning()) {
            throw new IllegalArgumentException("아직 게임을 시작하지 않았습니다.");
        }
    }

    @Override
    public boolean isStatus() {
        return true;
    }
}
