package chess.view;

import chess.domain.piece.Piece;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;

import java.util.Arrays;

public enum Character {
    BLACK_PAWN(Team.BLACK, Kind.PAWN, "P"),
    BLACK_KNIGHT(Team.BLACK, Kind.KNIGHT, "N"),
    BLACK_BISHOP(Team.BLACK, Kind.BISHOP, "B"),
    BLACK_ROOK(Team.BLACK, Kind.ROOK, "R"),
    BLACK_QUEEN(Team.BLACK, Kind.QUEEN, "Q"),
    BLACK_KING(Team.BLACK, Kind.KING, "K"),
    WHITE_PAWN(Team.WHITE, Kind.PAWN, "p"),
    WHITE_KNIGHT(Team.WHITE, Kind.KNIGHT, "n"),
    WHITE_BISHOP(Team.WHITE, Kind.BISHOP, "b"),
    WHITE_ROOK(Team.WHITE, Kind.ROOK, "r"),
    WHITE_QUEEN(Team.WHITE, Kind.QUEEN, "q"),
    WHITE_KING(Team.WHITE, Kind.KING, "k"),
    ;

    private final Team team;
    private final Kind kind;

    private final String message;

    Character(Team team, Kind kind, String message) {
        this.team = team;
        this.kind = kind;
        this.message = message;
    }

    public static Character findCharacter(Piece piece) {
        return Arrays.stream(values())
                .filter(character -> piece.isSameTeamWith(character.team)
                        && piece.checkKind(character.kind))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 기물의 Character가 존재하지 않습니다."));
    }

    public String getMessage() {
        return message;
    }
}
