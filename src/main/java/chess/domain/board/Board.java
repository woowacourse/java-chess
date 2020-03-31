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

    public void move(String keyFromPosition, String keyToPosition, Team teamInTurn) {
        move(Position.of(keyFromPosition), Position.of(keyToPosition), teamInTurn);
    }

    public void move(Position fromPosition, Position toPosition, Team teamInTurn) {
        Placeable pieceToMove = board.get(fromPosition);

        checkIfFromPositionMovable(fromPosition, teamInTurn);
        checkIfToPositionIsAvailable(toPosition, pieceToMove);

        Route route = pieceToMove.findRoute(fromPosition, toPosition);

        MovingExecutor movingExecutor = MovingExecutorFactory.from(pieceToMove);
        movingExecutor.move(route, board, fromPosition, toPosition);
    }

    private void checkIfFromPositionMovable(Position fromPosition, Team teamInTurn) {
        Placeable pieceToMove = board.get(fromPosition);

        if (pieceToMove.isOppositeTeam(teamInTurn)) {
            throw new IllegalArgumentException("상대편의 말은 움직일 수 없습니다.");
        }
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

    public boolean checkIfOppositeKingIsDead(Team teamInTurn) {
        Team oppositeTeam = teamInTurn.opposite();

        for (Position position : board.keySet()) {
            Placeable piece = board.get(position);
            if (piece.isKingOf(oppositeTeam)) {
                return false;
            }
        }

        return true;
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
        Placeable piece = board.get(Position.of(column, row));
        if (piece.isEmpty()) {
            return EMPTY_POSITION_ACRONYM;
        }

        return piece.getAcronym();
    }

    public List<List<String>> getBoardForPrinting() {
        List<List<String>> resultBoard = new ArrayList<>();

        for (Row row : Row.values()) {
            resultBoard.add(new ArrayList<>());
            addAcronymToRow(resultBoard, row);
        }

        return Collections.unmodifiableList(resultBoard);
    }

    public Map<Position, Placeable> getBoard() {
        return board;
    }
}
