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

public class PrintGameResult implements CommandStatus {

    private final ChessGame chessGame;
    private final Turn turn;

    public PrintGameResult(final ChessGame chessGame, Turn turn) {
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
        return new End(chessGame.getResultCalculator());
    }

    @Override
    public CommandStatus printGameResult() {
        throw new IllegalStateException("[ERROR] 게임 결과 출력 상태에서는 다시 게임 결과를 출력할 수 없습니다.");
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public boolean isPrintGameResult() {
        return true;
    }

    @Override
    public List<Piece> getPieces() {
        throw new IllegalStateException("[ERROR] 게임 결과 출력 상태에서는 기물들을 반환할 수 없습니다.");
    }

    @Override
    public String getTurnDisplayName() {
        throw new IllegalStateException("[ERROR] 게임 결과 출력 상태에서는 턴 이름을 반환할 수 없습니다.");
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
