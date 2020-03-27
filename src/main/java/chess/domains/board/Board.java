package chess.domains.board;

import chess.domains.piece.PieceColor;
import chess.domains.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Board {
    public static final String INVALID_LOCATION_ERR_MSG = "위치를 잘못 입력하였습니다.";
    private final Set<PlayingPiece> board = BoardFactory.getBoard();

    public List<PlayingPiece> showBoard() {
        List<PlayingPiece> showingBoard = new ArrayList<>(board);
        showingBoard.sort(PlayingPiece::compareTo);
        return showingBoard;
    }

    public PlayingPiece findPiece(String location) {
        Position position = Position.ofPositionName(location);
        return board.stream()
                .filter(piece -> piece.has(position))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_LOCATION_ERR_MSG));
    }

    public PlayingPiece findPiece(Position location) {
        return board.stream()
                .filter(piece -> piece.has(location))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_LOCATION_ERR_MSG));
    }

    public void move(Position source, Position target, PieceColor teamColor) {
        PlayingPiece sourcePiece = findPiece(source);
        PlayingPiece targetPiece = findPiece(target);

        sourcePiece.checkMyTurn(teamColor);
        sourcePiece.validMove(targetPiece);

        if (!sourcePiece.isKnight()) {
            List<Position> route = sourcePiece.findRoute(targetPiece);
            validRoute(route);
        }

        exchange(sourcePiece, targetPiece);
    }

    private void validRoute(List<Position> route) {
        if (isBlocked(route)) {
            throw new IllegalArgumentException("말을 뛰어넘을 수 없습니다.");
        }
    }

    private boolean isBlocked(List<Position> route) {
        int count = (int) route.stream()
                .map(this::findPiece)
                .map(PlayingPiece::isBlank)
                .filter(x -> !x)
                .count();

        return count != 0;
    }

    private void exchange(PlayingPiece sourcePiece, PlayingPiece targetPiece) {

        this.board.remove(targetPiece);
        this.board.remove(sourcePiece);
        this.board.addAll(sourcePiece.moveTo(targetPiece));
    }

    public boolean isGameOver() {
        int count = (int) board.stream()
                .filter(PlayingPiece::isKing)
                .count();
        return count != 2;
    }
}
