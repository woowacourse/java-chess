package chess.domain.board;

import chess.domain.Pieces;
import chess.domain.Team;
import chess.domain.piece.Placeable;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.domain.route.Route;

import java.util.*;

public class Board {
    private static final String EMPTY_POSITION_ACRONYM = ".";

    private Map<Position, Placeable> board;

    Board(Map<Position, Placeable> board) {
        this.board = board;
    }

    public void move(String keyFromPosition, String keyToPosition) {
        move(Position.of(keyFromPosition), Position.of(keyToPosition));
    }

    public void move(Position fromPosition, Position toPosition) {
        Placeable pieceToMove = board.get(fromPosition);

        checkIfToPositionIsAvailable(toPosition, pieceToMove);

        Route route = pieceToMove.findRoute(fromPosition, toPosition);

        MovingExecutor movingExecutor = MovingExecutorFactory.from(pieceToMove);
        movingExecutor.move(route, board, fromPosition, toPosition);
    }

    private void checkIfToPositionIsAvailable(Position toPosition, Placeable pieceToMove) {
        Placeable pieceToRemove = board.get(toPosition);
        if (pieceToRemove.isNotEmpty() && pieceToRemove.isTeam(pieceToMove.getTeam())) {
            throw new IllegalArgumentException("이동하려는 위치에 우리팀의 기물이 있습니다.");
        }
    }

    public Pieces findPiecesOf(Team team) {
        Set<Placeable> piecesSource = new HashSet<>();

        for (Position position : board.keySet()) {
            Placeable piece = board.get(position);
            if (piece.isNotEmpty() && piece.isTeam(team)) {
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

    public Placeable findPieceBy(Position position) {
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
