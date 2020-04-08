package chess.domain.board;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import chess.domain.exception.InvalidMovementException;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.GamePiece;
import chess.domain.player.PlayerColor;
import chess.domain.player.User;
import chess.domain.result.ChessResult;
import chess.dto.DtoConverter;
import chess.dto.LineDto;

public class Board {

    private final Map<Position, GamePiece> board;
    private final Status status;
    private final User first;
    private final User second;

    private Board(Map<Position, GamePiece> board, Status status, User first, User second) {
        this.board = Collections.unmodifiableMap(new TreeMap<>(board));
        this.status = status;
        this.first = first;
        this.second = second;
    }

    static Board of(Map<Position, GamePiece> board, Status status, User first, User second) {
        return new Board(board, status, first, second);
    }

    public String searchPath(String sourceInput) {
        Position source = Position.from(sourceInput);
        GamePiece sourcePiece = board.get(source);
        validateSourcePiece(sourcePiece);

        return String.join(",", sourcePiece.searchPaths(this, source));
    }

    public Board move(String sourceInput, String targetInput) {
        validateAll(sourceInput, targetInput);

        Map<Position, GamePiece> board = new HashMap<>(this.board);
        Position source = Position.from(sourceInput);
        Position target = Position.from(targetInput);
        GamePiece sourcePiece = board.get(source);
        GamePiece targetPiece = board.get(target);

        board.put(target, sourcePiece);
        board.put(source, EmptyPiece.getInstance());
        Status nextStatus = status.nextTurn();

        if (targetPiece.isKing()) {
            return new Board(board, nextStatus.finish(), first, second);
        }
        return new Board(board, nextStatus, first, second);
    }

    private void validateAll(String sourceInput, String targetInput) {
        Position source = Position.from(sourceInput);
        Position target = Position.from(targetInput);

        validateStatus();
        GamePiece sourcePiece = board.get(source);
        validateSourcePiece(sourcePiece);
        validateColor(source, target);
        sourcePiece.validateMoveTo(this, source, target);
    }

    private void validateStatus() {
        if (status.isNotProcessing()) {
            throw new UnsupportedOperationException("먼저 게임을 실행해야합니다.");
        }
    }

    private void validateSourcePiece(GamePiece sourcePiece) {
        if (sourcePiece.equals(EmptyPiece.getInstance())) {
            throw new InvalidMovementException("기물이 존재하지 않습니다.");
        }
        if (status.isWhiteTurn() && sourcePiece.is(PlayerColor.BLACK)) {
            throw new InvalidMovementException("해당 플레이어의 턴이 아닙니다.");
        }
        if (status.isBlackTurn() && sourcePiece.is(PlayerColor.WHITE)) {
            throw new InvalidMovementException("해당 플레이어의 턴이 아닙니다.");
        }
    }

    private void validateColor(Position source, Position target) {
        if (board.get(source).isSameColor(board.get(target))) {
            throw new InvalidMovementException("자신의 말은 잡을 수 없습니다.");
        }
    }

    public boolean isSameColor(GamePiece gamePiece, Position position) {
        return board.get(position).isSameColor(gamePiece);
    }

    public boolean isNotEmpty(Position position) {
        return !EmptyPiece.getInstance().equals(board.get(position));
    }

    public boolean isNotFinished() {
        return status.isNotFinished();
    }

    public ChessResult calculateResult() {
        return ChessResult.from(board);
    }

    public List<LineDto> getRows() {
        return DtoConverter.convertFrom(board);
    }

    public Map<Position, GamePiece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    public int getTurn() {
        return status.getTurn();
    }

    public User getFirstUser() {
        return first;
    }

    public User getSecondUser() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Board))
            return false;
        Board board1 = (Board)o;
        return Objects.equals(board, board1.board) &&
                Objects.equals(status, board1.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, status);
    }
}
