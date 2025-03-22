package chess;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.piece.Team;

public class Board {

    private final Map<Position, Piece> board;

    public Board() {
        this.board = new HashMap<>();
        addPawn();
        addFirstLine();
    }

    private void addFirstLine() {
        for (Column value : Column.values()) {
            if (value == Column.A || value == Column.H) {
                Position teamAPosition = new Position(Row.ONE, value);
                Position teamBPosition = new Position(Row.EIGHT, value);
                board.put(teamAPosition, new Rook(Team.A, teamAPosition));
                board.put(teamBPosition, new Rook(Team.B, teamBPosition));
            }
            if (value == Column.B || value == Column.G) {
                Position teamAPosition = new Position(Row.ONE, value);
                Position teamBPosition = new Position(Row.EIGHT, value);
                board.put(teamAPosition, new Knight(Team.A, teamAPosition));
                board.put(teamBPosition, new Knight(Team.B, teamBPosition));
            }
            if (value == Column.C || value == Column.F) {
                Position teamAPosition = new Position(Row.ONE, value);
                Position teamBPosition = new Position(Row.EIGHT, value);
                board.put(teamAPosition, new Bishop(Team.A, teamAPosition));
                board.put(teamBPosition, new Bishop(Team.B, teamBPosition));
            }
            if (value == Column.D) {
                Position teamAPosition = new Position(Row.ONE, value);
                Position teamBPosition = new Position(Row.EIGHT, value);
                board.put(teamAPosition, new Queen(Team.A, teamAPosition));
                board.put(teamBPosition, new Queen(Team.B, teamBPosition));
            }
            if (value == Column.E) {
                Position teamAPosition = new Position(Row.ONE, value);
                Position teamBPosition = new Position(Row.EIGHT, value);
                board.put(teamAPosition, new King(Team.A, teamAPosition));
                board.put(teamBPosition, new King(Team.B, teamBPosition));
            }
        }
    }

    private void addPawn() {
        for (Column column : Column.values()) {
            Position teamAPosition = new Position(Row.TWO, column);
            Position teamBPosition = new Position(Row.SEVEN, column);
            board.put(teamAPosition, new Pawn(Team.A, teamAPosition));
            board.put(teamBPosition, new Pawn(Team.B, teamBPosition));
        }
    }

    public Piece findByPosition(final Position position) {
        if (board.containsKey(position)) {
            return board.get(position);
        }
        throw new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다.");
    }

    public void move(final Position start, final Position destination) {
        if (!board.containsKey(start)) {
            throw new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다.");
        }
        int rowDiff = Row.calculateDiff(start.row(), destination.row());
        int columnDiff = Column.calculateDiff(start.column(), destination.column());

        Map<Movement, Integer> movements = Movement.calculate(rowDiff, columnDiff);
        if (movements.isEmpty()) {
            throw new IllegalArgumentException("움직일 수 없습니다.");
        }

        Piece piece = board.get(start);
        if (board.containsKey(destination)) {
            Piece otherPiece = board.get(destination);
            if (piece.isSameTeam(otherPiece)) {
                throw new IllegalArgumentException("해당 위치로 움직일 수 없습니다.");
            }
        }
        for (Entry<Movement, Integer> entry : movements.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                piece.move(entry.getKey());
            }
        }

    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

}
