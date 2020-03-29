package chess.domain.chessPiece.factory;

import chess.domain.Position;
import chess.domain.XAxis;
import chess.domain.YAxis;
import chess.domain.chessPiece.piece.*;
import chess.domain.chessPiece.team.BlackTeam;
import chess.domain.chessPiece.team.TeamStrategy;

import java.util.ArrayList;
import java.util.List;

public class PieceBundleFactory {
    public static List<Piece> createPieceSet(TeamStrategy teamStrategy) {
        List<Piece> pieceBundle = new ArrayList<>();
        YAxis pawnYAxis = YAxis.SEVEN;
        YAxis otherYAxis = YAxis.EIGHT;
        if (teamStrategy instanceof BlackTeam) {
            pawnYAxis = YAxis.TWO;
            otherYAxis = YAxis.ONE;
        }

        for (XAxis value : XAxis.values()) {
            pieceBundle.add(new Pawn(Position.of(value, pawnYAxis), teamStrategy));
        }
        pieceBundle.add(new Rook(Position.of(XAxis.H, otherYAxis), teamStrategy));
        pieceBundle.add(new Rook(Position.of(XAxis.A, otherYAxis), teamStrategy));
        pieceBundle.add(new Knight(Position.of(XAxis.B, otherYAxis), teamStrategy));
        pieceBundle.add(new Knight(Position.of(XAxis.G, otherYAxis), teamStrategy));
        pieceBundle.add(new Bishop(Position.of(XAxis.C, otherYAxis), teamStrategy));
        pieceBundle.add(new Bishop(Position.of(XAxis.F, otherYAxis), teamStrategy));
        pieceBundle.add(new King(Position.of(XAxis.E, otherYAxis), teamStrategy));
        pieceBundle.add(new Queen(Position.of(XAxis.D, otherYAxis), teamStrategy));
        return pieceBundle;
    }
}
