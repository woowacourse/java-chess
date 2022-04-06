package chess.web.dto;

import chess.domain.ChessBoard;
import chess.domain.ChessGame;
import chess.domain.state.State;

public class ChessGameDto {

    private final ChessBoardDto chessBoard;
    private final String gameName;
    private State state;

    public ChessGameDto(ChessGame chessGame) {
        this.gameName = chessGame.getGameName();
        ChessBoard chessBoard = chessGame.getChessBoard();
        this.chessBoard = ChessBoardDto.from(chessBoard.getCells());
        this.state = chessGame.getState();
    }

    public String getGameName() {
        return this.gameName;
    }

    public String getTurn() {
        return state.getTurn();
    }

    public ChessBoardDto getChessBoard() {
        return chessBoard;
    }
}
