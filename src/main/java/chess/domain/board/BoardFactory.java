package chess.domain.board;

import chess.domain.pieces.*;
import chess.domain.pieces.component.Team;
import chess.domain.pieces.component.Type;

import java.util.HashMap;
import java.util.Map;

public class BoardFactory {

    public static Map<Position, Piece> create() {
        Map<Position, Piece> init = new HashMap<>();
        init.put(new Position(Rank.ONE, File.A), new Rook(Team.WHITE, Type.ROOK));
        init.put(new Position(Rank.ONE, File.B), new Knight(Team.WHITE, Type.KNIGHT));
        init.put(new Position(Rank.ONE, File.C), new Bishop(Team.WHITE, Type.BISHOP));
        init.put(new Position(Rank.ONE, File.D), new Queen(Team.WHITE, Type.QUEEN));
        init.put(new Position(Rank.ONE, File.E), new King(Team.WHITE, Type.KING));
        init.put(new Position(Rank.ONE, File.F), new Bishop(Team.WHITE, Type.BISHOP));
        init.put(new Position(Rank.ONE, File.G), new Knight(Team.WHITE, Type.KNIGHT));
        init.put(new Position(Rank.ONE, File.H), new Rook(Team.WHITE, Type.ROOK));

        for (File file : File.values()) {
            init.put(new Position(Rank.TWO, file), new WhitePawn(Type.PAWN));
        }

        for (int i = 3; i < 7; i++) {
            for (File file : File.values()) {
                init.put(new Position(Rank.of(i), file), new EmptyPiece(Team.NEUTRALITY, Type.NO_PIECE));
            }
        }

        for (File file : File.values()) {
            init.put(new Position(Rank.SEVEN, file), new BlackPawn(Type.PAWN));
        }

        init.put(new Position(Rank.EIGHT, File.A), new Rook(Team.BLACK, Type.ROOK));
        init.put(new Position(Rank.EIGHT, File.B), new Knight(Team.BLACK, Type.KNIGHT));
        init.put(new Position(Rank.EIGHT, File.C), new Bishop(Team.BLACK, Type.BISHOP));
        init.put(new Position(Rank.EIGHT, File.D), new Queen(Team.BLACK, Type.QUEEN));
        init.put(new Position(Rank.EIGHT, File.E), new King(Team.BLACK, Type.KING));
        init.put(new Position(Rank.EIGHT, File.F), new Bishop(Team.BLACK, Type.BISHOP));
        init.put(new Position(Rank.EIGHT, File.G), new Knight(Team.BLACK, Type.KNIGHT));
        init.put(new Position(Rank.EIGHT, File.H), new Rook(Team.BLACK, Type.ROOK));

        return init;
    }
}



