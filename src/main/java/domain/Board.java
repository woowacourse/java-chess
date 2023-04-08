package domain;

import dao.Movement;
import domain.piece.Empty;
import domain.piece.King;
import domain.piece.Piece;
import domain.point.File;
import domain.point.Point;
import domain.point.Rank;
import exception.CheckMateException;
import util.BoardInitializer;
import util.ExceptionMessages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Point, Piece> pieceStatus;

    public Board() {
        this.pieceStatus = BoardInitializer.initializeBoard();
    }

    private Board(Map<Point, Piece> pieceStatus) {
        this.pieceStatus = pieceStatus;
    }

    public void reset() {
        pieceStatus.clear();
        pieceStatus.putAll(BoardInitializer.initializeBoard());
    }

    public List<List<Piece>> findCurrentStatus() {
        List<List<Piece>> status = new ArrayList<>();
        findCurrentRanks(status);
        return status;
    }

    private void findCurrentRanks(List<List<Piece>> status) {
        for (Rank rank : Rank.values()) {
            List<Piece> files = new ArrayList<>();
            findCurrentFiles(rank, files);
            status.add(files);
        }
    }

    private void findCurrentFiles(Rank rank, List<Piece> files) {
        for (File file : File.values()) {
            files.add(pieceStatus.get(new Point(file, rank)));
        }
    }

    public void move2(Movement movement, Turn turn) {
        Piece piece = pieceStatus.get(movement.getStartingPoint());

        if (piece.canMove(movement, pieceStatus, turn)) {
            Piece previousPiece = pieceStatus.get(movement.getDestinationPoint());
            pieceStatus.put(movement.getStartingPoint(), new Empty());
            pieceStatus.put(movement.getDestinationPoint(), piece);
            if (previousPiece.getClass() == King.class) {
                // TODO: 테스트코드 통과를 위해 작성하였지만 추후 고쳐야 할 코드
                throw new CheckMateException(turn);
            }
            return;
        }
        throw new IllegalArgumentException(ExceptionMessages.INVALID_DESTINATION);
    }
}
