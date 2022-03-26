package chess.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

import chess.domain.piece.Color;
import chess.domain.piece.None;
import chess.domain.piece.Piece;
import chess.domain.position.Direction;
import chess.domain.position.File;
import chess.domain.position.Rank;
import chess.domain.position.Square;

public class Board {
    private static final String ERROR_MESSAGE_POSITION_INCAPABLE = "[ERROR] 이동할 수 없는 위치입니다.";
    private static final String ERROR_MESSAGE_DIRECTION_INCAPABLE = "[ERROR] 가는 길에 다른 피스가 있습니다";
    private static final String ERROR_MESSAGE_TURN = "[ERROR] 순서 지키시지?!";

    private final Map<Square, Piece> board;
    private Color turn;

    public Board() {
        this(createBoard(), Color.WHITE);
    }

    public Board(Map<Square, Piece> board, Color color) {
        this.board = new LinkedHashMap<>(board);
        this.turn = color;
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

    public Board move(Square source, Square target) {
        Piece sourcePiece = board.get(source);
        Piece targetPiece = board.get(target);
        Direction direction = source.getGap(target);

        checkTurn(sourcePiece);
        checkCapablePosition(direction, sourcePiece, targetPiece);
        checkCapableDirection(source, target, direction);

        turn = turn.switchColor();

        board.put(target, sourcePiece);
        board.put(source, new None(Color.NONE));
        return new Board(board,turn);
    }

    private void checkTurn(Piece sourcePiece) {
        if (!sourcePiece.isSameColor(turn)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_TURN);
        }
    }

    private void checkCapablePosition(Direction direction, Piece sourcePiece, Piece targetPiece) {
        if (!sourcePiece.canMove(direction, targetPiece)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_POSITION_INCAPABLE);
        }
    }

    private void checkCapableDirection(Square source, Square target, Direction direction) {
        Direction unitDirection = direction.getUnitDirection();
        Square road = source.add(unitDirection);

        while (!road.equals(target)) {
            checkNone(board.get(road));
            road = road.add(unitDirection);
        }
    }

    private void checkNone(Piece roadPiece) {
        if (!roadPiece.isNone()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_DIRECTION_INCAPABLE);
        }
    }

    public List<Map.Entry<Square, Piece>> filterBy(Color color) {
        return board.entrySet().stream()
                .filter(entry -> entry.getValue().isSameColor(color))
                .collect(Collectors.toList());
    }
}
