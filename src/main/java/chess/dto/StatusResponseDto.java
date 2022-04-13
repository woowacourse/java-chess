package chess.dto;

import chess.domain.game.ChessGame;

public class StatusResponseDto {

    private final String status;
    private final String myTurn;
    private final String opponentTurn;
    private final double myScore;
    private final double opponentScore;
    private final String result;

    public StatusResponseDto(final ChessGame chessGame, final double myScore, final double opponentScore,
                             final String result) {
        this.status = "success";
        this.myTurn = chessGame.getTurn().getName();
        this.opponentTurn = chessGame.getTurn().getOpposite().getName();
        this.myScore = myScore;
        this.opponentScore = opponentScore;
        this.result = result;
    }
}
