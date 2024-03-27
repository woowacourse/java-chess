package chess.domain.chessboard;

import static chess.domain.chesspiece.Team.BLACK;
import static chess.domain.chesspiece.Team.WHITE;

import chess.domain.chesspiece.Empty;
import chess.domain.chesspiece.Knight;
import chess.domain.chesspiece.Piece;
import chess.domain.chesspiece.Team;
import chess.domain.chesspiece.pawn.BlackPawn;
import chess.domain.chesspiece.pawn.WhitePawn;
import chess.domain.chesspiece.slidingPiece.Bishop;
import chess.domain.chesspiece.slidingPiece.King;
import chess.domain.chesspiece.slidingPiece.Queen;
import chess.domain.chesspiece.slidingPiece.Rook;
import chess.domain.position.Position;
import java.util.LinkedHashMap;
import java.util.Map;

public class ChessBoardGenerator {

    public static Map<Position, Piece> initializeBoard() {
        Map<Position, Piece> board = new LinkedHashMap<>();
        initializeBackRank(board, "8", BLACK);
        initializeBlackPawnRank(board);
        initializeEmptyRanks(board);
        initializeWhitePawnRank(board);
        initializeBackRank(board, "1", WHITE);
        return board;
    }

    private static void initializeBackRank(Map<Position, Piece> board, String column, Team team) {
        board.put(createPosition("a", column), new Rook(team));
        board.put(createPosition("b", column), new Knight(team));
        board.put(createPosition("c", column), new Bishop(team));
        board.put(createPosition("d", column), new Queen(team));
        board.put(createPosition("e", column), new King(team));
        board.put(createPosition("f", column), new Bishop(team));
        board.put(createPosition("g", column), new Knight(team));
        board.put(createPosition("h", column), new Rook(team));
    }

    private static void initializeBlackPawnRank(Map<Position, Piece> board) {
        initializeFiles(board, "7", new BlackPawn());
    }

    private static void initializeWhitePawnRank(Map<Position, Piece> board) {
        initializeFiles(board, "2", new WhitePawn());
    }

    private static void initializeEmptyRanks(Map<Position, Piece> board) {
        for (int rank = 6; rank >= 3; rank--) {
            initializeFiles(board, String.valueOf(rank), new Empty());
        }
    }

    private static void initializeFiles(Map<Position, Piece> board, String rank, Piece piece) {
        for (char file = 'a'; file <= 'h'; file++) {
            board.put(createPosition(String.valueOf(file), String.valueOf(rank)), piece);
        }
    }

    private static Position createPosition(String file, String column) {
        return new Position(file, column);
    }
}
