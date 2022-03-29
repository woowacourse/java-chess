package chess.domain;

import chess.domain.piece.*;
import chess.domain.postion.File;
import chess.domain.postion.Position;
import chess.domain.postion.Rank;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardInitializer {

    public Board init() {
        Map<Position, Piece> pieces = initWhite();
        pieces.putAll(initBlack());

        return new Board(pieces);
    }

    private Map<Position, Piece> initWhite() {
        Team team = Team.WHITE;
        Rank initWhitePawnRank = Rank.TWO;
        Rank initWhiteRank = Rank.ONE;

        Map<Position, Piece> whitePieces = makePawns(team, initWhitePawnRank, "p");

        whitePieces.put(new Position(File.A, initWhiteRank), new Rook(team, "r"));
        whitePieces.put(new Position(File.B, initWhiteRank), new Knight(team, "n"));
        whitePieces.put(new Position(File.C, initWhiteRank), new Bishop(team, "b"));
        whitePieces.put(new Position(File.D, initWhiteRank), new Queen(team, "q"));
        whitePieces.put(new Position(File.E, initWhiteRank), new King(team, "k"));
        whitePieces.put(new Position(File.F, initWhiteRank), new Bishop(team, "b"));
        whitePieces.put(new Position(File.G, initWhiteRank), new Knight(team, "n"));
        whitePieces.put(new Position(File.H, initWhiteRank), new Rook(team, "r"));

        return whitePieces;
    }

    private Map<Position, Piece> initBlack() {
        Team team = Team.BLACK;
        Rank initBlackPawnRank = Rank.SEVEN;
        Rank initBlackRank = Rank.EIGHT;

        Map<Position, Piece> BlackPieces = makePawns(team, initBlackPawnRank, "P");

        BlackPieces.put(new Position(File.A, initBlackRank), new Rook(team, "R"));
        BlackPieces.put(new Position(File.B, initBlackRank), new Knight(team, "N"));
        BlackPieces.put(new Position(File.C, initBlackRank), new Bishop(team, "B"));
        BlackPieces.put(new Position(File.D, initBlackRank), new Queen(team, "Q"));
        BlackPieces.put(new Position(File.E, initBlackRank), new King(team, "K"));
        BlackPieces.put(new Position(File.F, initBlackRank), new Bishop(team, "B"));
        BlackPieces.put(new Position(File.G, initBlackRank), new Knight(team, "N"));
        BlackPieces.put(new Position(File.H, initBlackRank), new Rook(team, "R"));

        return BlackPieces;
    }

    private Map<Position, Piece> makePawns(Team team, Rank rank, String symbol) {
        return Arrays.stream(File.values())
                .filter(file -> !file.equals(File.NOTHING))
                .collect(Collectors
                        .toMap(file -> new Position(file, rank), file -> new Pawn(team, symbol)));
    }
}
