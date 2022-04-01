package chess.domain;

import static chess.domain.GameStatus.*;

import chess.domain.board.Board;
import chess.domain.board.BoardGenerationStrategy;
import chess.domain.board.Result;
import chess.domain.piece.Direction;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;

public class ChessGame {

    private Team turn = Team.WHITE;
    private GameStatus gameStatus = READY;
    private final Board board = new Board();

    public void startGame(BoardGenerationStrategy strategy) {
        gameStatus = PLAYING;
        board.initBoard(strategy);
    }

    public void move(Position from, Position to) {
        Piece fromPiece = board.takePieceByPosition(from);
        Piece toPiece = board.takePieceByPosition(to);

        validateNowTurn(fromPiece);
        fromPiece.movable(from, to, toPiece);
        validatePath(from, to, fromPiece.findDirection(from, to));

        tryMove(from, to, fromPiece, toPiece);
    }

    private void tryMove(Position from, Position to, Piece fromPiece, Piece toPiece) {
        if (isFailMove(from, to, fromPiece)) {
            doRollBack(from, to, fromPiece, toPiece);
        }
        turn = turn.change();
    }

    public void validatePath(Position from, Position to, Direction direction) {
        Position current = from.move(direction);

        while (!current.equals(to)) {
            if (board.takePieceByPosition(current) != null) {
                throw new IllegalArgumentException("이동 경로에 말이 있습니다.");
            }
            current = current.move(direction);
        }
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
        if (gameStatus.isReady()) {
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
            fromPiece.movable(from, to, new King(turn));
            board.validatePath(from, to, fromPiece.findDirection(from, to));
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean isCheckmate() {
        if (gameStatus.isReady()) {
            return false;
        }
        try {
            Position kingPosition = board.findKingPosition(turn);
            return cannotMoveKing(board.takePieceByPosition(kingPosition), kingPosition);
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

    public GameStatus checkGameStatus() {
        if (isCheckmate()) {
            gameStatus = CHECK_MATE;
        }
        return gameStatus;
    }

    public Result stepGame() {
        gameStatus = END;
        return new Result(getBoard());
    }

    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }
}
