package chess.domain.state;

import chess.domain.Command;
import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import chess.domain.piece.ChessPiece;
import java.util.List;

public abstract class Play implements GameState {
    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;
    private static final String INVALID_COMMAND_EXCEPTION = "[ERROR] move 또는 status 중에서 입력해주세요.";
    private static final String NOT_MOVABLE_PATH_EXCEPTION = "[ERROR] 체스피스가 이동할 수 없는 위치입니다.";
    private static final String MY_TEAM_EXISTS_IN_TARGET_POSITION_EXCEPTION = "[ERROR] 도착 위치에 우리 팀 체스피스가 있어 이동할 수 없습니다.";

    private final ChessBoard chessBoard;
    private final Team turn;

    Play(ChessBoard chessBoard, Team turn) {
        this.chessBoard = chessBoard;
        this.turn = turn;
    }

    @Override
    public GameState execute(Command command, List<String> input) {
        isExecutable(command);
        if (command.isStatus()) {
            return this;
        }
        ChessBoardPosition sourcePosition = ChessBoardPosition.from(input.get(SOURCE_POSITION_INDEX));
        ChessBoardPosition targetPosition = ChessBoardPosition.from(input.get(TARGET_POSITION_INDEX));
        move(sourcePosition, targetPosition);
        if (chessBoard.isKingDead()) {
            return new Finish(chessBoard);
        }
        return reverseTurn();
    }

    private void isExecutable(Command command) {
        if (command.isStart() || command.isEnd()) {
            throw new IllegalArgumentException(INVALID_COMMAND_EXCEPTION);
        }
    }

    private void move(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        ChessPiece chessPiece = chessBoard.getChessPiece(sourcePosition, turn);

        if (!chessPiece.isMovable(sourcePosition, targetPosition, chessBoard)) {
            throw new IllegalArgumentException(NOT_MOVABLE_PATH_EXCEPTION);
        }

        if (chessBoard.existChessPieceOf(targetPosition, turn)) {
            throw new IllegalArgumentException(MY_TEAM_EXISTS_IN_TARGET_POSITION_EXCEPTION);
        }

        if (chessBoard.existChessPieceOf(targetPosition, turn.reverse())) {
            chessBoard.removeChessPieceAt(targetPosition);
        }
        chessBoard.move(sourcePosition, targetPosition);
    }

    abstract GameState reverseTurn();

    @Override
    public Team findWinner() {
        return chessBoard.judgeWinner();
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
