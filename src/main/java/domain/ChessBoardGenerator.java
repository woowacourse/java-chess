package domain;

import static domain.piece.Player.BLACK;
import static domain.piece.Player.WHITE;

import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Player;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.position.Position;
import domain.position.XPosition;
import domain.position.YPosition;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ChessBoardGenerator implements BoardGenerator {

    @Override
    public Map<Position, Piece> generate() {
        Map<Position, Piece> board = new HashMap<>();
        createInitialize(board);
        createTeamBoard(board, BLACK);
        createTeamBoard(board, Player.WHITE);

        return board;
    }

    private void createInitialize(final Map<Position, Piece> board) {
        for (XPosition x : XPosition.values()) {
            initializeByRow(board, x);
        }
    }

    private void initializeByRow(final Map<Position, Piece> board, final XPosition x) {
        for (YPosition y : YPosition.values()) {
            board.put(new Position(x, y), null);
        }
    }

    private void createTeamBoard(final Map<Position, Piece> board, final Player player) {
        createInitPawn(board, player);
        createInitRook(board, player);
        createInitKnight(board, player);
        createInitBishop(board, player);
        createInitQueen(board, player);
        createInitKing(board, player);
    }

    private void createInitPawn(final Map<Position, Piece> board, final Player player) {
        if (player == BLACK) {
            Arrays.stream(XPosition.values())
                    .forEach(x -> board.put(new Position(x, YPosition.SEVEN), new Pawn(BLACK)));
            return;
        }
        Arrays.stream(XPosition.values()).forEach(x -> board.put(new Position(x, YPosition.TWO), new Pawn(WHITE)));
    }

    private void createInitRook(final Map<Position, Piece> board, final Player player) {
        if (player == BLACK) {
            board.put(new Position(XPosition.A, YPosition.EIGHT), new Rook(BLACK));
            board.put(new Position(XPosition.H, YPosition.EIGHT), new Rook(BLACK));
            return;
        }
        board.put(new Position(XPosition.A, YPosition.ONE), new Rook(WHITE));
        board.put(new Position(XPosition.H, YPosition.ONE), new Rook(WHITE));
    }

    private void createInitKnight(final Map<Position, Piece> board, final Player player) {
        if (player == BLACK) {
            board.put(new Position(XPosition.B, YPosition.EIGHT), new Knight(BLACK));
            board.put(new Position(XPosition.G, YPosition.EIGHT), new Knight(BLACK));
            return;
        }
        board.put(new Position(XPosition.B, YPosition.ONE), new Knight(WHITE));
        board.put(new Position(XPosition.G, YPosition.ONE), new Knight(WHITE));
    }

    private void createInitBishop(final Map<Position, Piece> board, final Player player) {
        if (player == BLACK) {
            board.put(new Position(XPosition.C, YPosition.EIGHT), new Bishop(BLACK));
            board.put(new Position(XPosition.F, YPosition.EIGHT), new Bishop(BLACK));
            return;
        }
        board.put(new Position(XPosition.C, YPosition.ONE), new Bishop(WHITE));
        board.put(new Position(XPosition.F, YPosition.ONE), new Bishop(WHITE));
    }

    private void createInitQueen(final Map<Position, Piece> board, final Player player) {
        if (player == BLACK) {
            board.put(new Position(XPosition.D, YPosition.EIGHT), new Queen(BLACK));
            return;
        }
        board.put(new Position(XPosition.D, YPosition.ONE), new Queen(WHITE));
    }

    private void createInitKing(final Map<Position, Piece> board, final Player player) {
        if (player == BLACK) {
            board.put(new Position(XPosition.E, YPosition.EIGHT), new King(BLACK));
            return;
        }
        board.put(new Position(XPosition.E, YPosition.ONE), new King(WHITE));
    }
}
