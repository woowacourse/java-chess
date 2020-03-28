package chess.domain.board;

import chess.domain.Pieces;
import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.domain.route.Route;

import java.util.*;

public class Board {
    private static final String EMPTY_POSITION_ACRONYM = ".";

    private Map<Position, Piece> board;

    Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public void move(String keyFromPosition, String keyToPosition) {
        move(Position.of(keyFromPosition), Position.of(keyToPosition));
    }

    public void move(Position fromPosition, Position toPosition) {
        Piece piece = board.get(fromPosition);
        Route route = piece.findRoute(fromPosition, toPosition);

        MovingExecutor movingExecutor = MovingExecutorFactory.from(piece);
        movingExecutor.move(route, board, fromPosition, toPosition);
    }

    public Pieces findPiecesOf(Team team) {
        Set<Piece> piecesSource = new HashSet<>();

        for (Position position : board.keySet()) {
            Piece piece = board.get(position);
            if (piece != null && piece.isTeam(team)) {
                piecesSource.add(piece);
            }
        }

        return new Pieces(piecesSource);
    }

    public List<List<String>> getBoard() {
        List<List<String>> resultBoard = new ArrayList<>();

        for (Row row : Row.values()) {
            resultBoard.add(new ArrayList<>());
            addAcronymToRow(resultBoard, row);
        }

        return Collections.unmodifiableList(resultBoard);
    }

    public Piece findPieceBy(Position position) {
        return board.get(position);
    }

    private void addAcronymToRow(List<List<String>> result, Row row) {
        for (Column column : Column.values()) {
            result.get(Row.size() - row.getRowNumber()).add(acronym(column, row));
        }
    }

    private String acronym(Column column, Row row) {
        try {
            return board.get(Position.of(column, row)).getAcronym();
        } catch (NullPointerException e) {
            return EMPTY_POSITION_ACRONYM;
        }
    }
}
