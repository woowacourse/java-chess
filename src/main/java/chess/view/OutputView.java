package chess.view;

import chess.domain.board.Board;
import chess.domain.board.FileCoordinate;
import chess.domain.board.Position;
import chess.domain.board.RankCoordinate;
import chess.domain.piece.Piece;
import java.util.Map;

public class OutputView {

    public void printBoard(Board board) {
        Map<Position, Piece> boards = board.getBoards();
        for (RankCoordinate rankCoordinate : RankCoordinate.values()) {
            for (FileCoordinate fileCoordinate : FileCoordinate.values()) {
                Position position = new Position(fileCoordinate, rankCoordinate);
                Piece piece = boards.get(position);
                String message = PieceTypeView.of(piece.getClass()).getMessage(piece.getColor());
                System.out.print(message);
            }
            System.out.println();
        }
    }
}
