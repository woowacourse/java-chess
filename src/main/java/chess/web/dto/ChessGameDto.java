package chess.web.dto;

import chess.domain.ChessBoard;
import chess.domain.ChessGame;
import chess.domain.state.State;
import java.util.Map;

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

    public ChessGameDto(String turn, String gameName, Map<PositionDto, PieceDto> cells) {
        this.chessBoard = new ChessBoardDto(cells);
        this.gameName = gameName;
        this.state = State.getState(turn);
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
