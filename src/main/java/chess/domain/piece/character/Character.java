package chess.domain.piece.character;

import java.util.Arrays;

public enum Character {
    BLACK_PAWN(Team.BLACK, Kind.PAWN),
    BLACK_KNIGHT(Team.BLACK, Kind.KNIGHT),
    BLACK_BISHOP(Team.BLACK, Kind.BISHOP),
    BLACK_ROOK(Team.BLACK, Kind.ROOK),
    BLACK_QUEEN(Team.BLACK, Kind.QUEEN),
    BLACK_KING(Team.BLACK, Kind.KING),
    WHITE_PAWN(Team.WHITE, Kind.PAWN),
    WHITE_KNIGHT(Team.WHITE, Kind.KNIGHT),
    WHITE_BISHOP(Team.WHITE, Kind.BISHOP),
    WHITE_ROOK(Team.WHITE, Kind.ROOK),
    WHITE_QUEEN(Team.WHITE, Kind.QUEEN),
    WHITE_KING(Team.WHITE, Kind.KING),
    ;

    private final Team team;
    private final Kind kind;

    Character(Team team, Kind kind) {
        this.team = team;
        this.kind = kind;
    }

    public static Character findCharacter(Team team, Kind kind) {
        return Arrays.stream(values())
                .filter(character -> character.team == team && character.kind == kind)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("%s, %s의 Character가 존재하지 않습니다."
                        .formatted(team.name(), kind.name())));
    }
}
