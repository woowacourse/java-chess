package chess.domain.board;

import chess.domain.Position;
import chess.domain.Team;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;

public enum EmptySettings {

    RANK3_FILE1(new Position(3, 1), new Empty(Team.EMPTY)),
    RANK3_FILE2(new Position(3, 2), new Empty(Team.EMPTY)),
    RANK3_FILE3(new Position(3, 3), new Empty(Team.EMPTY)),
    RANK3_FILE4(new Position(3, 4), new Empty(Team.EMPTY)),
    RANK3_FILE5(new Position(3, 5), new Empty(Team.EMPTY)),
    RANK3_FILE6(new Position(3, 6), new Empty(Team.EMPTY)),
    RANK3_FILE7(new Position(3, 7), new Empty(Team.EMPTY)),
    RANK3_FILE8(new Position(3, 8), new Empty(Team.EMPTY)),
    RANK4_FILE1(new Position(4, 1), new Empty(Team.EMPTY)),
    RANK4_FILE2(new Position(4, 2), new Empty(Team.EMPTY)),
    RANK4_FILE3(new Position(4, 3), new Empty(Team.EMPTY)),
    RANK4_FILE4(new Position(4, 4), new Empty(Team.EMPTY)),
    RANK4_FILE5(new Position(4, 5), new Empty(Team.EMPTY)),
    RANK4_FILE6(new Position(4, 6), new Empty(Team.EMPTY)),
    RANK4_FILE7(new Position(4, 7), new Empty(Team.EMPTY)),
    RANK4_FILE8(new Position(4, 8), new Empty(Team.EMPTY)),
    RANK5_FILE1(new Position(5, 1), new Empty(Team.EMPTY)),
    RANK5_FILE2(new Position(5, 2), new Empty(Team.EMPTY)),
    RANK5_FILE3(new Position(5, 3), new Empty(Team.EMPTY)),
    RANK5_FILE4(new Position(5, 4), new Empty(Team.EMPTY)),
    RANK5_FILE5(new Position(5, 5), new Empty(Team.EMPTY)),
    RANK5_FILE6(new Position(5, 6), new Empty(Team.EMPTY)),
    RANK5_FILE7(new Position(5, 7), new Empty(Team.EMPTY)),
    RANK5_FILE8(new Position(5, 8), new Empty(Team.EMPTY)),
    RANK6_FILE1(new Position(6, 1), new Empty(Team.EMPTY)),
    RANK6_FILE2(new Position(6, 2), new Empty(Team.EMPTY)),
    RANK6_FILE3(new Position(6, 3), new Empty(Team.EMPTY)),
    RANK6_FILE4(new Position(6, 4), new Empty(Team.EMPTY)),
    RANK6_FILE5(new Position(6, 5), new Empty(Team.EMPTY)),
    RANK6_FILE6(new Position(6, 6), new Empty(Team.EMPTY)),
    RANK6_FILE7(new Position(6, 7), new Empty(Team.EMPTY)),
    RANK6_FILE8(new Position(6, 8), new Empty(Team.EMPTY));

    private final Position position;
    private final Piece piece;

    EmptySettings(Position position, Piece piece) {
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
