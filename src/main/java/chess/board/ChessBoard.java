package chess.board;

import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.piece.Team;

import java.util.HashMap;
import java.util.Map;

import static chess.board.File.values;

public class ChessBoard {

    private final Map<Position, Piece> piecePosition;

    // TODO : EMPTY CHESSBOARD 반환하는 팩토리 메소드 만들기
    
    public static ChessBoard createBoard() {
        final Map<Position, Piece> piecePosition = new HashMap<>();
        for (Team team : Team.values()) {
            createPawn(piecePosition, team);
            createRook(piecePosition, team);
            createKnight(piecePosition, team);
            createBishop(piecePosition, team);
            createQueen(piecePosition, team);
            createKing(piecePosition, team);
        }
        return new ChessBoard(piecePosition);
    }

    private ChessBoard(final Map<Position, Piece> piecePosition) {
        this.piecePosition = piecePosition;
    }

    private static void createPawn(final Map<Position, Piece> piecePosition, final Team team) {
        if (team == Team.WHITE) {
            for (File file : values()) {
                piecePosition.put(new Position(file, "2"), new Pawn(team));
            }
        } else {
            for (File file : values()) {
                piecePosition.put(new Position(file, "7"), new Pawn(team));
            }
        }
    }

    private static void createRook(final Map<Position, Piece> piecePosition, final Team team) {
        if (team == Team.WHITE) {
            piecePosition.put(new Position(File.A, "1"), new Rook(team));
            piecePosition.put(new Position(File.H, "1"), new Rook(team));
        } else {
            piecePosition.put(new Position(File.A, "8"), new Rook(team));
            piecePosition.put(new Position(File.H, "8"), new Rook(team));
        }

    }

    private static void createKnight(final Map<Position, Piece> piecePosition, final Team team) {
        if (team == Team.WHITE) {
            piecePosition.put(new Position(File.B, "1"), new Knight(team));
            piecePosition.put(new Position(File.G, "1"), new Knight(team));
        } else {
            piecePosition.put(new Position(File.B, "8"), new Knight(team));
            piecePosition.put(new Position(File.G, "8"), new Knight(team));
        }

    }

    private static void createBishop(final Map<Position, Piece> piecePosition, final Team team) {
        if (team == Team.WHITE) {
            piecePosition.put(new Position(File.C, "1"), new Bishop(team));
            piecePosition.put(new Position(File.F, "1"), new Bishop(team));
        } else {
            piecePosition.put(new Position(File.C, "8"), new Bishop(team));
            piecePosition.put(new Position(File.F, "8"), new Bishop(team));
        }
    }

    private static void createQueen(final Map<Position, Piece> piecePosition, final Team team) {
        if (team == Team.WHITE) {
            piecePosition.put(new Position(File.D, "1"), new Queen(team));
        } else {
            piecePosition.put(new Position(File.D, "8"), new Queen(team));
        }
    }

    private static void createKing(final Map<Position, Piece> piecePosition, final Team team) {
        if (team == Team.WHITE) {
            piecePosition.put(new Position(File.E, "1"), new King(team));
        } else {
            piecePosition.put(new Position(File.E, "8"), new King(team));
        }
    }

    public Map<Position, Piece> getPiecePosition() {
        return Map.copyOf(piecePosition);
    }
}
