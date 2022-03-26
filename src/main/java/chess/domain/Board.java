package chess.domain;

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

    public Board() {
        this.chessBoard = new LinkedHashMap<>();
        initializeBoard();
    }

    private void initializeBoard() {
        initializeEmptyBoard();

        initializePiece(Rank.ONE, Team.WHITE);
        initializePiece(Rank.EIGHT, Team.BLACK);

        initializePawn(Rank.TWO, Team.WHITE);
        initializePawn(Rank.SEVEN, Team.BLACK);
    }

    private void initializeEmptyBoard() {
        for (Rank rank : Rank.reverseValues()) {
            for (File file : File.values()) {
                chessBoard.put(Location.of(file, rank), new EmptyPiece());
            }
        }
    }

    private void initializePiece(Rank rank, Team team) {
        chessBoard.put(Location.of(File.A, rank), new Rook(team));
        chessBoard.put(Location.of(File.B, rank), new Knight(team));
        chessBoard.put(Location.of(File.C, rank), new Bishop(team));
        chessBoard.put(Location.of(File.D, rank), new Queen(team));
        chessBoard.put(Location.of(File.E, rank), new King(team));
        chessBoard.put(Location.of(File.F, rank), new Bishop(team));
        chessBoard.put(Location.of(File.G, rank), new Knight(team));
        chessBoard.put(Location.of(File.H, rank), new Rook(team));
    }

    private void initializePawn(Rank rank, Team team) {
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

    public double computeTotalScore(Team team) {
        double score = 0;
        for (File file : File.values()) {
            List<Piece> pieceList = collectFilePieceByTeam(file, team);
            score += computeScore(pieceList);
            score -= computeDuplicatePawnScore(pieceList);
        }
        return score;
    }

    private List<Piece> collectFilePieceByTeam(File file, Team team) {
        return Arrays.stream(Rank.values())
                .map(rank -> Location.of(file, rank))
                .map(chessBoard::get)
                .filter(piece -> piece.isSameTeam(team))
                .collect(Collectors.toList());
    }

    private double computeDuplicatePawnScore(List<Piece> pieceList) {
        if (countPawn(pieceList) > 1) {
            return countPawn(pieceList) * 0.5;
        }
        return 0;
    }

    private long countPawn(List<Piece> pieceList) {
        return pieceList.stream()
                .filter(Piece::isPawn)
                .count();
    }

    private double computeScore(List<Piece> pieces) {
        return pieces.stream()
                .mapToDouble(Piece::getScore)
                .sum();
    }
}
