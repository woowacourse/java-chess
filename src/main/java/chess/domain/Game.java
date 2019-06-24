package chess.domain;

import chess.domain.pieces.Blank;
import chess.domain.pieces.Piece;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static chess.domain.Type.*;

public class Game {
    private static final int KING_COUNT = 2;
    private static final int MAX_BOARD_SIZE = 8;
    private static final int PAWN_ONE_BY_ONE_LINE = 1;

    private Map<Point, Piece> board;
    private Team turn;

    public Game(Map<Point, Piece> board) {
        this.board = board;
        this.turn = Team.WHITE;
    }

    public void move(Point start, Point end) {
        checkBlank(start);
        checkTurn(start);
        Piece startPiece = board.get(start);
        Piece endPiece = board.get(end);
        checkSameTeam(startPiece, endPiece);

        List<Point> candidatePoints = isBlank(end) ? startPiece.move(start, end) : startPiece.attack(start, end);
        checkMove(candidatePoints);
        candidatePoints.forEach(candidatePoint -> checkRoute(end, candidatePoint));
        movePiece(start, end);
    }

    private void checkTurn(Point start) {
        if (!board.get(start).isSameTeam(turn)) {
            throw new IllegalArgumentException("당신 차례가 아닙니다.");
        }
    }

    private void movePiece(Point start, Point end) {
        Piece piece = board.get(start);
        board.put(end, piece);
        board.put(start, new Blank());
    }

    private void checkSameTeam(Piece startPiece, Piece endPiece) {
        if (startPiece.isSameTeam(endPiece)) {
            throw new IllegalArgumentException("이동 불가능 합니다.3");
        }
    }

    private void checkRoute(Point end, Point candidatePoint) {
        if (!isBlank(candidatePoint) && !candidatePoint.equals(end)) {
            throw new IllegalArgumentException("이동 불가능 합니다.2");
        }
    }

    private void checkMove(List<Point> candidatePoints) {
        if (candidatePoints.size() == 0) throw new IllegalArgumentException("이동 불가능 합니다.1");
    }

    private void checkBlank(Point start) {
        if (isBlank(start)) throw new IllegalArgumentException("말이 없습니다.");
    }

    private boolean isBlank(Point position) {
        return board.get(position).isSameType(BLANK);
    }

    public Map<Point, Piece> getBoard() {
        return board;
    }

    public double calculateScore(Team team) {
        return calculatePawnScore(team) + board.values().stream()
                .filter(piece -> piece.isSameTeam(team))
                .mapToDouble(Piece::getScore)
                .sum();
    }

    public boolean isKingAlive() {
        List<Piece> pieces = board.values().stream()
                .filter(piece -> piece.isSameType(KING))
                .collect(Collectors.toList());
        return pieces.size() == KING_COUNT;
    }

    private double calculatePawnScore(Team team) {
        double sub = 0;

        List<Point> pawnPosition = board.keySet().stream()
                .filter(point -> board.get(point).isSameTeam(team))
                .filter(point -> board.get(point).isSameType(WHITE_PAWN)
                        || board.get(point).isSameType(BLACK_PAWN))
                .collect(Collectors.toList());

        //TODO: index를 변경 필요
        for (int i = 0; i < MAX_BOARD_SIZE; i++) {
            int index = i;
            long count = pawnPosition.stream()
                    .filter(p -> p.getPositionX() == index)
                    .count();
            sub -= LongStream.of(count)
                    .mapToDouble(d -> d > PAWN_ONE_BY_ONE_LINE ? count * 0.5 : 0)
                    .sum();
        }

        return sub;
    }

    public void changeTurn() {
        this.turn = (this.turn == Team.WHITE) ? Team.BLACK : Team.WHITE;
    }

    public Team getTurn() {
        return this.turn;
    }
}
