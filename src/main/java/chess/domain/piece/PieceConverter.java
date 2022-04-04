package chess.domain.piece;

import chess.domain.Team;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

public enum PieceConverter{
    BISHOP("bishop", Bishop::new),
    BLANK("blank", Blank::new),
    KING("king", King::new),
    KNIGHT("knight", Knight::new),
    PAWN("pawn", Pawn::new),
    QUEEN("queen", Queen::new),
    ROOK("rook", Rook::new);

    private String name;
    private Function<Team, Piece> convertFunction;

    PieceConverter(String name, Function<Team, Piece> convertFunction) {
        this.name = name;
        this.convertFunction = convertFunction;
    }

    public static Piece convert(String name, String team) {
        PieceConverter pieceConverter = Arrays.stream(values())
                .filter(piece -> piece.name.equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 잘못된 말 이름입니다."));

        return pieceConverter.convertFunction
                .apply(Team.find(name));
    }

}
