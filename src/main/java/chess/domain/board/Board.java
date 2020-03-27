package chess.domain.board;

import chess.domain.Pieces;
import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
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

        if (positionsAreDisturbed(route)) {
            throw new IllegalArgumentException("선택한 기물이 움직일 수 없는 위치입니다.");
        }

        board.remove(fromPosition);
        board.put(toPosition, piece);
    }

    private boolean positionsAreDisturbed(Route route) {
        for (Position position : route.getRoute()) {
            if (board.get(position) != null) {
                return true;
            }
        }
        return false;
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

        for (Rank rank : Rank.values()) {
            resultBoard.add(new ArrayList<>());
            addAcronymToRow(resultBoard, rank);
        }

        return Collections.unmodifiableList(resultBoard);
    }

    private void addAcronymToRow(List<List<String>> result, Rank rank) {
        for (File file : File.values()) {
            result.get(Rank.size() - rank.getRowNumber()).add(acronym(file, rank));
        }
    }

    private String acronym(File file, Rank rank) {
        try {
            return board.get(Position.of(file, rank)).getAcronym();
        } catch (NullPointerException e) {
            return EMPTY_POSITION_ACRONYM;
        }
    }
}
