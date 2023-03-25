package chess.game;

import chess.board.Board;
import chess.board.Position;
import chess.controller.GameStatus;
import chess.piece.Side;

public class ChessGame {

    private static final double PAWN_DEDUCATION_SCORE = 0.5;

    private final Board board;
    private Side turnToMove;
    private GameStatus gameStatus;

    public ChessGame(final Board board, final Side firstTurn, final GameStatus gameStatus) {
        this.board = board;
        this.turnToMove = firstTurn;
        this.gameStatus = gameStatus;
    }

    public void start() {
        checkGameAlreadyStart(gameStatus);
        gameStatus = GameStatus.START;
    }

    private void checkGameAlreadyStart(final GameStatus gameStatus) {
        if (gameStatus == GameStatus.START) {
            throw new IllegalArgumentException("[ERROR] 게임 플레이 중에는 다시 시작할 수 없습니다.");
        }
    }

    public void movePiece(Position sourcePosition, Position targetPosition) {
        checkGameNotStart();
        final Side sourcePieceSide = board.getPieceSide(sourcePosition);
        checkTurnToMoveBySide(sourcePieceSide);
        board.movePiece(sourcePosition, targetPosition);
        changeTurnToMove();
    }

    private void checkTurnToMoveBySide(final Side sourcePieceSide) {
        if (sourcePieceSide != turnToMove) {
            throw new IllegalArgumentException("[ERROR] 상대방의 말은 이동시킬 수 없습니다.");
        }
    }

    private void changeTurnToMove() {
        if (turnToMove == Side.BLACK) {
            turnToMove = Side.WHITE;
            return;
        }
        turnToMove = Side.BLACK;
    }

    private void checkGameNotStart() {
        if (gameStatus != GameStatus.START) {
            throw new IllegalArgumentException("[ERROR] 게임 시작 이후에 말을 이동할 수 있습니다.");
        }
    }

    public void exit() {
        gameStatus = GameStatus.END;
    }

    public double calculateScoreBySide(Side side) {
        double scoreBySide = board.getScoreBySide(side);
        final long exceedingPawnCount = board.getCountOfPawnsOnSameFileBySide(side);
        scoreBySide = scoreBySide - exceedingPawnCount * PAWN_DEDUCATION_SCORE;
        return scoreBySide;
    }

    public GameStatus status() {
        return this.gameStatus;
    }

    public Board getBoard() {
        return this.board;
    }
}
