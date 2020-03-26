package chess.domain.chessPiece.factory;

import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.chessPiece.piece.*;
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
            pieceBundle.add(new Pawn(Position.of(value, pawnRank), teamStrategy));
        }
        pieceBundle.add(new Rook(Position.of(File.H, otherRank), teamStrategy));
        pieceBundle.add(new Rook(Position.of(File.A, otherRank), teamStrategy));
        pieceBundle.add(new Knight(Position.of(File.B, otherRank), teamStrategy));
        pieceBundle.add(new Knight(Position.of(File.G, otherRank), teamStrategy));
        pieceBundle.add(new Bishop(Position.of(File.C, otherRank), teamStrategy));
        pieceBundle.add(new Bishop(Position.of(File.F, otherRank), teamStrategy));
        pieceBundle.add(new King(Position.of(File.E, otherRank), teamStrategy));
        pieceBundle.add(new Queen(Position.of(File.D, otherRank), teamStrategy));
        return pieceBundle;
    }
}
