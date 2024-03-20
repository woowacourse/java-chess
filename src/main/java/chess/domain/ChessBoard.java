package chess.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, Piece> chessBoard = new LinkedHashMap<>();

    public ChessBoard() {
        // 흑팀 말 배치
        chessBoard.put(Position.of('a', 8), new Piece(PieceType.ROOK, Team.BLACK));
        chessBoard.put(Position.of('b', 8), new Piece(PieceType.KNIGHT, Team.BLACK));
        chessBoard.put(Position.of('c', 8), new Piece(PieceType.BISHOP, Team.BLACK));
        chessBoard.put(Position.of('d', 8), new Piece(PieceType.QUEEN, Team.BLACK));
        chessBoard.put(Position.of('e', 8), new Piece(PieceType.KING, Team.BLACK));
        chessBoard.put(Position.of('f', 8), new Piece(PieceType.BISHOP, Team.BLACK));
        chessBoard.put(Position.of('g', 8), new Piece(PieceType.KNIGHT, Team.BLACK));
        chessBoard.put(Position.of('h', 8), new Piece(PieceType.ROOK, Team.BLACK));
        for (char file = 'a'; file <= 'h'; file++) {
            chessBoard.put(Position.of(file, 7), new Piece(PieceType.PAWN, Team.BLACK));
        }

        // 비어있는 칸들 초기화 (3~6 랭크)
        for (int rank = 3; rank <= 6; rank++) {
            for (char file = 'a'; file <= 'h'; file++) {
                chessBoard.put(Position.of(file, rank), null);
            }
        }

        // 백팀 말 배치
        for (char file = 'a'; file <= 'h'; file++) {
            chessBoard.put(Position.of(file, 2), new Piece(PieceType.PAWN, Team.WHITE));
        }
        chessBoard.put(Position.of('a', 1), new Piece(PieceType.ROOK, Team.WHITE));
        chessBoard.put(Position.of('b', 1), new Piece(PieceType.KNIGHT, Team.WHITE));
        chessBoard.put(Position.of('c', 1), new Piece(PieceType.BISHOP, Team.WHITE));
        chessBoard.put(Position.of('d', 1), new Piece(PieceType.QUEEN, Team.WHITE));
        chessBoard.put(Position.of('e', 1), new Piece(PieceType.KING, Team.WHITE));
        chessBoard.put(Position.of('f', 1), new Piece(PieceType.BISHOP, Team.WHITE));
        chessBoard.put(Position.of('g', 1), new Piece(PieceType.KNIGHT, Team.WHITE));
        chessBoard.put(Position.of('h', 1), new Piece(PieceType.ROOK, Team.WHITE));
    }

    public List<Piece> findAllPieces() {
        return chessBoard.values()
                .stream()
                .toList();
    }

    public Piece findPieceByPosition(final Position position) {
        return chessBoard.get(position);
    }

    public void move(final Position source, final Position target) {
        Piece piece = chessBoard.get(source);

        chessBoard.put(target, piece);
        chessBoard.put(source, null);
    }
}
