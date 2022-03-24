package chess.domain.board;

import chess.domain.Color;
import chess.domain.PieceConvertor;
import chess.domain.piece.InvalidPiece;
import chess.domain.piece.Piece;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Board {

    private static final int COLOR_CRITERIA = 15;
    private static final String PIECE_ORDER = "RNBQKBNRPPPPPPPPpppppppprnbqkbnr";
    private static final Map<Position, Piece> startBoard = new LinkedHashMap<>();

    static {
        List<Position> collect = getInitialPositions();
        List<String> pieceOrders = Arrays.stream(PIECE_ORDER.split(""))
                .collect(Collectors.toList());

        for (int i = 0; i < pieceOrders.size(); i++) {
            startBoard.put(collect.get(i), PieceConvertor.of(pieceOrders.get(i), checkColor(i)));
        }
    }

    private final Map<Position, Piece> board;

    public Board() {
        this.board = new LinkedHashMap<>(startBoard);
    }

    private static List<Position> getInitialPositions() {
        return Rank.initialRows()
                .stream()
                .flatMap(getPositionStream())
                .collect(Collectors.toList());
    }

    private static Function<Rank, Stream<? extends Position>> getPositionStream() {
        return rank ->
                Arrays.stream(File.values())
                        .map(file -> Position.of(rank, file));
    }

    private static Color checkColor(int index) {
        if (index > COLOR_CRITERIA) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }

    public boolean move(String from, String to) {
        Position fromPosition = Position.of(from);
        Position toPosition = Position.of(to);

        Piece pieceAtFrom = board.getOrDefault(fromPosition, InvalidPiece.getInstance());
        Piece pieceAtTo = board.getOrDefault(toPosition, InvalidPiece.getInstance());

        // 출발 좌표에 기물이 없으면 false다
        if (pieceAtFrom.isInValid()) {
            return false;
        }

        // 출발 좌표에 있는 기물이 목적지로 이동이 불가하면 false다
        boolean movable = pieceAtFrom.movable(fromPosition.calculateDistance(toPosition), pieceAtTo);
        if (!movable) {
            return false;
        }

        // 이동 경로 내 다른 기물이 있을 경우 false다
        if (!pieceAtFrom.isKnight() && isPieceOnTheWay(fromPosition, toPosition)) {
            return false;
        }

        board.put(toPosition, pieceAtFrom);
        board.remove(fromPosition);
        return true;
    }

    private boolean isPieceOnTheWay(Position fromPosition, Position toPosition) {
        List<Position> positionsOnTheWay = fromPosition.getPositionBetween(toPosition);

        return positionsOnTheWay.stream()
                .anyMatch(board::containsKey);
    }


    public Map<Position, Piece> getBoard() {
        return new LinkedHashMap<>(board);
    }
}
