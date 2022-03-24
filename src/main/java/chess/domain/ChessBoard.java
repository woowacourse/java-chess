package chess.domain;

import chess.domain.generator.BoardGenerator;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Collections;
import java.util.List;

public class ChessBoard {

    private final List<List<Piece>> board;
    private Color turn;

    public ChessBoard(BoardGenerator boardGenerator) {
        this.board = boardGenerator.generate();
        this.turn = Color.WHITE;
    }

    public void init() {
        for (BoardSetting boardSetting : BoardSetting.values()) {
            fillPieces(boardSetting);
        }
    }

    private void fillPieces(BoardSetting boardSetting) {
        Piece piece = boardSetting.getPiece();
        List<Position> positions = boardSetting.getPositions();

        for (Position position : positions) {
            fillPiece(piece, position);
        }
    }

    private void fillPiece(Piece piece, Position position) {
        board.get(position.getRankIndex()).set(position.getFileIndex(), piece);
    }

    public void move(String source, String target) {
        Position sourcePosition = new Position(source);
        Position targetPosition = new Position(target);
        validateSamePosition(sourcePosition, targetPosition);

        Piece sourcePiece = findPiece(sourcePosition);
        validateTurn(sourcePiece);

        turn = turn.change();
    }

    private void validateSamePosition(Position sourcePosition, Position targetPosition) {
        if (sourcePosition.equals(targetPosition)) {
            throw new IllegalArgumentException("source 위치와 target 위치는 같을 수 없습니다.");
        }
    }

    private Piece findPiece(Position sourcePosition) {
        int rankIndex = sourcePosition.getRankIndex();
        int fileIndex = sourcePosition.getFileIndex();
        Piece piece = board.get(rankIndex).get(fileIndex);
        validateEmptyPiece(piece);

        return piece;
    }

    private void validateEmptyPiece(Piece piece) {
        if (piece.isEmpty()) {
            throw new IllegalArgumentException("source위치에 기물이 존재하지 않습니다.");
        }
    }

    private void validateTurn(Piece sourcePiece) {
        if (!sourcePiece.isSameColor(turn)) {
            throw new IllegalArgumentException("source 위치의 기물이 본인의 기물이 아닙니다.");
        }
    }

    public List<List<Piece>> getBoard() {
        return Collections.unmodifiableList(board);
    }
}
