package chess.dto;

import chess.domain.game.ChessGame;

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

    public String getDisplayName() {
        return turn.displayName;
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

