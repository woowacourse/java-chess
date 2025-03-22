package chess.game;

import chess.Board;
import chess.Color;
import chess.Position;
import chess.piece.Piece;
import chess.view.BoardView;
import chess.view.InputView;

public class ChessApplication {
    public static void main(String[] args) {
        ChessApplication chessApplication = new ChessApplication();

        chessApplication.start();
    }

    private void start() {
        Board board = Board.initialize();

        Color color = Color.WHITE;
        while (true) { //TODO 승패 조건 추가
            BoardView.printBoard(board);

            Position movingPosition = InputView.readMovingPosition();
            Piece movingPiece = board.findByPositionOrThrow(movingPosition);
            Position targetPosition = InputView.readTargetPosition();

            board.move(movingPiece, targetPosition);

            color = color.opposite();
        }
    }
}
