package chess.domain.chessboard;

import static chess.domain.chesspiece.Team.BLACK;
import static chess.domain.chesspiece.Team.WHITE;

import chess.domain.chesspiece.Bishop;
import chess.domain.chesspiece.Empty;
import chess.domain.chesspiece.King;
import chess.domain.chesspiece.Knight;
import chess.domain.chesspiece.Pawn;
import chess.domain.chesspiece.Piece;
import chess.domain.chesspiece.Queen;
import chess.domain.chesspiece.Rook;
import chess.domain.chesspiece.Team;
import chess.domain.position.Position;
import java.util.LinkedHashMap;
import java.util.Map;

public class ChessBoardGenerator {
    private static final Map<Position, Piece> board = new LinkedHashMap<>();

    public static Map<Position, Piece> initializeBoard() {
        initializeBackRank("8", BLACK);
        initializePawnRank("7", BLACK);
        initializeEmptyRanks();
        initializePawnRank("2", WHITE);
        initializeBackRank("1", WHITE);
        return board;
    }

    private static void initializeBackRank(String column, Team team) {
        board.put(createPosition("a", column), new Rook(team));
        board.put(createPosition("b", column), new Knight(team));
        board.put(createPosition("c", column), new Bishop(team));
        board.put(createPosition("d", column), new Queen(team));
        board.put(createPosition("e", column), new King(team));
        board.put(createPosition("f", column), new Bishop(team));
        board.put(createPosition("g", column), new Knight(team));
        board.put(createPosition("h", column), new Rook(team));
    }

    private static void initializePawnRank(String rank, Team team) {
        initializeFiles(rank, new Pawn(team));
    }

    private static void initializeEmptyRanks() {
        for (int rank = 6; rank >= 3; rank--) {
            initializeFiles(String.valueOf(rank), new Empty());
        }
    }

    private static void initializeFiles(String rank, Piece piece) {
        for (char file = 'a'; file <= 'h'; file++) {
            board.put(createPosition(String.valueOf(file), String.valueOf(rank)), piece);
        }
    }

    private static Position createPosition(String file, String column) {
        return Position.from(file, column);
    }
}
