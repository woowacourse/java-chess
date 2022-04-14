package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import chess.domain.piece.ChessPiece;

public abstract class Play implements GameState {
    private static final String PLAY_INVALID_COMMAND_EXCEPTION = "[ERROR] 게임 진행 중에는 START 명령을 수행할 수 없습니다.";
    private static final String NOT_MOVABLE_PATH_EXCEPTION = "[ERROR] 체스피스가 이동할 수 없는 위치입니다.";
    private static final String MY_TEAM_EXISTS_IN_TARGET_POSITION_EXCEPTION = "[ERROR] 도착 위치에 우리 팀 체스피스가 있어 이동할 수 없습니다.";

    private final ChessBoard chessBoard;
    private final Team turn;

    Play(ChessBoard chessBoard, Team turn) {
        this.chessBoard = chessBoard;
        this.turn = turn;
    }


    @Override
    public GameState start() {
        throw new UnsupportedOperationException(PLAY_INVALID_COMMAND_EXCEPTION);
    }

    @Override
    public GameState end() {
        return new End();
    }

    @Override
    public GameState move(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        checkMovable(sourcePosition, targetPosition);
        if (chessBoard.existChessPieceOf(targetPosition, turn.reverse())) {
            chessBoard.removeChessPieceAt(targetPosition);
        }
        chessBoard.move(sourcePosition, targetPosition);
        if (chessBoard.isKingDead()) {
            return new Finish(chessBoard);
        }
        return reverseTurn();
    }

    private void checkMovable(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        ChessPiece chessPiece = chessBoard.getChessPiece(sourcePosition, turn);
        if (!chessPiece.isMovable(sourcePosition, targetPosition, chessBoard)) {
            throw new IllegalArgumentException(NOT_MOVABLE_PATH_EXCEPTION);
        }
        if (chessBoard.existChessPieceOf(targetPosition, turn)) {
            throw new IllegalArgumentException(MY_TEAM_EXISTS_IN_TARGET_POSITION_EXCEPTION);
        }
    }

    abstract GameState reverseTurn();

    @Override
    public GameState status() {
        return this;
    }

    @Override
    public Team findWinner() {
        return chessBoard.judgeWinner();
    }

    @Override
    public boolean isPlay() {
        return true;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
