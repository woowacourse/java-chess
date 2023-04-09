package chess.dto.dtomapper;

import chess.domain.chessgame.PlayerScore;
import chess.domain.piece.Color;
import chess.dto.ScoreDto;
import chess.dto.WinnerDto;
import chess.view.OutputView;

public class ResultMapper {

    public ScoreDto scoreToDto(PlayerScore whiteScore, PlayerScore blackScore) {
        return new ScoreDto(whiteScore.getPlayerScore(), blackScore.getPlayerScore());
    }

    public WinnerDto winnerToDto(final Color winner) {
        if (winner == Color.WHITE) {
            return new WinnerDto(OutputView.WHITE_COLOR);
        }
        return new WinnerDto(OutputView.BLACK_COLOR);
    }
}
