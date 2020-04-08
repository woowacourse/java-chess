package chess.domain.board;

import chess.domain.move.Direction;
import chess.domain.move.Move;
import chess.domain.move.MoveFactory;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceBundleFactory;
import chess.domain.piece.position.Position;
import chess.domain.piece.team.BlackTeam;
import chess.domain.piece.team.WhiteTeam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChessBoard {
    private static final int INIT_KING_COUNT = 2;
    private static final String ERROR_MESSAGE_EXIST_PIECE_ON_PATH = "경로에 다른 말이 존재합니다";
    private static final String ERROR_MESSAGE_POSITION_EXIST_SAME_TEAM = "해당 칸에 같은 팀의 말이 존재 합니다";
    private static final String ERROR_MESSAGE_SOURCE_EMPTY = "해당 칸은 비어있습니다";

    private final List<Piece> pieces;

    private ChessBoard(List<Piece> pieces) {
        this.pieces = new ArrayList<>(pieces);
    }

    public static ChessBoard initPieces() {
        List<Piece> pieces = PieceBundleFactory.createPieceSet(new BlackTeam());
        pieces.addAll(PieceBundleFactory.createPieceSet(new WhiteTeam()));
        return new ChessBoard(pieces);
    }

    public static ChessBoard create(List<Piece> pieces) {
        return new ChessBoard(pieces);
    }

    public void movePiece(Position sourcePosition, Position targetPosition) {
        Piece sourcePiece = findPieceByPosition(sourcePosition)
                .orElseThrow(() -> new IllegalArgumentException(ERROR_MESSAGE_SOURCE_EMPTY));
        validateMovable(sourcePosition, targetPosition);
        validateTargetTeam(sourcePiece, targetPosition);

        removeAttackedPiece(targetPosition);
        sourcePiece.move(targetPosition);
    }

    private void validateMovable(Position sourcePosition, Position targetPosition) {
        Move move = MoveFactory.findMovePattern(sourcePosition, targetPosition);
        findPieceByPosition(sourcePosition)
                .ifPresent(piece -> {
                    piece.validateMovePattern(move, targetPosition, pieces);
                });

        findPieceByPosition(sourcePosition).filter(Piece::isNotKnight)
                .ifPresent(piece -> validatePath(sourcePosition, move));
    }

    public Optional<Piece> findPieceByPosition(Position position) {
        return pieces.stream()
                .filter(x -> x.isEqualPosition(position))
                .findAny();
    }

    private void validatePath(Position sourcePosition, Move move) {
        Position positionInPath = Position.of(sourcePosition);
        Direction direction = move.getDirection();
        int count = move.getCount();

        for (int i = 1; i < count; i++) {
            positionInPath.move(direction);
            checkIsExistPieceOnPath(positionInPath);
        }
    }

    private void checkIsExistPieceOnPath(Position positionInPath) {
        if (findPieceByPosition(positionInPath).isPresent()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_EXIST_PIECE_ON_PATH);
        }
    }

    private void validateTargetTeam(Piece sourcePiece, Position targetPosition) {
        findPieceByPosition(targetPosition)
                .filter(sourcePiece::isSameTeam)
                .ifPresent(piece -> {
                    throw new IllegalArgumentException(ERROR_MESSAGE_POSITION_EXIST_SAME_TEAM);
                });
    }

    public void removeAttackedPiece(Position position) {
        findPieceByPosition(position).ifPresent(pieces::remove);
    }

    public boolean isSurviveKings() {
        return pieces.stream()
                .filter(x -> x instanceof King)
                .count() == INIT_KING_COUNT;
    }

    public List<Piece> getPieces() {
        return this.pieces;
    }

    public boolean isPieceInPosition(Position position) {
        Optional<Piece> pieceByPosition = findPieceByPosition(position);
        return pieceByPosition.isPresent();
    }
}
