package chess.view;

import chess.domain.Team;
import chess.domain.piece.Bishop;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Arrays;
import java.util.function.Predicate;

public enum ChessPieceName {
    BLACK_PAWN((chessPiece) -> chessPiece instanceof Pawn && chessPiece.isSameTeam(Team.BLACK), "P"),
    BLACK_KNIGHT((chessPiece) -> chessPiece instanceof Knight && chessPiece.isSameTeam(Team.BLACK), "N"),
    BLACK_BISHOP((chessPiece) -> chessPiece instanceof Bishop && chessPiece.isSameTeam(Team.BLACK), "B"),
    BLACK_ROOK((chessPiece) -> chessPiece instanceof Rook && chessPiece.isSameTeam(Team.BLACK), "R"),
    BLACK_QUEEN((chessPiece) -> chessPiece instanceof Queen && chessPiece.isSameTeam(Team.BLACK), "Q"),
    BLACK_KING((chessPiece) -> chessPiece instanceof King && chessPiece.isSameTeam(Team.BLACK), "K"),

    WHITE_PAWN((chessPiece) -> chessPiece instanceof Pawn && chessPiece.isSameTeam(Team.WHITE), "p"),
    WHITE_KNIGHT((chessPiece) -> chessPiece instanceof Knight && chessPiece.isSameTeam(Team.WHITE), "n"),
    WHITE_BISHOP((chessPiece) -> chessPiece instanceof Bishop && chessPiece.isSameTeam(Team.WHITE), "b"),
    WHITE_ROOK((chessPiece) -> chessPiece instanceof Rook && chessPiece.isSameTeam(Team.WHITE), "r"),
    WHITE_QUEEN((chessPiece) -> chessPiece instanceof Queen && chessPiece.isSameTeam(Team.WHITE), "q"),
    WHITE_KING((chessPiece) -> chessPiece instanceof King && chessPiece.isSameTeam(Team.WHITE), "k");

    private final Predicate<ChessPiece> condition;
    private final String name;

    ChessPieceName(Predicate<ChessPiece> condition, String name) {
        this.condition = condition;
        this.name = name;
    }

    public static String of(ChessPiece chessPiece) {
        return Arrays.stream(ChessPieceName.values())
                .filter(it -> it.condition.test(chessPiece))
                .map(it -> it.name)
                .findFirst()
                .orElseThrow();
    }
}
