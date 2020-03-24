package chess.domain.chessPiece.factory;

import chess.domain.chessPiece.*;
import chess.domain.chessPiece.team.BlackTeam;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.chessPiece.team.WhiteTeam;

import java.util.HashMap;
import java.util.Map;

public class PieceFactory {
    private final static Map<String, Piece> piecesBlack = new HashMap<>();
    private final static Map<String, Piece> piecesWhite = new HashMap<>();

    static {
        TeamStrategy blackTeam = new BlackTeam();
        TeamStrategy whiteTeam = new WhiteTeam();

        piecesBlack.put("pawn", new Pawn(blackTeam));
        piecesBlack.put("queen", new Queen(blackTeam));
        piecesBlack.put("knight", new Knight(blackTeam));
        piecesBlack.put("bishop", new Bishop(blackTeam));
        piecesBlack.put("king", new King(blackTeam));
        piecesBlack.put("rook", new Rook(blackTeam));

        piecesWhite.put("pawn", new Pawn(whiteTeam));
        piecesWhite.put("queen", new Queen(whiteTeam));
        piecesWhite.put("knight", new Knight(whiteTeam));
        piecesWhite.put("bishop", new Bishop(whiteTeam));
        piecesWhite.put("king", new King(whiteTeam));
        piecesWhite.put("rook", new Rook(whiteTeam));
    }

    public static Piece of(String name, TeamStrategy teamStrategy) {
        if (teamStrategy instanceof BlackTeam) {
            return piecesBlack.get(name);
        }
        return piecesWhite.get(name);
    }
}
