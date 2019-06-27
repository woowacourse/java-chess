package chess.domain.chesspiece;

import java.util.Arrays;
import java.util.List;

public class ChessPieces {
    private static ChessPieces chessPieces = null;
    private List<ChessPiece> pieces = Arrays.asList(Bishop.getInstance(), King.getInstance(), Knight.getInstance()
            , Queen.getInstance(), Rook.getInstance(), BlackPawn.getInstance(), WhitePawn.getInstance());

    private ChessPieces() {
    }

    public static ChessPieces getInstance() {
        if (chessPieces == null) {
            chessPieces = new ChessPieces();
        }
        return chessPieces;
    }

    public ChessPiece find(String name, boolean isWhiteTeam) {
        BlackPawn pawn = BlackPawn.getInstance();
        if (pawn.hasName(name)) {
            return isWhiteTeam ? WhitePawn.getInstance() : BlackPawn.getInstance();
        }

        return pieces.stream()
                .filter(chessPiece -> chessPiece.hasName(name))
                .findFirst()
                .orElseThrow(InvalidChessPieceNameException::new)
                ;
    }
}
