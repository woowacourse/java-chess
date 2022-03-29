package domain;

import domain.piece.unit.Piece;
import domain.piece.unit.Bishop;
import domain.piece.unit.King;
import domain.piece.unit.Knight;
import domain.piece.unit.Pawn;
import domain.piece.property.Team;
import domain.piece.unit.Queen;
import domain.piece.unit.Rook;
import domain.position.Position;
import domain.position.XPosition;
import domain.position.YPosition;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class ChessBoardGenerator implements BoardGenerator {

    @Override
    public Map<Position, Piece> generate() {
        Map<Position, Piece> board = new HashMap<>();
        createInitialize(board);
        createTeamBoard(board, Team.BLACK);
        createTeamBoard(board, Team.WHITE);

        return board;
    }

    private void createInitialize(final Map<Position, Piece> board) {
        for (XPosition x : XPosition.values()) {
            initializeByRow(board, x);
        }
    }

    private void initializeByRow(final Map<Position, Piece> board, final XPosition x) {
        for (YPosition y : YPosition.values()) {
            board.put(Position.of(x, y), null);
        }
    }

    private void createTeamBoard(final Map<Position, Piece> board, final Team team) {
        checkTeamNull(team);
        createInitPawn(board, team);
        createInitRook(board, team);
        createInitKnight(board, team);
        createInitBishop(board, team);
        createInitQueen(board, team);
        createInitKing(board, team);
    }

    private void checkTeamNull(Team team) {
        if (team == null) {
            throw new IllegalArgumentException("[ERROR] Team 값은 지정되어야 합니다.");
        }
    }

    private void createInitPawn(final Map<Position, Piece> board, final Team team) {
        if (team == team.BLACK) {
            Arrays.stream(XPosition.values())
                    .forEach(x -> board.put(Position.of(x, YPosition.SEVEN), new Pawn(team.BLACK)));
            return;
        }
        Arrays.stream(XPosition.values())
                .forEach(x -> board.put(Position.of(x, YPosition.TWO), new Pawn(team.WHITE)));
    }

    private void createInitRook(final Map<Position, Piece> board, final Team team) {
        if (team == team.BLACK) {
            board.put(Position.of(XPosition.A, YPosition.EIGHT), new Rook(team.BLACK));
            board.put(Position.of(XPosition.H, YPosition.EIGHT), new Rook(team.BLACK));
            return;
        }
        board.put(Position.of(XPosition.A, YPosition.ONE), new Rook(team.WHITE));
        board.put(Position.of(XPosition.H, YPosition.ONE), new Rook(team.WHITE));
    }

    private void createInitKnight(final Map<Position, Piece> board, final Team team) {
        if (team == team.BLACK) {
            board.put(Position.of(XPosition.B, YPosition.EIGHT), new Knight(team.BLACK));
            board.put(Position.of(XPosition.G, YPosition.EIGHT), new Knight(team.BLACK));
            return;
        }
        board.put(Position.of(XPosition.B, YPosition.ONE), new Knight(team.WHITE));
        board.put(Position.of(XPosition.G, YPosition.ONE), new Knight(team.WHITE));
    }

    private void createInitBishop(final Map<Position, Piece> board, final Team team) {
        if (team == team.BLACK) {
            board.put(Position.of(XPosition.C, YPosition.EIGHT), new Bishop(team.BLACK));
            board.put(Position.of(XPosition.F, YPosition.EIGHT), new Bishop(team.BLACK));
            return;
        }
        board.put(Position.of(XPosition.C, YPosition.ONE), new Bishop(team.WHITE));
        board.put(Position.of(XPosition.F, YPosition.ONE), new Bishop(team.WHITE));
    }

    private void createInitQueen(final Map<Position, Piece> board, final Team team) {
        if (team == team.BLACK) {
            board.put(Position.of(XPosition.D, YPosition.EIGHT), new Queen(team.BLACK));
            return;
        }
        board.put(Position.of(XPosition.D, YPosition.ONE), new Queen(team.WHITE));
    }

    private void createInitKing(final Map<Position, Piece> board, final Team team) {
        if (team == team.BLACK) {
            board.put(Position.of(XPosition.E, YPosition.EIGHT), new King(team.BLACK));
            return;
        }
        board.put(Position.of(XPosition.E, YPosition.ONE), new King(team.WHITE));
    }
}
