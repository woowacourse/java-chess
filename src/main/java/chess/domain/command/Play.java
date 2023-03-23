package chess.domain.command;

import java.util.List;

import chess.controller.dto.GameResultBySideDto;
import chess.controller.dto.ScoreBySideDto;
import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.GameResultBySide;
import chess.domain.board.ResultCalculator;
import chess.domain.board.ScoreBySide;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.position.Position;

public class Play implements CommandStatus {

    private final ChessGame chessGame;
    private final Turn turn;

    public Play(final ChessGame chessGame, Turn turn) {
        this.chessGame = chessGame;
        this.turn = turn;
    }

    @Override
    public CommandStatus start() {
        Board board = new Board(new Pieces());
        ResultCalculator resultCalculator = new ResultCalculator(new ScoreBySide(), new GameResultBySide());
        return new Play(new ChessGame(board, resultCalculator), Turn.WHITE);
    }

    @Override
    public CommandStatus move(Position sourcePosition, Position targetPosition) {
        checkTurn(sourcePosition);
        chessGame.checkPieceMoveCondition(sourcePosition, targetPosition);
        chessGame.movePiece(sourcePosition, targetPosition);
        Turn oppositeTurn = turn.change();
        return new Play(this.chessGame, oppositeTurn);
    }

    private void checkTurn(Position sorucePosition) {
        Piece sourcePiece = chessGame.findPieceByPosition(sorucePosition);
        if (!turn.isCorrectTurn(sourcePiece.getSide())) {
            throw new IllegalArgumentException("[ERROR] 현재 턴인 진영의 기물만 이동할 수 있습니다.");
        }
    }

    @Override
    public CommandStatus end() {
        return new End();
    }

    @Override
    public CommandStatus printGameResult() {
        chessGame.saveScoreBySide();
        chessGame.saveGameResultBySide();
        return new PrintGameResult(chessGame, turn);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public boolean canPrintGameResult() {
        return false;
    }

    @Override
    public List<Piece> getPieces() {
        return chessGame.getPieces();
    }

    @Override
    public String getTurnDisplayName() {
        return turn.getDisplayName();
    }

    @Override
    public ScoreBySideDto getScoreBySide() {
        return new ScoreBySideDto(chessGame.getScoreBySide());
    }

    @Override
    public GameResultBySideDto getGameResultBySide() {
        return new GameResultBySideDto(chessGame.getGameResultBySide());
    }
}
