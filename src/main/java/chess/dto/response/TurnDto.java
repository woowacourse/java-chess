package chess.dto.response;

import chess.domain.game.ChessGame;
import chess.domain.piece.PieceColor;

public class TurnDto {

    private final Turn turn;

    private TurnDto(Turn turn) {
        this.turn = turn;
    }

    public static TurnDto from(ChessGame chessGame) {
        if (chessGame.isWhiteTurn()) {
            return new TurnDto(Turn.WHITE);
        }

        return new TurnDto(Turn.BLACK);
    }

    public static TurnDto from(String turn) {
        return new TurnDto(Turn.valueOf(turn));
    }

    public static TurnDto from(PieceColor pieceColor) {
        return new TurnDto(Turn.valueOf(pieceColor.name()));
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

