package chess.domain.board;

import chess.domain.Piece;
import chess.domain.Pieces;
import chess.domain.Route;
import chess.domain.Team;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.*;

public class Board {
    private static final String EMPTY_POSITION_ACRONYM = ".";

    private Map<Position, Piece> board;

    Board(Map<Position, Piece> board) {
        this.board = board;
    }

    private void addAcronymToRow(List<List<String>> result, Rank rank) {
        for (File file : File.values()) {
            result.get(Rank.size() - rank.getRowNumber())
                    .add(acronym(file, rank));
        }
    }

    private String acronym(File file, Rank rank) {
        try {
            return board.get(Position.of(file, rank))
                    .getAcronym();
        } catch (NullPointerException e) {
            return EMPTY_POSITION_ACRONYM;
        }
    }

    public void move(String keyFromPosition, String keyToPosition) {
        move(Position.of(keyFromPosition), Position.of(keyToPosition));
    }

    public void move(Position fromPosition, Position toPosition) {
        Piece piece = board.get(fromPosition);
        Map<Position, Piece> boardForMoving = board;

        if (piece.isBlackTeam()) {
//            boardForMoving = reverseBoard(); // ToDo: reverse 로직 다시 확인
        }

        Route route = piece.findRoute(fromPosition, toPosition);


        if (positionsAreDisturbed(route)) {
            throw new IllegalArgumentException("선택한 기물이 움직일 수 없는 위치입니다.");
        }

        boardForMoving.remove(fromPosition);
        boardForMoving.put(toPosition, piece);
    }

    private boolean positionsAreDisturbed(Route route) {
        for (Position position : route.getRoute()) {
            if (board.get(position) != null) {
                return true;
            }
        }

        return false;
    }

    private Board reverse() {
        Map<Position, Piece> reversedBoard = new HashMap<>();

        for (Position position : board.keySet()) {
            Piece piece = board.get(position);
            Position reversedPosition = position.reverse();
            reversedBoard.put(reversedPosition, piece);
        }

        return BoardFactory.of(reversedBoard);
    }

    public List<List<String>> getBoard() {
        List<List<String>> resultBoard = new ArrayList<>();

        for (Rank rank : Rank.values()) {
            resultBoard.add(new ArrayList<>());
            addAcronymToRow(resultBoard, rank);
        }

        return Collections.unmodifiableList(resultBoard);
    }

    public Pieces findPiecesOf(Team team) {
        List<Piece> piecesSource = new ArrayList<>();

        for (Position position : board.keySet()) {
            Piece piece = board.get(position);
            if (piece != null && piece.isTeam(team)) {
                piecesSource.add(piece);
            }
        }

        return new Pieces(piecesSource);
    }
}
