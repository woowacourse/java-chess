package chess.domain.chessPiece.factory;

import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.chessPiece.*;
import chess.domain.chessPiece.team.BlackTeam;
import chess.domain.chessPiece.team.TeamStrategy;

import java.util.ArrayList;
import java.util.List;

public class PieceBundleFactory {
    public static List<Piece> createPieceSet(TeamStrategy teamStrategy) {
        List<Piece> pieceBundle = new ArrayList<>();
        Rank pawnRank = Rank.SEVEN;
        Rank otherRank = Rank.EIGHT;
        if (teamStrategy instanceof BlackTeam) {
            pawnRank = Rank.TWO;
            otherRank = Rank.ONE;
        }

        for (File value : File.values()) {
            pieceBundle.add(new Pawn(new Position(value, pawnRank), teamStrategy));
        }
        pieceBundle.add(new Rook(new Position(File.H, otherRank), teamStrategy));
        pieceBundle.add(new Rook(new Position(File.A, otherRank), teamStrategy));
        pieceBundle.add(new Knight(new Position(File.B, otherRank), teamStrategy));
        pieceBundle.add(new Knight(new Position(File.G, otherRank), teamStrategy));
        pieceBundle.add(new Bishop(new Position(File.C, otherRank), teamStrategy));
        pieceBundle.add(new Bishop(new Position(File.F, otherRank), teamStrategy));
        pieceBundle.add(new King(new Position(File.E, otherRank), teamStrategy));
        pieceBundle.add(new Queen(new Position(File.D, otherRank), teamStrategy));
        return pieceBundle;

    }
}
