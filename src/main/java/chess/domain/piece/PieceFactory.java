package chess.domain.piece;

import java.util.Arrays;
import java.util.function.Function;

public enum PieceFactory {

    PAWN(Name.PAWN, team -> new Pawn(team)),
    QUEEN(Name.QUEEN, team -> new Queen(team)),
    KING(Name.KING, team -> new King(team)),
    BISHOP(Name.BISHOP, team -> new Bishop(team)),
    ROOK(Name.ROOK, team -> new Rook(team)),
    KNIGHT(Name.KNIGHT, team -> new Knight(team)),
    EMPTY(Name.NONE, team -> new EmptyPiece());


    private final Name name;
    private final Function<Team, Piece> function;

    PieceFactory(Name name, Function<Team, Piece> function) {
        this.name = name;
        this.function = function;
    }


    public static Piece of(String teamName, String PieceType) {
        Team team = Team.of(teamName);
        Name name = Name.of(PieceType);

        PieceFactory pieceFactory = Arrays.stream(PieceFactory.values())
                .filter(piece -> piece.name == name)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("없는 기물입니다."));

        return pieceFactory.function.apply(team);
    }
}
