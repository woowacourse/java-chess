package chess.service;

import chess.chessboard.Side;
import chess.chessgame.ChessGame;
import chess.chessgame.PlayerScore;
import chess.controller.ScoreDto;
import chess.dto.ResultDto;

public class ChessGameService {

    public boolean isGameOver(final ChessGame chessGame) {
        return chessGame.isGameOver();
    }

    public ResultDto getResult(final ChessGame chessGame) {
        return new ResultDto(chessGame.getWinner());
    }

    public ScoreDto calculateScores(final ChessGame chessGame) {
        final PlayerScore whiteScore = chessGame.calculateScore(Side.WHITE);
        final PlayerScore blackScore = chessGame.calculateScore(Side.BLACK);

        return new ScoreDto(whiteScore, blackScore);
    }
}
