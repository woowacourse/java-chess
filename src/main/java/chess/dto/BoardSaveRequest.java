package chess.dto;

import chess.domain.board.Position;
import chess.domain.piece.Piece;

public class BoardSaveRequest {

    private final Position position;
    private final Piece piece;
    private final int gameNumber;

    public BoardSaveRequest(Position position, Piece piece, int gameNumber) {
        this.position = position;
        this.piece = piece;
        this.gameNumber = gameNumber;
    }

    public Position getPosition() {
        return position;
    }

    public Piece getPiece() {
        return piece;
    }

    public int getGameNumber() {
        return gameNumber;
    }
}
