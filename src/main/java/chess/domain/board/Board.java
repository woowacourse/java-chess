package chess.domain.board;

import static chess.domain.piece.PieceColor.EMPTY;

import chess.constant.MoveType;
import chess.domain.board.position.Position;
import chess.domain.board.position.Positions;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.turndecider.GameFlow;
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
        turnDecide(source);
        validateSourceNotEmpty(source);
        boolean isGameFinished = isTargetKing(target);
        changePieces(source, target);
        gameFlow.nextState(isGameFinished);
    }

    private boolean isTargetKing(Position target) {
        return board.get(target).isKing();
    }

    private void turnDecide(Position source) {
        if (!gameFlow.isCorrectTurn(board.get(source))) {
            throw new IllegalArgumentException("[ERROR] 현재 올바르지 않은 팀 선택입니다. ");
        }
    }

    private void changePieces(Position source, Position target) {
        Piece sourcePiece = board.get(source);
        Piece targetPiece = board.get(target);

        MoveType moveType = decideMoveType(targetPiece);
        if (!sourcePiece.isMovable(source, target, moveType) || isBlocked(source, target) || targetPiece.isMyTeam(
                sourcePiece)) {
            throw new IllegalArgumentException("[ERROR] 이동할 수 없는 위치입니다.");
        }

        board.put(target, sourcePiece);
        board.put(source, EMPTY_PIECE);
    }

    private MoveType decideMoveType(Piece targetPiece) {
        if (targetPiece.equals(EMPTY_PIECE)) {
            return MoveType.EMPTY;
        }
        if (gameFlow.isCorrectTurn(targetPiece)) {
            return MoveType.FRIENDLY;
        }
        return MoveType.ENEMY;
    }

    private boolean isBlocked(Position source, Position target) {
        if (board.get(source) instanceof Knight) {
            return false;
        }

        for (Position position : source.findPositionsToMove(target)) {
            if (isEmpty(position)) {
                return true;
            }
        }
        return false;
    }

    private boolean isEmpty(Position position) {
        return !board.get(position).equals(EMPTY_PIECE);
    }

    private void validateSourceNotEmpty(Position source) {
        if (board.get(source).equals(EMPTY_PIECE)) {
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
        int adjustingScore = 0;
        for (File file : File.values()) {
            long rankDuplicatedPiecesCount = Rank.reverseValues()
                    .stream()
                    .map(rank -> Positions.findPositionBy(file, rank))
                    .filter(position -> {
                                Piece piece = board.get(position);
                                return piece.isPawn() && gameFlow.isCorrectTurn(piece);
                            }
                    ).count();

            if (rankDuplicatedPiecesCount> 1) {
                adjustingScore += rankDuplicatedPiecesCount * 0.5;
            }
        }
        return adjustingScore;
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
