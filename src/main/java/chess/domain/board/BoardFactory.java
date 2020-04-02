package chess.domain.board;

import chess.domain.Team;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class BoardFactory {

    public static Board createInitially() {
        return new Board(createBoardSource());
    }

    private static Map<Position, Piece> createBoardSource() {
        Map<Position, Piece> board = new HashMap<>();

        fillFirstRank(board);
        fillSecondRank(board);
        fillEighthRank(board);
        fillSeventhRank(board);

        return board;
    }

    private static void fillSeventhRank(Map<Position, Piece> board) {
        addToBoard(board, "a7", new Pawn(Team.BLACK));
        addToBoard(board, "b7", new Pawn(Team.BLACK));
        addToBoard(board, "c7", new Pawn(Team.BLACK));
        addToBoard(board, "d7", new Pawn(Team.BLACK));
        addToBoard(board, "e7", new Pawn(Team.BLACK));
        addToBoard(board, "f7", new Pawn(Team.BLACK));
        addToBoard(board, "g7", new Pawn(Team.BLACK));
        addToBoard(board, "h7", new Pawn(Team.BLACK));
    }

    private static void fillEighthRank(Map<Position, Piece> board) {
        addToBoard(board, "a8", new Rook(Team.BLACK));
        addToBoard(board, "b8", new Knight(Team.BLACK));
        addToBoard(board, "c8", new Bishop(Team.BLACK));
        addToBoard(board, "d8", new Queen(Team.BLACK));
        addToBoard(board, "e8", new King(Team.BLACK));
        addToBoard(board, "f8", new Bishop(Team.BLACK));
        addToBoard(board, "g8", new Knight(Team.BLACK));
        addToBoard(board, "h8", new Rook(Team.BLACK));
    }

    private static void fillSecondRank(Map<Position, Piece> board) {
        addToBoard(board, "a2", new Pawn(Team.WHITE));
        addToBoard(board, "b2", new Pawn(Team.WHITE));
        addToBoard(board, "c2", new Pawn(Team.WHITE));
        addToBoard(board, "d2", new Pawn(Team.WHITE));
        addToBoard(board, "e2", new Pawn(Team.WHITE));
        addToBoard(board, "f2", new Pawn(Team.WHITE));
        addToBoard(board, "g2", new Pawn(Team.WHITE));
        addToBoard(board, "h2", new Pawn(Team.WHITE));
    }

    private static void fillFirstRank(Map<Position, Piece> board) {
        addToBoard(board, "a1", new Rook(Team.WHITE));
        addToBoard(board, "b1", new Knight(Team.WHITE));
        addToBoard(board, "c1", new Bishop(Team.WHITE));
        addToBoard(board, "d1", new Queen(Team.WHITE));
        addToBoard(board, "e1", new King(Team.WHITE));
        addToBoard(board, "f1", new Bishop(Team.WHITE));
        addToBoard(board, "g1", new Knight(Team.WHITE));
        addToBoard(board, "h1", new Rook(Team.WHITE));
    }

    private static void addToBoard(
        Map<Position, Piece> board,
        String position,
        Piece piece
    ) {
        board.put(Position.of(position), piece);
    }
}
