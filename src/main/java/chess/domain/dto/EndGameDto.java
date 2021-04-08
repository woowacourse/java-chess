package chess.domain.dto;

import chess.domain.game.ChessGame;

public class EndGameDto {

    private String isOk;
    private String message;

    public EndGameDto(String message) {
        this.isOk = "end";
        this.message = message;
    }

    public EndGameDto(ChessGame chessGame) {
        this.isOk = "end";
        this.message = result(chessGame);
        ;
    }

    private String result(ChessGame chessGame) {
        if (chessGame.getBlackScore() > chessGame.getWhiteScore()) {
            return "블랙팀 : " + chessGame.getBlackScore() + " 화이트팀 : " + chessGame.getWhiteScore() + "\n" + "블랙팀 승 입니다.";
        }
        if (chessGame.getBlackScore() < chessGame.getWhiteScore()) {
            return "블랙팀 : " + chessGame.getBlackScore() + " 화이트팀 : " + chessGame.getWhiteScore() + "\n" + "화이트 승 입니다.";
        }

        return "블랙팀 : " + chessGame.getBlackScore() + " 화이트팀 : " + chessGame.getWhiteScore() + "\n" + "무승부 입니다.";
    }

    public String getIsOk() {
        return isOk;
    }

    public String getMessage() {
        return message;
    }

}
