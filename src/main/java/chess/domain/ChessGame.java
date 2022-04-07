package chess.domain;

import static chess.domain.GameStatus.CHECK_MATE;
import static chess.domain.GameStatus.END;
import static chess.domain.GameStatus.PLAYING;
import static chess.domain.GameStatus.READY;

import chess.domain.board.Board;
import chess.domain.board.strategy.BoardGenerationStrategy;
import chess.domain.board.Result;
import chess.domain.piece.Direction;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ChessGame {

    private Team turn;
    private GameStatus gameStatus;
    private final Board board;

    public ChessGame() {
        gameStatus = READY;
        turn = Team.WHITE;
        board = new Board();
    }

    public ChessGame(Team turn, GameStatus gameStatus, Board board) {
        this.turn = turn;
        this.gameStatus = gameStatus;
        this.board = board;
    }

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


        tryMove(from, to, fromPiece);
    }

    private void tryMove(Position from, Position to, Piece fromPiece) {
        board.move(to, fromPiece);
        board.remove(from);
        turn = turn.change();
    }

    public void validatePath(Position from, Position to, Direction direction) {
        Position current = from.move(direction);

        while (!current.equals(to)) {
            if (!board.takePieceByPosition(current).isBlank()) {
                throw new IllegalArgumentException("이동 경로에 말이 있습니다.");
            }
            current = current.move(direction);
        }
    }

    private void validateNowTurn(Piece piece) {
        if (!piece.isSameTeam(turn)) {
            throw new IllegalArgumentException("현재 차례는 " + turn.toString().toUpperCase(Locale.ROOT) + "입니다.");
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
            board.findKingPosition(turn.change());
            return cannotMoveKing(board.takePieceByPosition(kingPosition), kingPosition);
        } catch (IllegalArgumentException e) {
            gameStatus = CHECK_MATE;
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
        turn = Team.WHITE;
        gameStatus = END;
        Result result = new Result(getBoard());
        board.removeBoard();
        return result;
    }

    public Piece takePieceByPosition(Position position) {
        return board.takePieceByPosition(position);
    }

    public Map<String, String> toMap() {
        return board.toMap();
    }

    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }

    public Team getTurn() {
        return turn;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }
}
