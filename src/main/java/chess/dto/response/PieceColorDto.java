package chess.dto.response;

import chess.domain.game.ChessGame;
import chess.domain.piece.PieceColor;

public class PieceColorDto {

    private final Turn turn;

    private PieceColorDto(Turn turn) {
        this.turn = turn;
    }

    public static PieceColorDto from(ChessGame chessGame) {
        if (chessGame.isWhiteTurn()) {
            return new PieceColorDto(Turn.WHITE);
        }

        return new PieceColorDto(Turn.BLACK);
    }

    public static PieceColorDto from(String turn) {
        return new PieceColorDto(Turn.valueOf(turn));
    }

    public static PieceColorDto from(PieceColor pieceColor) {
        return new PieceColorDto(Turn.valueOf(pieceColor.name()));
    }

    public String getDisplayName() {
        return turn.displayName;
    }

    public PieceColor toPieceColor() {
        System.out.println("turn : " + turn);
        return PieceColor.valueOf(turn.name());
    }

    public boolean isWhiteTurn() {
        return turn.equals(Turn.WHITE);
    }


    // TODO: 뷰로 분리
    enum Turn {
        WHITE("백"),
        BLACK("흑");

        private final String displayName;

        Turn(String displayName) {
            this.displayName = displayName;
        }
    }
}

