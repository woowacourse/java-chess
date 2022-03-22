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
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Board {
    private final Map<Location, Piece> chessBoard;

    public Board() {
        this.chessBoard = new HashMap<>();
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
        Stream.of(File.values())
                .flatMap(file ->
                        Stream.of(Rank.values())
                                .map(rank -> Location.of(file, rank)))
                .forEach(location -> chessBoard.put(location, new EmptyPiece()));
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
        return chessBoard.get(Location.of(file, rank));
    }
}
