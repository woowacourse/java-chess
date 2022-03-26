package domain.chessboard;

import domain.Player;
import domain.directions.Direction;
import domain.piece.Piece;
import domain.position.Position;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    private static final int BLACK_PAWN_START_LINE = 7;
    private static final int WHITE_PAWN_START_LINE = 2;

    private Player currentPlayer;
    Map<Position, Piece> board;

    public ChessBoard(final BoardGenerator boardGenerator) {
        this.board = boardGenerator.generate();
        this.currentPlayer = Player.WHITE;
    }

    public String getSymbol(final Position position) {
        Piece piece = board.get(position);
        if (piece == null) {
            return ".";
        }
        return piece.symbol();
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

    private void validateSourcePosition(Position source) {
        validateSourceNotNull(source);
        validateTurn(source);
    }

    private void validateSourceNotNull(Position source) {
        if (board.get(source) == null) {
            throw new IllegalArgumentException("[ERROR] 비어있는 곳은 출발 위치가 될 수 없습니다.");
        }
    }

    private void validateTurn(Position source) {
        if (!board.get(source).isSamePlayer(currentPlayer)) {
            throw new IllegalArgumentException("[ERROR] 상대방의 기물을 움직일 수 없습니다.");
        }
    }

    private void validateTargetPosition(final Position source, final Position target) {
        if (!board.get(source).isAvailableMove(source, target)) {
            throw new IllegalArgumentException("[ERROR] 선택한 기물이 이동할 수 없는 위치입니다.");
        }
    }

    private boolean isPawn(Position source) {
        return board.get(source).symbol().toLowerCase().equals("p");
    }

    private void validateTargetRouteForPawn(Position source, Position target) {
        Piece sourcePiece = board.get(source);
        Piece targetPiece = board.get(target);
        Direction moveDirection = sourcePiece.getDirection(target);

        if (isPawnMoveDirection(moveDirection)) {
            validatePawnMove(target, sourcePiece);
            return;
        }
        validatePawnAttack(sourcePiece, targetPiece);
    }

    private boolean isPawnMoveDirection(Direction moveDirection) {
        return moveDirection.equals(Direction.NORTH_NORTH) || moveDirection.equals(
            Direction.SOUTH_SOUTH) || moveDirection.equals(Direction.NORTH) || moveDirection.equals(
            Direction.SOUTH);
    }

    private void validatePawnMove(Position target, Piece sourcePiece) {
        List<Position> positions = sourcePiece.getAvailablePositions(target);
        positions.forEach(this::validateNullPosition);
    }

    private void validatePawnAttack(Piece sourcePiece, Piece targetPiece) {
        if (targetPiece == null || sourcePiece.isSamePlayer(targetPiece)) {
            throw new IllegalArgumentException("[ERROR] Pawn은 상대편 말이 있을 경우에만 대각선으로 이동할 수 있습니다.");
        }
    }

    private void validateMoveRoute(Position source, Position target) {
        Piece sourcePiece = board.get(source);
        Piece targetPiece = board.get(target);
        validateTargetPiece(sourcePiece, targetPiece);
        validatePieceMove(target, sourcePiece);
    }

    private void validatePieceMove(Position target, Piece sourcePiece) {
        List<Position> availablePosition = sourcePiece.getAvailablePositions(target);
        List<Position> availableRoute = createMoveRouteList(availablePosition, target);
        availableRoute.forEach(this::validateNullPosition);
    }

    private List<Position> createMoveRouteList(List<Position> positions, Position target) {
        int index = positions.indexOf(target);
        return positions.subList(0, index);
    }

    private void validateNullPosition(Position position) {
        if (board.get(position) != null) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치는 다른 기물에 의해 이동할 수 없습니다.");
        }
    }

    private void validateTargetPiece(Piece sourcePiece, Piece targetPiece) {
        if (targetPiece != null && sourcePiece.isSamePlayer(targetPiece)) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치는 같은 색 기물이 위치하여 이동할 수 없습니다.");
        }
    }

    private void movePiece(Position source, Position target) {
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
}
