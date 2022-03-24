package chess.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

import chess.domain.piece.Piece;
import chess.domain.position.Direction;
import chess.domain.position.File;
import chess.domain.position.Rank;
import chess.domain.position.Square;

public class Board {
    private static final Piece NONE = Piece.from(File.A, Rank.THREE);
    private static final String ERROR_MESSAGE_POSITION_INCAPABLE = "[ERROR] 이동할 수 없는 위치입니다.";
    private static final String ERROR_MESSAGE_POSITION_SAME_TEAM = "[ERROR] 아군의 말이 있는 곳으로는 이동할 수 없습니다.";

    private final Map<Square, Piece> board;

    public Board() {
        this(createBoard());
    }

    public Board(Map<Square, Piece> board) {
        this.board = new LinkedHashMap<>(board);
    }

    private static Map<Square, Piece> createBoard() {
        Map<Square, Piece> board = new LinkedHashMap<>();
        for (Rank rank : Rank.values()) {
            createRow(board, rank);
        }
        return board;
    }

    private static void createRow(Map<Square, Piece> board, Rank rank) {
        for (File file : File.values()) {
            board.put(new Square(file, rank), Piece.from(file, rank));
        }
    }

    public List<List<Piece>> splitByRank() {
        List<Piece> pieces = new ArrayList<>(board.values());
        List<List<Piece>> splitPieces = Lists.partition(pieces, Rank.values().length);
        return reverse(splitPieces);
    }

    private List<List<Piece>> reverse(List<List<Piece>> splitPieces) {
        List<List<Piece>> result = new ArrayList<>();
        for (List<Piece> splitPiece : splitPieces) {
            result.add(0, splitPiece);
        }
        return result;
    }

    public void move(Square source, Square target) {
        Piece sourcePiece = board.get(source);
        Piece targetPiece = board.get(target);
        Direction direction = source.getGap(target);

        checkSameTeamPosition(sourcePiece, targetPiece);
        checkCapablePosition(direction, sourcePiece);

        board.put(target, sourcePiece);
        board.put(source, NONE);
    }

    private void checkCapablePosition(Direction direction, Piece sourcePiece) {
        if (!sourcePiece.canMove(direction)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_POSITION_INCAPABLE);
        }
    }

    private void checkSameTeamPosition(Piece sourcePiece, Piece targetPiece) {
        if (sourcePiece.isSameTeam(targetPiece)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_POSITION_SAME_TEAM);
        }
    }
}
