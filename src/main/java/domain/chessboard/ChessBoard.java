package domain.chessboard;

import domain.Player;
import domain.directions.Direction;
import domain.piece.Piece;
import domain.piece.PieceSymbol;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.PieceScore;

public class ChessBoard {

    private static final String NULL_PIECE_SYMBOL = ".";

    private Player currentPlayer;
    private Map<Position, Piece> board;

    public ChessBoard(final BoardGenerator boardGenerator) {
        this.board = boardGenerator.generate();
        this.currentPlayer = Player.WHITE;
    }

    public String getSymbol(final Position position) {
        Piece piece = board.get(position);
        if (piece == null) {
            return NULL_PIECE_SYMBOL;
        }
        return piece.symbolByPlayer();
    }

    public Piece getPieceOf(final Position position) {
        return board.get(position);
    }

    public void move(final Position source, final Position target) {
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        if (isPawn(source)) {
            validateTargetRouteForPawn(source, target);
            movePiece(source, target);
            return;
        }
        validateMoveRoute(source, target);
        movePiece(source, target);
    }

    private void validateSourcePosition(final Position source) {
        validateSourceNotNull(source);
        validateTurn(source);
    }

    private void validateSourceNotNull(final Position source) {
        if (board.get(source) == null) {
            throw new IllegalArgumentException("[ERROR] 비어있는 곳은 출발 위치가 될 수 없습니다.");
        }
    }

    private void validateTurn(final Position source) {
        if (!board.get(source).isSamePlayer(currentPlayer)) {
            throw new IllegalArgumentException("[ERROR] 상대방의 기물을 움직일 수 없습니다.");
        }
    }

    private void validateTargetPosition(final Position source, final Position target) {
        if (!board.get(source).isAvailableMove(source, target)) {
            throw new IllegalArgumentException("[ERROR] 선택한 기물이 이동할 수 없는 위치입니다.");
        }
    }

    private boolean isPawn(final Position source) {
        return board.get(source).symbolByPlayer().equals(PieceSymbol.Pawn.symbol(currentPlayer));
    }

    private void validateTargetRouteForPawn(final Position source, final Position target) {
        Piece sourcePiece = board.get(source);
        Piece targetPiece = board.get(target);
        Direction moveDirection = sourcePiece.getDirection(target);

        if (isPawnMoveDirection(moveDirection)) {
            validatePawnMove(target, sourcePiece);
            return;
        }
        validatePawnAttack(sourcePiece, targetPiece);
    }

    private boolean isPawnMoveDirection(final Direction moveDirection) {
        return moveDirection.equals(Direction.NORTH_NORTH) || moveDirection.equals(
            Direction.SOUTH_SOUTH) || moveDirection.equals(Direction.NORTH) || moveDirection.equals(
            Direction.SOUTH);
    }

    private void validatePawnMove(final Position target, final Piece sourcePiece) {
        List<Position> positions = sourcePiece.getAvailablePositions(target);
        positions.forEach(this::validateNullPosition);
    }

    private void validatePawnAttack(final Piece sourcePiece, final Piece targetPiece) {
        if (targetPiece == null || sourcePiece.isSamePlayer(targetPiece)) {
            throw new IllegalArgumentException("[ERROR] Pawn은 상대편 말이 있을 경우에만 대각선으로 이동할 수 있습니다.");
        }
    }

    private void validateMoveRoute(final Position source, final Position target) {
        Piece sourcePiece = board.get(source);
        Piece targetPiece = board.get(target);
        validateTargetPiece(sourcePiece, targetPiece);
        validatePieceMove(target, sourcePiece);
    }

    private void validatePieceMove(final Position target, final Piece sourcePiece) {
        List<Position> availablePosition = sourcePiece.getAvailablePositions(target);
        List<Position> availableRoute = createMoveRouteList(availablePosition, target);
        availableRoute.forEach(this::validateNullPosition);
    }

    private List<Position> createMoveRouteList(final List<Position> positions,
        final Position target) {
        int index = positions.indexOf(target);
        return positions.subList(0, index);
    }

    private void validateNullPosition(final Position position) {
        if (board.get(position) != null) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치는 다른 기물에 의해 이동할 수 없습니다.");
        }
    }

    private void validateTargetPiece(final Piece sourcePiece, final Piece targetPiece) {
        if (targetPiece != null && sourcePiece.isSamePlayer(targetPiece)) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치는 같은 색 기물이 위치하여 이동할 수 없습니다.");
        }
    }

    private void movePiece(final Position source, final Position target) {
        Piece sourcePiece = board.get(source);
        board.put(target, sourcePiece);
        board.put(source, null);
        changeTurn();
    }

    private void changeTurn() {
        if (currentPlayer.equals(Player.WHITE)) {
            this.currentPlayer = Player.BLACK;
            return;
        }
        this.currentPlayer = Player.WHITE;
    }

    public double calculateScoreByPlayer(Player player) {
        double sum = 0;
        for (File file : File.values()) {
            List<PieceScore> pieceScores = new ArrayList<>();
            for (Rank rank : Rank.values()) {
                Piece piece = board.get(Position.of(file, rank));
                if (piece != null && piece.isSamePlayer(player)) {
                    PieceScore pieceScore = PieceScore.of(piece.symbol());
                    pieceScores.add(pieceScore);
                }

            }
            sum += calculateFileScore(pieceScores);
        }
        return sum;
    }

    private double calculateFileScore(List<PieceScore> pieceScores) {
        double sum = 0;
        for (PieceScore pieceScore : pieceScores) {
            if (pieceScore != null) {
                sum += pieceScore.score();
            }
        }
        return sum - calculatePawnsInFile(pieceScores);
    }

    private double calculatePawnsInFile(List<PieceScore> pieceScores) {
        long count = pieceScores.stream()
            .filter(pieceScore -> pieceScore.score() == PieceScore.Pawn.score())
            .count();
        if (count >= 2) {
            return count * PieceScore.DUPLICATE_PAWN;
        }
        return 0;
    }
}
