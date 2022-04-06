package chess.dao;

import chess.domain.piece.Bishop;
import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import java.util.Arrays;
import java.util.function.Function;

public enum StringToPieceConvertor {

    KING("KING", teamValue -> new King(Team.of(teamValue))),
    QUEEN("QUEEN", teamValue -> new Queen(Team.of(teamValue))),
    KNIGHT("KNIGHT", teamValue -> new Knight(Team.of(teamValue))),
    BISHOP("BISHOP", teamValue -> new Bishop(Team.of(teamValue))),
    ROOK("ROOK", teamValue -> new Rook(Team.of(teamValue))),
    PAWN("PAWN", teamValue -> new Pawn(Team.of(teamValue))),
    BLANK("BLANK", teamValue -> new Blank());

    private final String name;
    private final Function<String, Piece> convertPiece;

    StringToPieceConvertor(final String name, final Function<String, Piece> convertPiece) {
        this.name = name;
        this.convertPiece = convertPiece;
    }

    public static Piece convert(final String name, String teamValue) {
        return Arrays.stream(values())
                .filter(it -> it.name.equals(name))
                .map(it -> it.convertPiece.apply(teamValue))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 Piece 입니다."));
    }
}
