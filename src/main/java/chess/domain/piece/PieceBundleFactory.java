package chess.domain.piece;

import chess.domain.piece.position.Position;
import chess.domain.piece.position.XPosition;
import chess.domain.piece.position.YPosition;
import chess.domain.piece.team.TeamStrategy;

import java.util.ArrayList;
import java.util.List;

public class PieceBundleFactory {
    public static List<Piece> createPieceSet(TeamStrategy teamStrategy) {
        List<Piece> pieceBundle = new ArrayList<>();
        YPosition pawnYPosition = YPosition.SEVEN;
        YPosition otherYPosition = YPosition.EIGHT;
        if (teamStrategy.isBlackTeam()) {
            pawnYPosition = YPosition.TWO;
            otherYPosition = YPosition.ONE;
        }

        for (XPosition value : XPosition.values()) {
            pieceBundle.add(new Pawn(Position.of(value, pawnYPosition), teamStrategy));
        }
        pieceBundle.add(new Rook(Position.of(XPosition.H, otherYPosition), teamStrategy));
        pieceBundle.add(new Rook(Position.of(XPosition.A, otherYPosition), teamStrategy));
        pieceBundle.add(new Knight(Position.of(XPosition.B, otherYPosition), teamStrategy));
        pieceBundle.add(new Knight(Position.of(XPosition.G, otherYPosition), teamStrategy));
        pieceBundle.add(new Bishop(Position.of(XPosition.C, otherYPosition), teamStrategy));
        pieceBundle.add(new Bishop(Position.of(XPosition.F, otherYPosition), teamStrategy));
        pieceBundle.add(new King(Position.of(XPosition.E, otherYPosition), teamStrategy));
        pieceBundle.add(new Queen(Position.of(XPosition.D, otherYPosition), teamStrategy));
        return pieceBundle;
    }
}
