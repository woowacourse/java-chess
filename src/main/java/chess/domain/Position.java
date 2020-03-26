package chess.domain;

import chess.domain.chessboard.ChessBoard;
import chess.domain.movefactory.Direction;
import chess.domain.movefactory.MoveType;

import java.util.Objects;

public class Position {
    private File file;
    private Rank rank;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(String coordinate) {
        String[] fileAndRank = coordinate.split("");
        return new Position(File.of(fileAndRank[0]), Rank.of(fileAndRank[1]));
    }

    public static Position of(File file, Rank rank) {
        return new Position(file, rank);
    }

    public boolean isNewRank() {
        return file == File.A;
    }

    public boolean isSameRank(Position target) {
        return this.rank == target.rank;
    }

    public boolean isSameFile(Position target) {
        return this.file == target.file;
    }

    public int calculateRankDistance(Position target) {
        return rank.getNumber() - target.rank.getNumber();
    }

    public int calculateFileDistance(Position target) {
        return file.getNumber() - target.file.getNumber();
    }

    public void move(MoveType moveType, ChessBoard chessBoard) {
        moveWhenStraight(moveType, chessBoard);
        moveWhenCross(moveType, chessBoard);
    }

    private void moveWhenStraight(MoveType moveType, ChessBoard chessBoard) {
        Direction direction = moveType.getDirection();
        int count = moveType.getCount();
        int xDegree = this.file.getNumber();
        int yDegree = this.rank.getNumber();

        if (direction == Direction.UP) {
            for (int i = 0; i < count; i++) {
                yDegree++;
                isExistPieceOnPath(chessBoard, xDegree, yDegree);
            }
            this.rank = Rank.of(yDegree);
        }

        if (direction == Direction.DOWN) {
            for (int i = 0; i < count; i++) {
                yDegree--;
                isExistPieceOnPath(chessBoard, xDegree, yDegree);
            }
            this.rank = Rank.of(yDegree);
        }

        if (direction == Direction.RIGHT) {
            for (int i = 0; i < count; i++) {
                xDegree++;
                isExistPieceOnPath(chessBoard, xDegree, yDegree);
            }
            this.file = File.of(xDegree);
        }

        if (direction == Direction.LEFT) {
            for (int i = 0; i < count; i++) {
                xDegree--;
                isExistPieceOnPath(chessBoard, xDegree, yDegree);
            }
            this.file = File.of(xDegree);
        }
        return;
    }

    private void moveWhenCross(MoveType moveType, ChessBoard chessBoard) {
        Direction direction = moveType.getDirection();
        int count = moveType.getCount();
        int xDegree = this.file.getNumber();
        int yDegree = this.rank.getNumber();

        if (direction == Direction.UP_RIGHT) {
            for (int i = 0; i < count; i++) {
                xDegree++;
                yDegree++;
                isExistPieceOnPath(chessBoard, xDegree, yDegree);
            }
            this.file = File.of(xDegree);
            this.rank = Rank.of(yDegree);
        }

        if (direction == Direction.UP_LEFT) {
            for (int i = 0; i < count; i++) {
                xDegree--;
                yDegree++;
                isExistPieceOnPath(chessBoard, xDegree, yDegree);
            }
            this.file = File.of(xDegree);
            this.rank = Rank.of(yDegree);
        }

        if (direction == Direction.DOWN_RIGHT) {
            for (int i = 0; i < count; i++) {
                xDegree++;
                yDegree--;
                isExistPieceOnPath(chessBoard, xDegree, yDegree);
            }
            this.file = File.of(xDegree);
            this.rank = Rank.of(yDegree);
        }

        if (direction == Direction.DOWN_LEFT) {
            for (int i = 0; i < count; i++) {
                xDegree--;
                yDegree--;
                isExistPieceOnPath(chessBoard, xDegree, yDegree);
            }
            this.file = File.of(xDegree);
            this.rank = Rank.of(yDegree);
        }
    }

    private void isExistPieceOnPath(ChessBoard chessBoard, int xDegree, int yDegree) {
        if (chessBoard.isExistPiece(Position.of(File.of(xDegree), Rank.of(yDegree)))) {
            throw new IllegalArgumentException("경로에 다른 말이 존재합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return file == position.file &&
                rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
