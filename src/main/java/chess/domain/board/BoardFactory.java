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

    private static Map<Position, Piece> createBoardSource() {
        Map<Position, Piece> board = new HashMap<>();

        fillFirstRank(board);
        fillSecondRank(board);
        fillEighthRank(board);
        fillSeventhRank(board);

        return board;
    }

    private static void fillSeventhRank(Map<Position, Piece> board) {
        addToBoard(board, "a7", Team.BLACK, PieceType.PAWN);
        addToBoard(board, "b7", Team.BLACK, PieceType.PAWN);
        addToBoard(board, "c7", Team.BLACK, PieceType.PAWN);
        addToBoard(board, "d7", Team.BLACK, PieceType.PAWN);
        addToBoard(board, "e7", Team.BLACK, PieceType.PAWN);
        addToBoard(board, "f7", Team.BLACK, PieceType.PAWN);
        addToBoard(board, "g7", Team.BLACK, PieceType.PAWN);
        addToBoard(board, "h7", Team.BLACK, PieceType.PAWN);
    }

    private static void fillEighthRank(Map<Position, Piece> board) {
        addToBoard(board, "a8", Team.BLACK, PieceType.ROOK);
        addToBoard(board, "b8", Team.BLACK, PieceType.KNIGHT);
        addToBoard(board, "c8", Team.BLACK, PieceType.BISHOP);
        addToBoard(board, "d8", Team.BLACK, PieceType.QUEEN);
        addToBoard(board, "e8", Team.BLACK, PieceType.KING);
        addToBoard(board, "f8", Team.BLACK, PieceType.BISHOP);
        addToBoard(board, "g8", Team.BLACK, PieceType.KNIGHT);
        addToBoard(board, "h8", Team.BLACK, PieceType.ROOK);
    }

    private static void fillSecondRank(Map<Position, Piece> board) {
        addToBoard(board, "a2", Team.WHITE, PieceType.PAWN);
        addToBoard(board, "b2", Team.WHITE, PieceType.PAWN);
        addToBoard(board, "c2", Team.WHITE, PieceType.PAWN);
        addToBoard(board, "d2", Team.WHITE, PieceType.PAWN);
        addToBoard(board, "e2", Team.WHITE, PieceType.PAWN);
        addToBoard(board, "f2", Team.WHITE, PieceType.PAWN);
        addToBoard(board, "g2", Team.WHITE, PieceType.PAWN);
        addToBoard(board, "h2", Team.WHITE, PieceType.PAWN);
    }

    private static void fillFirstRank(Map<Position, Piece> board) {
        addToBoard(board, "a1", Team.WHITE, PieceType.ROOK);
        addToBoard(board, "b1", Team.WHITE, PieceType.KNIGHT);
        addToBoard(board, "c1", Team.WHITE, PieceType.BISHOP);
        addToBoard(board, "d1", Team.WHITE, PieceType.QUEEN);
        addToBoard(board, "e1", Team.WHITE, PieceType.KING);
        addToBoard(board, "f1", Team.WHITE, PieceType.BISHOP);
        addToBoard(board, "g1", Team.WHITE, PieceType.KNIGHT);
        addToBoard(board, "h1", Team.WHITE, PieceType.ROOK);
    }

    private static void addToBoard(
        Map<Position, Piece> board,
        String position,
        Team team,
        PieceType pieceType
    ) {
        board.put(Position.of(position), new Piece(team, pieceType));
    }
}
