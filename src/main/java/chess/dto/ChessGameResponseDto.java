package chess.dto;

import chess.domain.board.Board;
import chess.domain.game.ChessGame;

public class ChessGameResponseDto {

    private final ChessGame chessGame;

    private ChessGameResponseDto(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public static ChessGameResponseDto toDto(final Board board, final boolean isLowerTeamTurn) {
        return new ChessGameResponseDto(new ChessGame(board, isLowerTeamTurn));
    }

    public ChessGame getChessGame() {
        return chessGame;
    }
}
