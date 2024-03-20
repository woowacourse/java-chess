package domain;

import domain.board.Board;
import domain.piece.Piece;
import domain.position.Position;
import domain.position.PositionGenerator;
import java.util.List;

public class Chess {

    private final Board board = Board.create();

    public void play(List<String> rawCommand) {
        PositionGenerator positionGenerator = new PositionGenerator();
        Position sourcePosition = positionGenerator.generate(rawCommand.get(1));
        Position targetPosition = positionGenerator.generate(rawCommand.get(2));

        Piece piece = board.findPieceByPosition(sourcePosition);
        if (piece.canMove(sourcePosition, targetPosition)) {
            board.placePieceByPosition(piece, targetPosition);
            board.displacePieceByPosition(sourcePosition);
        }
    }

    public Board getBoard() {
        return board;
    }
}
