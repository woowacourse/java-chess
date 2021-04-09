package chess.domain.dto;

import chess.domain.game.ChessGame;

public class ChessBoardDto {

    private String isOk;
    private PiecesDto piecesDto;
    private String turn;
    private String status;
    private String roomID;

    public ChessBoardDto(ChessGame chessGame) {
        this.isOk = "ture";
        this.piecesDto = new PiecesDto(chessGame.getBoard().getPieces());
        this.turn = chessGame.getStatus();
        this.status = "블랙팀 : " + chessGame.getBlackScore() + " 화이트팀 : " + chessGame.getWhiteScore();
        this.roomID = chessGame.getRoomID();
    }

    public String getIsOk() {
        return isOk;
    }

    public PiecesDto getPiecesDto() {
        return piecesDto;
    }

    public String getTurn() {
        return turn;
    }

    public String getStatus() {
        return status;
    }

    public String getRoomID() {
        return roomID;
    }

}
