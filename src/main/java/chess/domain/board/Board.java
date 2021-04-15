package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.utils.PointCalculator;

import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> chessBoard;

    private Board(Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public static Board of(Map<Position, Piece> pieceInfo) {
        return new Board(pieceInfo);
    }

    public boolean containsPosition(Position position) {
        return chessBoard.containsKey(position);
    }

    public Piece pieceAt(Position position) {
        if (!chessBoard.containsKey(position)) {
            throw new IllegalArgumentException("[ERROR] 해당 좌표에 말이 존재하지 않습니다.");
        }
        return chessBoard.get(position);
    }

    public void confirmSameTeamPiece(Position position, Team team) {
        if (!chessBoard.containsKey(position)) {
            return;
        }

        if (chessBoard.get(position).isSameTeam(team)) {
            throw new IllegalArgumentException("[ERROR] 해당 좌표에 같은 팀의 말이 존재합니다.");
        }
    }

    public double calculateTotalPoint(Team team) {
        return PointCalculator.calculate(chessBoard, team);
    }

    public double updatePawnPoint(Team team) {
        return PointCalculator.calculatePawn(chessBoard, team);
    }

    public void movePiece(Path path) {
        chessBoard.put(path.target(), chessBoard.get(path.source()));
        chessBoard.remove(path.source());
    }

    public boolean confirm(List<Position> positions) {
        // 목적지를 제외한 값들에 말이 존재하지 않으면
        return positions.stream().noneMatch(chessBoard::containsKey);
    }

    public void move(Path path, Team turn) {
        final Piece piece = pieceAt(path.source());
        piece.confirmTurn(turn); // 제 차례가 아닌 경우 먼저 터뜨림.
        confirmSameTeamPiece(path.target(), turn); // 목적지에 같은 팀의 말이 있는 경우 터뜨림.
        final List<Position> positions = piece.generate(path, target(path, turn));
        if (!confirm(positions)) {
            throw new IllegalArgumentException("[ERROR] 경로에 말이 존재합니다.");
        }
    }

    public boolean target(Path path, Team turn) {
        if (!chessBoard.containsKey(path.target())) {
            return false;
        }
        return !chessBoard.get(path.target()).isSameTeam(turn);
    }

    public boolean isKingAt(Position target) {
        if (pieceAt(target) == null) {
            return false;
        }
        return pieceAt(target).isKing();
    }

    public Map<Position, Piece> board() {
        return chessBoard;
    }
}
