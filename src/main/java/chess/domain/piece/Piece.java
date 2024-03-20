package chess.domain.piece;

import chess.domain.Board;
import chess.domain.File;
import chess.domain.PieceColor;
import chess.domain.PieceType;
import chess.domain.Position;
import chess.domain.Rank;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

    private final PieceType name;
    private final PieceColor color;

    public Piece(PieceType name, PieceColor color) {
        this.name = name;
        this.color = color;
    }

    public boolean canMove(Position source, Position target, Board board) {
        List<Position> path = generatePath(source, target);
        return path.stream().allMatch(board::isNotExistPiece);
    }

    protected List<Position> generatePath(Position source, Position target) {
        List<Position> path = new ArrayList<>();

        int deltaFile = (int) Math.signum(target.file().get() - source.file().get());
        int deltaRank = (int) Math.signum(target.rank().get() - source.rank().get());

        File file = source.file();
        Rank rank = source.rank();
        while (target.file() != file || target.rank() != rank) {
            file = file.add(deltaFile);
            rank = rank.add(deltaRank);

            path.add(new Position(file, rank));
        }

        return path;
    }
    
    public PieceType getName() {
        return name;
    }

    public PieceColor getColor() {
        return color;
    }
}
