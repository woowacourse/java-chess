package chess.dto;

import chess.domain.game.ChessGame;

public class StatusResponseDto {

    private final String code;
    private final String message;
    private final String myTurn;
    private final String opponentTurn;
    private final double myScore;
    private final double opponentScore;
    private final String result;

    public StatusResponseDto(final ChessGame chessGame, final double myScore, final double opponentScore,
                             final String result) {
        this("success", "", chessGame, myScore, opponentScore, result);
    }

    public StatusResponseDto(final String code, final String message, final ChessGame chessGame) {
        this(code, message, chessGame, 0, 0, "");
    }

    public StatusResponseDto(final String code, final String message, final ChessGame chessGame, final double myScore,
                             final double opponentScore,
                             final String result) {
        this.code = code;
        this.message = message;
        this.myTurn = chessGame.getTurn().getName();
        this.opponentTurn = chessGame.getTurn().getOpposite().getName();
        this.myScore = myScore;
        this.opponentScore = opponentScore;
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getMyTurn() {
        return myTurn;
    }

    public String getOpponentTurn() {
        return opponentTurn;
    }

    public double getMyScore() {
        return myScore;
    }

    public double getOpponentScore() {
        return opponentScore;
    }

    public String getResult() {
        return result;
    }
}
