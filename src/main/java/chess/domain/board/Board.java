package chess.domain.board;

import chess.domain.direction.Route;
import chess.domain.direction.core.Square;
import chess.domain.direction.core.TargetStatus;
import chess.domain.piece.core.Piece;
import chess.domain.piece.core.Team;
import chess.domain.piece.core.Type;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static chess.domain.board.InitBoard.GENERAL;
import static chess.domain.board.InitBoard.SOLDIER;
import static chess.domain.piece.core.Team.BLACK;
import static chess.domain.piece.core.Team.WHITE;

public class Board {
    private Map<Square, Piece> board;
//    private Team team;

    public Board(Map<Square, Piece> board) {
        this.board = board;
    }

    public static Board drawBoard() {
        Map<Square, Piece> board = new HashMap<>();
        board.putAll(GENERAL.genertate(0, BLACK));
        board.putAll(SOLDIER.genertate(1, BLACK));
        board.putAll(SOLDIER.genertate(6,WHITE));
        board.putAll(GENERAL.genertate(7,WHITE));

        return new Board(board);
    }

    public Map<Square, Piece> getBoard() {
        return board;
    }

    public static Board drawBoard(Map<Square, Piece> board) {
        return new Board(board);
    }

    public boolean hasPiece(Square source) {
        return board.containsKey(source);
    }

    public Piece getPiece(Square source) {
        return board.get(source);
    }

    public TargetStatus getTargetStatus(Square source, Square target) {
        return TargetStatus.valuesOf(getPiece(source), getPiece(target));
    }

    public Board changeBoard(Route route) {
        if (!route.canMove(this)) {
            throw new IllegalArgumentException();
        }
        return movePiece(route.getSourceSquare(), route.getTargetSquare());
    }

    Board movePiece(Square source, Square target) {
        Map<Square, Piece> board = new HashMap<>();
        board.putAll(this.board);

        board.put(target, this.board.get(source));
        board.remove(source);

        return drawBoard(board);
    }

    public boolean gameOver() {
        return (int) board.values().stream()
                .filter(this::hasKing)
                .count() != 2;
    }

    private boolean hasKing(Piece piece) {
        if (piece.getType() == Type.KING) {
//            team = piece.getTeam();
            return true;
        }
        return false;
    }

//    public Team getTeam() {
//        return team;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board1 = (Board) o;
        return Objects.equals(board, board1.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board);
    }
}
