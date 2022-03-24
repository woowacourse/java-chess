package domain;

import static domain.piece.property.TeamColor.BLACK;
import static domain.piece.property.TeamColor.WHITE;

import domain.piece.Piece;
import domain.piece.unit.Bishop;
import domain.piece.unit.King;
import domain.piece.unit.Knight;
import domain.piece.unit.Pawn;
import domain.piece.CommonMovablePiece;
import domain.piece.property.TeamColor;
import domain.piece.unit.Queen;
import domain.piece.unit.Rook;
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
        createTeamBoard(board, TeamColor.WHITE);

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

    private void createTeamBoard(final Map<Position, Piece> board, final TeamColor teamColor) {
        createInitPawn(board, teamColor);
        createInitRook(board, teamColor);
        createInitKnight(board, teamColor);
        createInitBishop(board, teamColor);
        createInitQueen(board, teamColor);
        createInitKing(board, teamColor);
    }

    private void createInitPawn(final Map<Position, Piece> board, final TeamColor teamColor) {
        if (teamColor == BLACK) {
            Arrays.stream(XPosition.values())
                    .forEach(x -> board.put(new Position(x, YPosition.SEVEN), new Pawn(BLACK)));
            return;
        }
        Arrays.stream(XPosition.values()).forEach(x -> board.put(new Position(x, YPosition.TWO), new Pawn(WHITE)));
    }

    private void createInitRook(final Map<Position, Piece> board, final TeamColor teamColor) {
        if (teamColor == BLACK) {
            board.put(new Position(XPosition.A, YPosition.EIGHT), new Rook(BLACK));
            board.put(new Position(XPosition.H, YPosition.EIGHT), new Rook(BLACK));
            return;
        }
        board.put(new Position(XPosition.A, YPosition.ONE), new Rook(WHITE));
        board.put(new Position(XPosition.H, YPosition.ONE), new Rook(WHITE));
    }

    private void createInitKnight(final Map<Position, Piece> board, final TeamColor teamColor) {
        if (teamColor == BLACK) {
            board.put(new Position(XPosition.B, YPosition.EIGHT), new Knight(BLACK));
            board.put(new Position(XPosition.G, YPosition.EIGHT), new Knight(BLACK));
            return;
        }
        board.put(new Position(XPosition.B, YPosition.ONE), new Knight(WHITE));
        board.put(new Position(XPosition.G, YPosition.ONE), new Knight(WHITE));
    }

    private void createInitBishop(final Map<Position, Piece> board, final TeamColor teamColor) {
        if (teamColor == BLACK) {
            board.put(new Position(XPosition.C, YPosition.EIGHT), new Bishop(BLACK));
            board.put(new Position(XPosition.F, YPosition.EIGHT), new Bishop(BLACK));
            return;
        }
        board.put(new Position(XPosition.C, YPosition.ONE), new Bishop(WHITE));
        board.put(new Position(XPosition.F, YPosition.ONE), new Bishop(WHITE));
    }

    private void createInitQueen(final Map<Position, Piece> board, final TeamColor teamColor) {
        if (teamColor == BLACK) {
            board.put(new Position(XPosition.D, YPosition.EIGHT), new Queen(BLACK));
            return;
        }
        board.put(new Position(XPosition.D, YPosition.ONE), new Queen(WHITE));
    }

    private void createInitKing(final Map<Position, Piece> board, final TeamColor teamColor) {
        if (teamColor == BLACK) {
            board.put(new Position(XPosition.E, YPosition.EIGHT), new King(BLACK));
            return;
        }
        board.put(new Position(XPosition.E, YPosition.ONE), new King(WHITE));
    }
}
