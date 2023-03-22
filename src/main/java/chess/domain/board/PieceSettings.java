package chess.domain.board;

import chess.domain.Position;
import chess.domain.Team;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

public enum PieceSettings {

    ONE_WHITE(new Position(1, 1), new Rook(Team.WHITE)),
    TWO_WHITE(new Position(1, 2), new Knight(Team.WHITE)),
    THREE_WHITE(new Position(1, 3), new Bishop(Team.WHITE)),
    FOUR_WHITE(new Position(1, 4), new Queen(Team.WHITE)),
    FIVE_WHITE(new Position(1, 5), new King(Team.WHITE)),
    SIX_WHITE(new Position(1, 6), new Bishop(Team.WHITE)),
    SEVEN_WHITE(new Position(1, 7), new Knight(Team.WHITE)),
    EIGHT_WHITE(new Position(1, 8), new Rook(Team.WHITE)),
    NINE_WHITE(new Position(2, 1), new Pawn(Team.WHITE)),
    TEN_WHITE(new Position(2, 2), new Pawn(Team.WHITE)),
    ELEVEN_WHITE(new Position(2, 3), new Pawn(Team.WHITE)),
    TWELVE_WHITE(new Position(2, 4), new Pawn(Team.WHITE)),
    THIRTEEN_WHITE(new Position(2, 5), new Pawn(Team.WHITE)),
    FOURTEEN_WHITE(new Position(2, 6), new Pawn(Team.WHITE)),
    FIFTEEN_WHITE(new Position(2, 7), new Pawn(Team.WHITE)),
    SIXTEEN_WHITE(new Position(2, 8), new Pawn(Team.WHITE)),
    ONE_BLACK(new Position(8, 1), new Rook(Team.BLACK)),
    TWO_BLACK(new Position(8, 2), new Knight(Team.BLACK)),
    THREE_BLACK(new Position(8, 3), new Bishop(Team.BLACK)),
    FOUR_BLACK(new Position(8, 4), new Queen(Team.BLACK)),
    FIVE_BLACK(new Position(8, 5), new King(Team.BLACK)),
    SIX_BLACK(new Position(8, 6), new Bishop(Team.BLACK)),
    SEVEN_BLACK(new Position(8, 7), new Knight(Team.BLACK)),
    EIGHT_BLACK(new Position(8, 8), new Rook(Team.BLACK)),
    NINE_BLACK(new Position(7, 1), new Pawn(Team.BLACK)),
    TEN_BLACK(new Position(7, 2), new Pawn(Team.BLACK)),
    ELEVEN_BLACK(new Position(7, 3), new Pawn(Team.BLACK)),
    TWELVE_BLACK(new Position(7, 4), new Pawn(Team.BLACK)),
    THIRTEEN_BLACK(new Position(7, 5), new Pawn(Team.BLACK)),
    FOURTEEN_BLACK(new Position(7, 6), new Pawn(Team.BLACK)),
    FIFTEEN_BLACK(new Position(7, 7), new Pawn(Team.BLACK)),
    SIXTEEN_BLACK(new Position(7, 8), new Pawn(Team.BLACK));

    private final Position position;
    private final Piece piece;

    PieceSettings(Position position, Piece piece) {
        this.position = position;
        this.piece = piece;
    }

    public Position getPosition() {
        return position;
    }

    public Piece getPiece() {
        return piece;
    }
}
