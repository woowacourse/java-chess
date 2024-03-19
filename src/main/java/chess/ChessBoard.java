package chess;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    private final Map<Square, Piece> chessBoard = new LinkedHashMap<>();

    public ChessBoard() {
        // 흑팀 말 배치
        chessBoard.put(Square.of('a', 8), new Piece(PieceType.ROOK, Team.BLACK));
        chessBoard.put(Square.of('b', 8), new Piece(PieceType.KNIGHT, Team.BLACK));
        chessBoard.put(Square.of('c', 8), new Piece(PieceType.BISHOP, Team.BLACK));
        chessBoard.put(Square.of('d', 8), new Piece(PieceType.QUEEN, Team.BLACK));
        chessBoard.put(Square.of('e', 8), new Piece(PieceType.KING, Team.BLACK));
        chessBoard.put(Square.of('f', 8), new Piece(PieceType.BISHOP, Team.BLACK));
        chessBoard.put(Square.of('g', 8), new Piece(PieceType.KNIGHT, Team.BLACK));
        chessBoard.put(Square.of('h', 8), new Piece(PieceType.ROOK, Team.BLACK));
        for (char file = 'a'; file <= 'h'; file++) {
            chessBoard.put(Square.of(file, 7), new Piece(PieceType.PAWN, Team.BLACK));
        }

        // 비어있는 칸들 초기화 (3~6 랭크)
        for (int rank = 3; rank <= 6; rank++) {
            for (char file = 'a'; file <= 'h'; file++) {
                chessBoard.put(Square.of(file, rank), null);
            }
        }

        // 백팀 말 배치
        for (char file = 'a'; file <= 'h'; file++) {
            chessBoard.put(Square.of(file, 2), new Piece(PieceType.PAWN, Team.WHITE));
        }
        chessBoard.put(Square.of('a', 1), new Piece(PieceType.ROOK, Team.WHITE));
        chessBoard.put(Square.of('b', 1), new Piece(PieceType.KNIGHT, Team.WHITE));
        chessBoard.put(Square.of('c', 1), new Piece(PieceType.BISHOP, Team.WHITE));
        chessBoard.put(Square.of('d', 1), new Piece(PieceType.QUEEN, Team.WHITE));
        chessBoard.put(Square.of('e', 1), new Piece(PieceType.KING, Team.WHITE));
        chessBoard.put(Square.of('f', 1), new Piece(PieceType.BISHOP, Team.WHITE));
        chessBoard.put(Square.of('g', 1), new Piece(PieceType.KNIGHT, Team.WHITE));
        chessBoard.put(Square.of('h', 1), new Piece(PieceType.ROOK, Team.WHITE));
    }

    public List<Piece> findAllPieces() {
        return chessBoard.values()
                .stream()
                .toList();
    }
}
