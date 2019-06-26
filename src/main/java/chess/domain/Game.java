package chess.domain;

import chess.domain.pieces.Blank;
import chess.domain.pieces.Piece;
import chess.dto.PieceDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static chess.domain.Type.*;

public class Game {
    private static final int KING_COUNT = 2;
    private static final int PAWN_ONE_BY_ONE_LINE = 1;
    private static final double PAWN_HALF_SCORE = 0.5;
    private static final int PAWN_SCORE = 1;

    private Map<Point, Piece> board;
    private Team turn;

    public Game(Map<Point, Piece> board) {
        this.board = board;
        this.turn = Team.WHITE;
    }

    public Game(Map<Point, Piece> board, Team turn) {
        this.board = board;
        this.turn = turn;
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
            throw new IllegalArgumentException("당신의 말 위로는 이동 할 수 없습니다.");
        }
    }

    private void checkRoute(Point end, Point candidatePoint) {
        if (!isBlank(candidatePoint) && !candidatePoint.equals(end)) {
            throw new IllegalArgumentException("이동 불가능 합니다.");
        }
    }

    private void checkMove(List<Point> candidatePoints) {
        if (candidatePoints.size() == 0) throw new IllegalArgumentException("이동 불가능 합니다.");
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
                .filter(piece -> piece.getType() != BLACK_PAWN)
                .filter(piece -> piece.getType() != WHITE_PAWN)
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
        return board.entrySet().stream()
                .filter(e -> e.getValue().getTeam() == team)
                .filter(e -> e.getValue().getType() == Type.BLACK_PAWN || e.getValue().getType() == Type.WHITE_PAWN)
                .collect(Collectors.groupingBy(e -> e.getKey().getPositionX()))
                .values()
                .stream()
                .mapToDouble(l -> l.size() == PAWN_ONE_BY_ONE_LINE ? PAWN_SCORE : PAWN_HALF_SCORE * l.size())
                .sum();
    }

    public void changeTurn() {
        this.turn = (this.turn == Team.WHITE) ? Team.BLACK : Team.WHITE;
    }

    public Team getTurn() {
        return this.turn;
    }

    public List<PieceDto> toDto() {
        List<PieceDto> pieceDtos = new ArrayList<>();
        for (Point point : board.keySet()) {
            Piece piece = board.get(point);
            pieceDtos.add(new PieceDto(point.getPositionX(), point.getPositionY(), piece.getType().name(), piece.isSameTeam(Team.WHITE)));
        }
        return pieceDtos;
    }
}
