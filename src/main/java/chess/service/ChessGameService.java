package chess.service;

import chess.chessgame.ChessGame;
import chess.dto.ResultDto;

public class ChessGameService {

    public boolean isGameOver(final ChessGame chessGame) {
        return chessGame.isGameOver();
    }

    public ResultDto getResult(final ChessGame chessGame) {
        return new ResultDto(chessGame.getWinner());
    }
}
