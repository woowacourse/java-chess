package domain.board;

import domain.dto.PieceDto;
import domain.dto.TurnDto;
import domain.piece.Color;
import domain.piece.Empty;
import domain.piece.Piece;
import domain.piece.Type;
import domain.position.Position;
import domain.position.Route;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {
    private final Map<Position, Piece> board;
    private Color turn;

    public ChessBoard(Map<Position, Piece> board, Color color) {
        this.board = board;
        this.turn = color;
    }

    public ChessBoard(Map<Position, Piece> board) {
        this(new HashMap<>(board), Color.WHITE);
    }

    public void move(final Position source, final Position target) {
        validateEmptyPiece(source);
        validateTurn(source);
        validateEmptyRoute(source, target);
        validateLegalMove(source, target);
        movePiece(source, target);
        changeTurn();
    }

    private void validateEmptyPiece(final Position source) {
        final Piece piece = findPieceByPosition(source);
        if (piece.color().isNeutrality()) {
            throw new IllegalArgumentException("피스가 없습니다.");
        }
    }

    private void validateTurn(final Position source) {
        final Piece piece = findPieceByPosition(source);
        if (this.turn.isOpposite(piece.color())) {
            throw new IllegalArgumentException("상대 턴입니다.");
        }
    }

    private void validateEmptyRoute(final Position source, final Position target) {
        final Route route = Route.create(source, target);
        if (route.isBlocked(board)) {
            throw new IllegalArgumentException("중간에 말이 있어서 이동할 수 없습니다.");
        }
    }

    private void validateLegalMove(final Position source, final Position target) {
        final Piece resourcePiece = findPieceByPosition(source);
        resourcePiece.validateMovement(source, target, findPieceByPosition(target));
    }

    private void movePiece(final Position source, final Position target) {
        final Piece piece = findPieceByPosition(source);
        board.remove(source);
        board.put(target, piece);
    }

    private void changeTurn() {
        if (this.turn.isBlack()) {
            this.turn = Color.WHITE;
            return;
        }
        this.turn = Color.BLACK;
    }

    private Piece findPieceByPosition(final Position position) {
        return board.getOrDefault(position, Empty.getInstance());
    }

    public boolean isKingNotExist() {
        return board.values().stream()
                .filter(piece -> piece.type() == Type.KING)
                .count() != 2;
    }

    public Score calculateScore() {
        return Score.calculate(board);
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    public List<PieceDto> getPieces() {
        return board.entrySet().stream()
                .map(entry -> PieceDto.of(entry.getKey(), entry.getValue()))
                .toList();
    }

    public TurnDto getTurn() {
        return new TurnDto(this.turn.name());
    }
}
