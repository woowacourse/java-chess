package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.util.PointCalculator;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> chessBoard;

    public Board(Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public boolean hasPieceAt(Position position) {
        return chessBoard.containsKey(position);
    }

    public Piece pieceAt(Position position) {
        if (!chessBoard.containsKey(position)) {
            throw new IllegalArgumentException("[ERROR] 해당 좌표에 말이 존재하지 않습니다.");
        }
        return chessBoard.get(position);
    }

    public void validateTargetPieceIsSameTeam(Position position, Team team) {
        if (!chessBoard.containsKey(position)) {
            return;
        }

        if (chessBoard.get(position).isSameTeam(team)) {
            throw new IllegalArgumentException("[ERROR] 해당 좌표에 같은 팀의 말이 존재합니다.");
        }
    }

    public void validateHasPieceInPath(Position position) {
        if (hasPieceAt(position)) {
            throw new IllegalArgumentException("[ERROR] 경로에 말이 존재합니다.");
        }
    }

    public void movePiece(Position source, Position target) {
        chessBoard.put(target, chessBoard.get(source));
        chessBoard.remove(source);
    }

    public double calculateTotalPoint(Team team) {
        return PointCalculator.calculateSumAllPiecesPoint(chessBoard, team);
    }

    public double updatePawnPoint(Team team) {
        return PointCalculator.calculateSumPawnAtSameFilePoint(chessBoard, team);
    }
}
