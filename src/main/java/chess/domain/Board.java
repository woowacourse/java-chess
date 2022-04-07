package chess.domain;

import chess.domain.location.File;
import chess.domain.location.Location;
import chess.domain.location.Rank;
import chess.domain.piece.Bishop;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {
    private final Map<Location, Piece> chessBoard;

    public Board(Map<Location, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public static Board of() {
        Map<Location, Piece> chessBoard = new LinkedHashMap<>();
        initializeBoard(chessBoard);
        return new Board(chessBoard);
    }

    private static void initializeBoard(Map<Location, Piece> chessBoard) {
        initializeEmptyBoard(chessBoard);

        initializePieceExceptPawn(chessBoard, Rank.ONE, Team.WHITE);
        initializePieceExceptPawn(chessBoard, Rank.EIGHT, Team.BLACK);

        initializePawn(chessBoard, Rank.TWO, Team.WHITE);
        initializePawn(chessBoard, Rank.SEVEN, Team.BLACK);
    }

    private static void initializeEmptyBoard(Map<Location, Piece> chessBoard) {
        Rank.reverseValues().forEach(rank -> Arrays.stream(File.values())
                .forEach(file -> chessBoard.put(Location.of(file, rank), new EmptyPiece())));
    }

    private static void initializePieceExceptPawn(Map<Location, Piece> chessBoard, Rank rank, Team team) {
        chessBoard.put(Location.of(File.A, rank), new Rook(team));
        chessBoard.put(Location.of(File.B, rank), new Knight(team));
        chessBoard.put(Location.of(File.C, rank), new Bishop(team));
        chessBoard.put(Location.of(File.D, rank), new Queen(team));
        chessBoard.put(Location.of(File.E, rank), new King(team));
        chessBoard.put(Location.of(File.F, rank), new Bishop(team));
        chessBoard.put(Location.of(File.G, rank), new Knight(team));
        chessBoard.put(Location.of(File.H, rank), new Rook(team));
    }

    private static void initializePawn(Map<Location, Piece> chessBoard, Rank rank, Team team) {
        for (File file : File.values()) {
            chessBoard.put(Location.of(file, rank), new Pawn(team));
        }
    }

    public Piece getPiece(File file, Rank rank) {
        return getPiece(Location.of(file, rank));
    }

    public Piece getPiece(Location location) {
        return chessBoard.get(location);
    }


    public Map<Location, Piece> getBoard() {
        return chessBoard;
    }

    public boolean isEmpty(Location location) {
        return chessBoard.get(location).isEmpty();
    }

    public void move(Location source, Location target) {
        checkIsFirst(chessBoard.get(source));
        chessBoard.put(target, chessBoard.get(source));
        chessBoard.put(source, new EmptyPiece());
    }

    private void checkIsFirst(Piece piece) {
        if (piece.isFirst()) {
            piece.changeNotFirst();
        }
    }

    public List<Piece> collectRankPiece(Rank rank) {
        return Arrays.stream(File.values())
                .map(file -> Location.of(file, rank))
                .map(chessBoard::get)
                .collect(Collectors.toList());
    }

    public Piece get(Location location) {
        return chessBoard.get(location);
    }
}
