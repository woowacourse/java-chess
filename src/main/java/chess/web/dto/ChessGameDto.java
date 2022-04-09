package chess.web.dto;

import chess.domain.ChessBoard;
import chess.domain.ChessGame;
import chess.domain.state.State;

public class ChessGameDto {

    private final ChessBoardDto chessBoard;
    private final String gameName;
    private String turn;

    private ChessGameDto(ChessBoardDto chessBoardDto, String gameName, String turn) {
        this.chessBoard = chessBoardDto;
        this.gameName = gameName;
        this.turn = turn;
    }

    public static ChessGameDto from(ChessGame chessGame) {
        ChessBoard chessBoard = chessGame.getChessBoard();
        ChessBoardDto chessBoardDto = ChessBoardDto.from(chessBoard.getCells());

        String gameName = chessGame.getGameName();

        State state = chessGame.getState();
        String turn = state.getTurn();

        return new ChessGameDto(chessBoardDto, gameName, turn);
    }

    public String getGameName() {
        return this.gameName;
    }

    public String getTurn() {
        return turn;
    }

    public ChessBoardDto getChessBoard() {
        return chessBoard;
    }
}
