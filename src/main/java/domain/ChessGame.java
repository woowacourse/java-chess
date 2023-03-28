package domain;

import domain.piece.Piece;
import domain.piece.PieceInfo;
import domain.piece.TeamColor;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class ChessGame {

    private final ChessBoard chessBoard;
    private final Turn turn;

    public ChessGame() {
        chessBoard = new ChessBoard();
        turn = new Turn();
    }

    public ChessGame(ChessBoard chessBoard, Turn turn) {
        this.chessBoard = chessBoard;
        this.turn = turn;
    }

    public Piece find(Square square) {
        return chessBoard.find(square);
    }

    public void move(Square source, Square destination) {
        Piece piece = chessBoard.find(source);
        turn.validateOrder(piece);
        List<Square> routes = piece.findRoutes(source, destination, chessBoard.find(destination));

        validateRoutes(destination, piece, routes);
        chessBoard.update(source, destination);
        turn.nextOrder();
    }

    private boolean canNotKill(Square destination, Piece piece, Square route) {
        return route != destination || chessBoard.isBlank(route) || piece.isSameTeam(
            chessBoard.find(destination));
    }

    private void validateRoutes(Square destination, Piece piece, List<Square> routes) {
        for (Square route : routes) {
            validateBlock(destination, piece, route);
        }
    }

    private void validateBlock(Square destination, Piece piece, Square route) {
        if (chessBoard.hasPiece(route) && canNotKill(destination, piece, route)) {
            throw new IllegalArgumentException("중간에 기물이 있어 이동할 수 없습니다.");
        }
    }

    public boolean isEnd() {
        return chessBoard.getKingSize() != ChessBoard.INITIAL_KING_SIZE;
    }

    public TeamColor findWin() {
        if (chessBoard.hasNotKing(TeamColor.BLACK)) {
            return TeamColor.WHITE;
        }
        if (chessBoard.hasNotKing(TeamColor.WHITE)) {
            return TeamColor.BLACK;
        }
        return TeamColor.EMPTY;
    }

    public double getScore(TeamColor team) {
        List<Entry<Square, Piece>> teamEntries = chessBoard.getEntriesByTeam(team);
        return getTotalScore(teamEntries) - getDuplicatePawnScore(teamEntries);
    }

    private double getTotalScore(List<Entry<Square, Piece>> teamEntries) {
        return teamEntries.stream()
            .mapToDouble(entry -> entry.getValue().getPieceType().getScore())
            .sum();
    }

    private double getDuplicatePawnScore(List<Entry<Square, Piece>> teamEntries) {
        return teamEntries.stream()
            .filter(entry -> entry.getValue().isPawn())
            .collect(Collectors.groupingBy(entry -> entry.getKey().getChessColumn(),
                Collectors.counting()))
            .values()
            .stream()
            .mapToDouble(Long::doubleValue)
            .filter(count -> count >= 2)
            .sum() * PieceInfo.PAWN.getScore() / 2;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public Turn getTurn() {
        return turn;
    }
}
