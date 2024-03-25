package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.HashMap;
import java.util.Map;

public class ChessBoardCreator {
    public ChessBoard create() {
        Map<Position, Piece> board = new HashMap<>();
        placePawns(board);
        placeRooks(board);
        placeKnights(board);
        placeBishops(board);
        placeQueens(board);
        placeKings(board);
        return new ChessBoard(board);
    }

    private void placePawns(Map<Position, Piece> board) {
        for (File file : File.values()) {
            board.put(new Position(file, Rank.TWO), new Pawn(Team.WHITE));
            board.put(new Position(file, Rank.SEVEN), new Pawn(Team.BLACK));
        }
    }

    private void placeRooks(Map<Position, Piece> board) {
        board.put(new Position(File.A, Rank.ONE), new Rook(Team.WHITE));
        board.put(new Position(File.H, Rank.ONE), new Rook(Team.WHITE));
        board.put(new Position(File.A, Rank.EIGHT), new Rook(Team.BLACK));
        board.put(new Position(File.H, Rank.EIGHT), new Rook(Team.BLACK));
    }

    private void placeKnights(Map<Position, Piece> board) {
        board.put(new Position(File.B, Rank.ONE), new Knight(Team.WHITE));
        board.put(new Position(File.G, Rank.ONE), new Knight(Team.WHITE));
        board.put(new Position(File.B, Rank.EIGHT), new Knight(Team.BLACK));
        board.put(new Position(File.G, Rank.EIGHT), new Knight(Team.BLACK));
    }

    private void placeBishops(Map<Position, Piece> board) {
        board.put(new Position(File.C, Rank.ONE), new Bishop(Team.WHITE));
        board.put(new Position(File.F, Rank.ONE), new Bishop(Team.WHITE));
        board.put(new Position(File.C, Rank.EIGHT), new Bishop(Team.BLACK));
        board.put(new Position(File.F, Rank.EIGHT), new Bishop(Team.BLACK));
    }

    private void placeQueens(Map<Position, Piece> board) {
        board.put(new Position(File.D, Rank.ONE), new Queen(Team.WHITE));
        board.put(new Position(File.D, Rank.EIGHT), new Queen(Team.BLACK));
    }

    private void placeKings(Map<Position, Piece> board) {
        board.put(new Position(File.E, Rank.ONE), new King(Team.WHITE));
        board.put(new Position(File.E, Rank.EIGHT), new King(Team.BLACK));
    }
}
