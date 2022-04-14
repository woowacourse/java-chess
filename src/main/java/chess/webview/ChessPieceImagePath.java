package chess.webview;

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

public enum ChessPieceImagePath {
    BLACK_PAWN((chessPiece) -> chessPiece instanceof Pawn && chessPiece.isSameTeam(Team.BLACK), "/images/Black_Pawn.png"),
    BLACK_KNIGHT((chessPiece) -> chessPiece instanceof Knight && chessPiece.isSameTeam(Team.BLACK), "/images/Black_Knight.png"),
    BLACK_BISHOP((chessPiece) -> chessPiece instanceof Bishop && chessPiece.isSameTeam(Team.BLACK), "/images/Black_Bishop.png"),
    BLACK_ROOK((chessPiece) -> chessPiece instanceof Rook && chessPiece.isSameTeam(Team.BLACK), "/images/Black_Rook.png"),
    BLACK_QUEEN((chessPiece) -> chessPiece instanceof Queen && chessPiece.isSameTeam(Team.BLACK), "/images/Black_Queen.png"),
    BLACK_KING((chessPiece) -> chessPiece instanceof King && chessPiece.isSameTeam(Team.BLACK), "/images/Black_King.png"),

    WHITE_PAWN((chessPiece) -> chessPiece instanceof Pawn && chessPiece.isSameTeam(Team.WHITE), "/images/White_Pawn.png"),
    WHITE_KNIGHT((chessPiece) -> chessPiece instanceof Knight && chessPiece.isSameTeam(Team.WHITE), "/images/White_Knight.png"),
    WHITE_BISHOP((chessPiece) -> chessPiece instanceof Bishop && chessPiece.isSameTeam(Team.WHITE), "/images/White_Bishop.png"),
    WHITE_ROOK((chessPiece) -> chessPiece instanceof Rook && chessPiece.isSameTeam(Team.WHITE), "/images/White_Rook.png"),
    WHITE_QUEEN((chessPiece) -> chessPiece instanceof Queen && chessPiece.isSameTeam(Team.WHITE), "/images/White_Queen.png"),
    WHITE_KING((chessPiece) -> chessPiece instanceof King && chessPiece.isSameTeam(Team.WHITE), "/images/White_King.png");

    private final Predicate<ChessPiece> condition;
    private final String imagePath;

    ChessPieceImagePath(Predicate<ChessPiece> condition, String imagePath) {
        this.condition = condition;
        this.imagePath = imagePath;
    }

    public static String of(ChessPiece chessPiece) {
        return Arrays.stream(ChessPieceImagePath.values())
                .filter(it -> it.condition.test(chessPiece))
                .map(it -> it.imagePath)
                .findFirst()
                .orElseThrow();
    }
}
