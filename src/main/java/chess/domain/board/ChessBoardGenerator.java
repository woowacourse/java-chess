package chess.domain.board;

import chess.domain.piece.property.Team;
import chess.domain.piece.unit.Bishop;
import chess.domain.piece.unit.King;
import chess.domain.piece.unit.Knight;
import chess.domain.piece.unit.Pawn;
import chess.domain.piece.unit.Piece;
import chess.domain.piece.unit.Queen;
import chess.domain.piece.unit.Rook;
import chess.domain.position.Position;
import chess.domain.position.XPosition;
import chess.domain.position.YPosition;
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
        validateTeamNull(team);
        createInitPawn(board, team);
        createInitRook(board, team);
        createInitKnight(board, team);
        createInitBishop(board, team);
        createInitQueen(board, team);
        createInitKing(board, team);
    }

    private void validateTeamNull(Team team) {
        if (team == null) {
            throw new IllegalArgumentException("[ERROR] Team 값은 지정되어야 합니다.");
        }
    }

    private void createInitPawn(final Map<Position, Piece> board, final Team team) {
        if (team == Team.BLACK) {
            Arrays.stream(XPosition.values())
                    .forEach(x -> board.put(Position.of(x, YPosition.SEVEN), new Pawn(Team.BLACK)));
            return;
        }
        Arrays.stream(XPosition.values())
                .forEach(x -> board.put(Position.of(x, YPosition.TWO), new Pawn(Team.WHITE)));
    }

    private void createInitRook(final Map<Position, Piece> board, final Team team) {
        if (team == Team.BLACK) {
            board.put(Position.of(XPosition.A, YPosition.EIGHT), new Rook(Team.BLACK));
            board.put(Position.of(XPosition.H, YPosition.EIGHT), new Rook(Team.BLACK));
            return;
        }
        board.put(Position.of(XPosition.A, YPosition.ONE), new Rook(Team.WHITE));
        board.put(Position.of(XPosition.H, YPosition.ONE), new Rook(Team.WHITE));
    }

    private void createInitKnight(final Map<Position, Piece> board, final Team team) {
        if (team == Team.BLACK) {
            board.put(Position.of(XPosition.B, YPosition.EIGHT), new Knight(Team.BLACK));
            board.put(Position.of(XPosition.G, YPosition.EIGHT), new Knight(Team.BLACK));
            return;
        }
        board.put(Position.of(XPosition.B, YPosition.ONE), new Knight(Team.WHITE));
        board.put(Position.of(XPosition.G, YPosition.ONE), new Knight(Team.WHITE));
    }

    private void createInitBishop(final Map<Position, Piece> board, final Team team) {
        if (team == Team.BLACK) {
            board.put(Position.of(XPosition.C, YPosition.EIGHT), new Bishop(Team.BLACK));
            board.put(Position.of(XPosition.F, YPosition.EIGHT), new Bishop(Team.BLACK));
            return;
        }
        board.put(Position.of(XPosition.C, YPosition.ONE), new Bishop(Team.WHITE));
        board.put(Position.of(XPosition.F, YPosition.ONE), new Bishop(Team.WHITE));
    }

    private void createInitQueen(final Map<Position, Piece> board, final Team team) {
        if (team == Team.BLACK) {
            board.put(Position.of(XPosition.D, YPosition.EIGHT), new Queen(Team.BLACK));
            return;
        }
        board.put(Position.of(XPosition.D, YPosition.ONE), new Queen(Team.WHITE));
    }

    private void createInitKing(final Map<Position, Piece> board, final Team team) {
        if (team == Team.BLACK) {
            board.put(Position.of(XPosition.E, YPosition.EIGHT), new King(Team.BLACK));
            return;
        }
        board.put(Position.of(XPosition.E, YPosition.ONE), new King(Team.WHITE));
    }
}
