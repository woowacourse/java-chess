package chess.dao;

import chess.domain.Team;
import chess.domain.piece.*;

import java.util.Arrays;
import java.util.function.Function;

public enum PieceMapper {
    BISHOP(Bishop.NAME, Bishop::new),
    BLANK(Blank.NAME, Blank::new),
    KING(King.NAME, King::new),
    KNIGHT(Knight.NAME, Knight::new),
    PAWN(Pawn.NAME, Pawn::new),
    QUEEN(Queen.NAME, Queen::new),
    ROOK(Rook.NAME, Rook::new);

    private String name;
    private Function<Team, Piece> convertFunction;

    PieceMapper(String name, Function<Team, Piece> convertFunction) {
        this.name = name;
        this.convertFunction = convertFunction;
    }

    public static Piece convert(String rawPiece) {
        String[] splitPiece = rawPiece.split("_");
        if (splitPiece.length == 1) {
            return new Blank();
        }

        PieceMapper pieceConverter = getPieceConverter(splitPiece);

        return pieceConverter.convertFunction
                .apply(Team.find(splitPiece[0]));
    }

    private static PieceMapper getPieceConverter(String[] splitPiece) {
        PieceMapper pieceConverter = Arrays.stream(values())
                .filter(piece -> piece.name.equals(splitPiece[1]))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 잘못된 말 이름입니다."));
        return pieceConverter;
    }
}
