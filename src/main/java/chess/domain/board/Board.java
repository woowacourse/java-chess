package chess.domain.board;

import static chess.domain.piece.PieceTeam.EMPTY;

import chess.constant.SquareType;
import chess.domain.board.position.File;
import chess.domain.board.position.Position;
import chess.domain.board.position.Positions;
import chess.domain.board.position.Rank;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.turndecider.GameFlow;
import java.util.Arrays;
import java.util.Map;

public class Board {

    static final String SOURCE_POSITION_SHOULD_HAVE_PIECE_MESSAGE = "[ERROR] 출발 위치에는 말이 있어야 합니다.";
    private static final EmptyPiece EMPTY_PIECE = new EmptyPiece(EMPTY);

    private final Map<Position, Piece> board;
    private final GameFlow gameFlow;

    public Board(Map<Position, Piece> board, GameFlow gameFlow) {
        this.board = board;
        this.gameFlow = gameFlow;
    }

    public void movePiece(Position source, Position target) {
        validateWhiteBlackTeamTurn(source);
        validateSourceNotEmpty(source);
        boolean isGameFinished = isTargetKing(target);
        changePieces(source, target);
        gameFlow.nextState(isGameFinished);
    }

    private boolean isTargetKing(Position position) {
        return board.get(position).isKing();
    }

    private void validateWhiteBlackTeamTurn(Position position) {
        if (!gameFlow.isCorrectTurn(board.get(position))) {
            throw new IllegalArgumentException("[ERROR] 현재 올바르지 않은 팀 선택입니다. ");
        }
    }

    private void changePieces(Position sourcePosition, Position targetPosition) {
        Piece sourcePiece = board.get(sourcePosition);
        Piece targetPiece = board.get(targetPosition);

        SquareType squareType = decideMoveType(targetPiece);
        if (!sourcePiece.isMovable(sourcePosition, targetPosition, squareType) ||
                isBlocked(sourcePosition, targetPosition) ||
                targetPiece.isMyTeam(sourcePiece)) {
            throw new IllegalArgumentException("[ERROR] 이동할 수 없는 위치입니다.");
        }

        board.put(targetPosition, sourcePiece);
        board.put(sourcePosition, EMPTY_PIECE);
    }

    private SquareType decideMoveType(Piece piece) {
        if (piece.equals(EMPTY_PIECE)) {
            return SquareType.EMPTY;
        }
        if (gameFlow.isCorrectTurn(piece)) {
            return SquareType.FRIENDLY;
        }
        return SquareType.ENEMY;
    }

    private boolean isBlocked(Position sourcePosition, Position targetPosition) {
        if (board.get(sourcePosition).isKnight()) {
            return false;
        }

        return sourcePosition.findPositionsToMove(targetPosition)
                .stream()
                .anyMatch(position -> !isEmpty(position));
    }

    private boolean isEmpty(Position position) {
        return board.get(position).equals(EMPTY_PIECE);
    }

    private void validateSourceNotEmpty(Position position) {
        if (isEmpty(position)) {
            throw new IllegalArgumentException(SOURCE_POSITION_SHOULD_HAVE_PIECE_MESSAGE);
        }
    }

    public double calculateScore() {
        return board.values()
                .stream()
                .filter(gameFlow::isCorrectTurn)
                .mapToDouble(Piece::getScore)
                .sum() - adjustPawnScore();
    }

    public double adjustPawnScore() {
        return File.stream()
                .map(file -> duplicatePieceCountByRank(file))
                .filter(count -> count >= 2)
                .mapToDouble(point -> point * 0.5)
                .sum();
    }

    private long duplicatePieceCountByRank(File file) {
        return Arrays.stream(Rank.values())
                .map(rank -> Positions.findPositionBy(file, rank))
                .filter(position -> {
                            Piece piece = board.get(position);
                            return piece.isPawn() && gameFlow.isCorrectTurn(piece);
                        }
                ).count();
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
