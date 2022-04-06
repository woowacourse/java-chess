package chess.dto;

import chess.domain.game.ChessGame;
import chess.domain.piece.PieceColor;

public class CurrentTurnDto {

    private final Turn turn;

    private CurrentTurnDto(Turn turn) {
        this.turn = turn;
    }

    public static CurrentTurnDto from(ChessGame chessGame) {
        if (chessGame.isWhiteTurn()) {
            return new CurrentTurnDto(Turn.WHITE);
        }

        return new CurrentTurnDto(Turn.BLACK);
    }

    public static CurrentTurnDto from(String turn) {
        return new CurrentTurnDto(Turn.valueOf(turn));
    }

    public String getDisplayName() {
        return turn.displayName;
    }

    public PieceColor toPieceColor() {
        System.out.println("turn : " + turn);
        return PieceColor.valueOf(turn.name());
    }

    enum Turn {
        WHITE("백"),
        BLACK("흑");

        private final String displayName;

        Turn(String displayName) {
            this.displayName = displayName;
        }
    }
}

