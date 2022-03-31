package chess.domain;

import static chess.domain.GameStatus.*;
import static chess.domain.piece.Direction.NORTH;
import static chess.domain.piece.Direction.SOUTH;
import static chess.domain.piece.Direction.pullDiagonalDirections;

import chess.domain.board.Board;
import chess.domain.board.BoardGenerationStrategy;
import chess.domain.board.Result;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ChessGame {

    private Team turn = Team.WHITE;
    private GameStatus gameStatus = READY;
    private final Board board = new Board();

    public void startGame(BoardGenerationStrategy strategy) {
        gameStatus = PLAYING;
        board.initBoard(strategy);
    }

    public void move(Position from, Position to) {
        Piece fromPiece = board.takePositionPiece(from);
        Piece toPiece = board.takePositionPiece(to);
        Direction direction = fromPiece.findDirection(from, to);

        fromPiece.movable(from, to);
        validatePath(from, to, direction);
        validateMoveByPieceType(fromPiece, toPiece, direction);

        if (isFailMove(from, to, fromPiece)) {
            doRollBack(from, to, fromPiece, toPiece);
        }
        turn = turn.change();
    }

    public void validatePath(Position from, Position to, Direction direction) {
        Position current = from.move(direction);

        while (!current.equals(to)) {
            if (board.takePositionPiece(current) != null) {
                throw new IllegalArgumentException("이동 경로에 말이 있습니다.");
            }
            current = current.move(direction);
        }
    }

    private void validateMoveByPieceType(Piece from, Piece to, Direction direction) {
        validateNowTurn(from);

        if (from.isPawn()) {
            checkStraightCondition(to, direction);
            checkDiagonalCondition(from, to, direction);
        }
        checkDifferentTeam(from, to);
    }

    private boolean isFailMove(Position from, Position to, Piece fromPiece) {
        board.move(to, fromPiece);
        board.remove(from);
        return isCheck();
    }

    private void doRollBack(Position from, Position to, Piece fromPiece, Piece toPiece) {
        board.move(to, toPiece);
        board.move(from, fromPiece);
        throw new IllegalArgumentException("체크 상황을 벗어나야 합니다.");
    }

    private void validateNowTurn(Piece piece) {
        if (!piece.isSameTeam(turn)) {
            throw new IllegalArgumentException("현재 차례는 " + turn + "입니다.");
        }
    }

    public boolean isCheck() {
        if (board.isEmpty()) {
            return false;
        }
        Position to = board.findKingPosition(turn);
        return hasCheckKing(to);
    }

    private boolean hasCheckKing(Position to) {
        return board.findSameTeamPieces(turn.change())
                .stream()
                .anyMatch(entry -> canKillKing(entry.getKey(), to, entry.getValue()));
    }

    private boolean canKillKing(Position from, Position to, Piece fromPiece) {
        try {
            fromPiece.movable(from, to);
            board.validatePath(from, to, fromPiece.findDirection(from, to));
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    private void checkStraightCondition(Piece to, Direction direction) {
        if ((direction == NORTH || direction == SOUTH) && Objects.nonNull(to)) {
            throw new IllegalArgumentException("직진은 도착 지점에 말이 없을 때만 가능합니다.");
        }
    }

    private void checkDiagonalCondition(Piece from, Piece to, Direction direction) {
        if (pullDiagonalDirections().contains(direction)
                && (Objects.isNull(to) || from.isSameTeam(to))) {
            throw new IllegalArgumentException("대각선 이동은 상대편의 말을 잡을 때만 가능합니다.");
        }
    }

    private void checkDifferentTeam(Piece from, Piece to) {
        if (Objects.nonNull(to) && from.isSameTeam(to)) {
            throw new IllegalArgumentException("도착 지점에 아군 말이 있어 이동이 불가능합니다.");
        }
    }

    public boolean isCheckmate() {
        if (board.isEmpty()) {
            return false;
        }
        try {
            Position kingPosition = board.findKingPosition(turn);
            return cannotMoveKing(board.takePositionPiece(kingPosition), kingPosition);
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    private boolean cannotMoveKing(Piece king, Position kingPosition) {
        List<Position> kingPaths = king.findMovablePosition(kingPosition);

        for (Position to : kingPaths) {
            if (!hasCheckKing(to)) {
                return false;
            }
        }
        return true;
    }

    public Result createResult() {
        return board.createResult();
    }

    public GameStatus checkGameStatus(){
        if(isCheckmate()){
            gameStatus = CHECK_MATE;
        }
        return gameStatus;
    }

    public Result stepGame(){
        gameStatus = END;
        return new Result(getBoard());
    }

    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }
}
