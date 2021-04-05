package chess.domain.game;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.feature.Color;
import chess.domain.gamestate.Running;
import chess.domain.gamestate.State;
import chess.domain.piece.Piece;

import java.util.List;
import java.util.Map;

public class ChessGame {
    public static final String TURN_MESSAGE = "%s의 차례입니다.";
    public static final String NO_MOVEMENT_ERROR = "현재 위치와 동일한 곳으로 이동할 수 없습니다.";
    public static final String INVALID_RUNNING_COMMAND_ERROR = "move source target 혹은 status 커맨드만 입력 가능합니다.";
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    private final ChessBoard chessBoard;
    private Color turn;
    private State state;

    public ChessGame(ChessBoard chessBoard, Color turn, State state) {
        this.chessBoard = chessBoard;
        this.turn = turn;
        this.state = state;
    }

    public void start(List<String> input) {
        state.start(input, this);
    }

    public void play(List<String> input) {
        state.play(input, this);
    }

    public Result calculateResult() {
        return state.result(this);
    }

    public void initBoard() {
        chessBoard.initBoard();
    }

    public void movePiece(List<String> input) {
        validateCommandSize(input);
        Position sourcePosition = Position.of(input.get(SOURCE_INDEX));
        Position targetPosition = Position.of(input.get(TARGET_INDEX));
        validateMovement(sourcePosition, targetPosition);
        validateTurn(sourcePosition);

        chessBoard.move(sourcePosition, targetPosition);
        turn = turn.getOppositeColor();
    }

    private void validateCommandSize(List<String> input) {
        if (input.size() != 3) {
            throw new IllegalArgumentException(INVALID_RUNNING_COMMAND_ERROR);
        }
    }

    private void validateMovement(Position sourcePosition, Position targetPosition) {
        if (sourcePosition.equals(targetPosition)) {
            throw new IllegalArgumentException(NO_MOVEMENT_ERROR);
        }
    }

    private void validateTurn(Position position) {
        Piece sourcePiece = chessBoard.getPiece(position);
        if (!sourcePiece.isSameColor(turn)) {
            throw new IllegalArgumentException(String.format(TURN_MESSAGE, turn.name()));
        }
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public boolean isOngoing() {
        return state instanceof Running && chessBoard.isOngoing();
    }

    public Result result() {
        double blackScore = chessBoard.getScore(Color.BLACK);
        double whiteScore = chessBoard.getScore(Color.WHITE);

        return new Result(blackScore, whiteScore);
    }

    public void changeState(State state) {
        this.state = state;
    }

    public Color getTurn() {
        return turn;
    }

    public State getState() {
        return state;
    }

    public Map<Position, Piece> getChessBoardAsMap() {
        return chessBoard.getChessBoard();
    }
}
