package chess.dao;

import chess.domain.chessboard.ChessBoard;
import chess.domain.piece.Team;

public class ChessGameData {
    private static final String WHITE_MARK = "W";
    private static final String BLACK_MARK = "B";

    private final Team turn;
    private final ChessBoard chessBoard;

    public ChessGameData(final String turnMark, String chessBoard) {
        this.turn = convertMarkToTurn(turnMark);
        this.chessBoard = parseChessBoard(chessBoard);
    }

    private Team convertMarkToTurn(final String turnMark) {
        if (turnMark.equals(WHITE_MARK)) {
            return Team.WHITE;
        }
        if (turnMark.equals(BLACK_MARK)) {
            return Team.BLACK;
        }
        throw new IllegalArgumentException("잘못된 마크입니다.");
    }

    private ChessBoard parseChessBoard(String chessBoard) {
        ChessBoardParser chessBoardParser = ChessBoardParser.from(chessBoard);
        return chessBoardParser.getChessBoard();
    }

    public Team getTurn() {
        return turn;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
