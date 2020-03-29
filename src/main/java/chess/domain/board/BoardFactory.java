package chess.domain.board;

import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.Map;

public class BoardFactory {
    public static Board createInitially() {
        return new Board(createBoardSource());
    }

    public static Board of(Map<Position, Piece> board) {
        return new Board(board);
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
        addToBoard(board, "A7", Team.BLACK, PieceType.PAWN);
        addToBoard(board, "B7", Team.BLACK, PieceType.PAWN);
        addToBoard(board, "C7", Team.BLACK, PieceType.PAWN);
        addToBoard(board, "D7", Team.BLACK, PieceType.PAWN);
        addToBoard(board, "E7", Team.BLACK, PieceType.PAWN);
        addToBoard(board, "F7", Team.BLACK, PieceType.PAWN);
        addToBoard(board, "G7", Team.BLACK, PieceType.PAWN);
        addToBoard(board, "H7", Team.BLACK, PieceType.PAWN);
    }

    private static void fillEighthRank(Map<Position, Piece> board) {
        addToBoard(board, "A8", Team.BLACK, PieceType.ROOK);
        addToBoard(board, "B8", Team.BLACK, PieceType.KNIGHT);
        addToBoard(board, "C8", Team.BLACK, PieceType.BISHOP);
        addToBoard(board, "D8", Team.BLACK, PieceType.QUEEN);
        addToBoard(board, "E8", Team.BLACK, PieceType.KING);
        addToBoard(board, "F8", Team.BLACK, PieceType.BISHOP);
        addToBoard(board, "G8", Team.BLACK, PieceType.KNIGHT);
        addToBoard(board, "H8", Team.BLACK, PieceType.ROOK);
    }

    private static void fillSecondRank(Map<Position, Piece> board) {
        addToBoard(board, "A2", Team.WHITE, PieceType.PAWN);
        addToBoard(board, "B2", Team.WHITE, PieceType.PAWN);
        addToBoard(board, "C2", Team.WHITE, PieceType.PAWN);
        addToBoard(board, "D2", Team.WHITE, PieceType.PAWN);
        addToBoard(board, "E2", Team.WHITE, PieceType.PAWN);
        addToBoard(board, "F2", Team.WHITE, PieceType.PAWN);
        addToBoard(board, "G2", Team.WHITE, PieceType.PAWN);
        addToBoard(board, "H2", Team.WHITE, PieceType.PAWN);
    }

    private static void fillFirstRank(Map<Position, Piece> board) {
        addToBoard(board, "A1", Team.WHITE, PieceType.ROOK);
        addToBoard(board, "B1", Team.WHITE, PieceType.KNIGHT);
        addToBoard(board, "C1", Team.WHITE, PieceType.BISHOP);
        addToBoard(board, "D1", Team.WHITE, PieceType.QUEEN);
        addToBoard(board, "E1", Team.WHITE, PieceType.KING);
        addToBoard(board, "F1", Team.WHITE, PieceType.BISHOP);
        addToBoard(board, "G1", Team.WHITE, PieceType.KNIGHT);
        addToBoard(board, "H1", Team.WHITE, PieceType.ROOK);
    }

    private static void addToBoard(Map<Position, Piece> board, String position, Team team, PieceType pieceType) {
        board.put(Position.of(position), new Piece(team, pieceType));
    }
}
