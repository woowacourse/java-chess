package domain.game.state;

import domain.board.ChessBoard;
import domain.piece.Color;
import domain.position.Position;

public class BlackPlay extends GameState {
    protected BlackPlay(ChessBoard board) {
        super(board);
    }

    @Override
    public GameState start() {
        throw new UnsupportedOperationException("이미 게임이 시작되었습니다.");
    }

    @Override
    public GameState move(Position source, Position target) {
        Color sourcePieceColor = chessBoard().getPieceColor(source);
        if (!sourcePieceColor.isBlack()) {
            throw new IllegalArgumentException("검은색 기물만 이동할 수 있습니다.");
        }
        chessBoard().movePiece(source, target);
        return new WhitePlay(chessBoard());
    }

    @Override
    public GameState end() {
        return new End(chessBoard());
    }
}
