package chess.domain.board;

import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.Team;
import chess.domain.pieces.*;

import java.util.HashMap;
import java.util.Map;

public class BoardFactory {

    public static Map<Position, Piece> create() {
        Map<Position, Piece> init = new HashMap<>();
        init.put(new Position(Rank.ONE, File.A), new Rook(Team.WHITE));
        init.put(new Position(Rank.ONE, File.B), new Knight(Team.WHITE));
        init.put(new Position(Rank.ONE, File.C), new Bishop(Team.WHITE));
        init.put(new Position(Rank.ONE, File.D), new Queen(Team.WHITE));
        init.put(new Position(Rank.ONE, File.E), new King(Team.WHITE));
        init.put(new Position(Rank.ONE, File.F), new Bishop(Team.WHITE));
        init.put(new Position(Rank.ONE, File.G), new Knight(Team.WHITE));
        init.put(new Position(Rank.ONE, File.H), new Rook(Team.WHITE));

        for (File file : File.values()) {
            init.put(new Position(Rank.TWO, file), new Pawn(Team.WHITE));
        }

        for (File file : File.values()) {
            init.put(new Position(Rank.TREE, file), new EmptyPiece(Team.NEUTRALITY));
        }
        for (File file : File.values()) {
            init.put(new Position(Rank.FOUR, file), new EmptyPiece(Team.NEUTRALITY));
        }
        for (File file : File.values()) {
            init.put(new Position(Rank.FIVE, file), new EmptyPiece(Team.NEUTRALITY));
        }
        for (File file : File.values()) {
            init.put(new Position(Rank.SIX, file), new EmptyPiece(Team.NEUTRALITY));
        }

        for (File file : File.values()) {
            init.put(new Position(Rank.SEVEN, file), new Pawn(Team.BLACK));
        }

        init.put(new Position(Rank.EIGHT, File.A), new Rook(Team.BLACK));
        init.put(new Position(Rank.EIGHT, File.B), new Knight(Team.BLACK));
        init.put(new Position(Rank.EIGHT, File.C), new Bishop(Team.BLACK));
        init.put(new Position(Rank.EIGHT, File.D), new Queen(Team.BLACK));
        init.put(new Position(Rank.EIGHT, File.E), new King(Team.BLACK));
        init.put(new Position(Rank.EIGHT, File.F), new Bishop(Team.BLACK));
        init.put(new Position(Rank.EIGHT, File.G), new Knight(Team.BLACK));
        init.put(new Position(Rank.EIGHT, File.H), new Rook(Team.BLACK));

        return init;
    }
}



