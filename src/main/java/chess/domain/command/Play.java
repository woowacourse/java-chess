package chess.domain.command;

import java.util.List;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;

public class Play implements CommandStatus {

    private final Board board;
    private final Turn turn;

    public Play(final Board board, Turn turn) {
        this.board = board;
        this.turn = turn;
    }

    @Override
    public CommandStatus start() {
        return new Play(new Board(new Pieces()), Turn.WHITE);
    }

    @Override
    public CommandStatus move(Position sourcePosition, Position targetPosition) {
        checkTurn(sourcePosition);
        board.checkPieceMoveCondition(sourcePosition, targetPosition);
        board.movePiece(sourcePosition, targetPosition);
        Turn oppositeTurn = turn.change();
        return new Play(board, oppositeTurn);
    }

    private void checkTurn(Position sorucePosition) {
        Piece sourcePiece = board.findPieceByPosition(sorucePosition);
        if (!turn.isCorrectTurn(sourcePiece.getSide())) {
            throw new IllegalArgumentException("[ERROR] 현재 턴인 진영의 기물만 이동할 수 있습니다.");
        }
    }

    @Override
    public CommandStatus end() {
        return new End();
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public List<Piece> getPieces() {
        return board.getPieces();
    }

    public String getTurnDisplayName() {
        return turn.getDisplayName();
    }
}
