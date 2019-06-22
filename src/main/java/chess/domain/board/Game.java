package chess.domain.board;

public class Game {
    private int totalTurn;
    private Board board;

    public Game(Board board) {
        this.totalTurn = 0;
        this.board = board;
    }

    public void tryGameProgress(Point prev, Point next) {
        if (totalTurn % 2 == 0 && board.isOwnPiece(prev, PlayerType.WHITE)) {
            board.move(prev, next);
            return;
        }

        if (board.isOwnPiece(prev, PlayerType.BLACK)) {
            board.move(prev, next);
        }

        if (board.isKingDead()) {
            // show 결산


        }
    }
}
