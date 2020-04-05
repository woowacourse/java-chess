package chess.controller;

import chess.controller.dao.ChessBoard;
import chess.controller.dao.ChessBoardDAO;
import chess.controller.dao.PieceDAO;
import chess.controller.dao.PieceOnBoard;
import chess.controller.dto.MoveResultDto;
import chess.controller.dto.TeamDto;
import chess.controller.dto.TileDto;
import chess.domain.ChessRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WebChessController {
    private static final String MESSAGE_STYLE_BLACK = "color:black;";
    private static final String MESSAGE_STYLE_RED = "color:red;";
    private static final String DELIMITER = ": ";
    private static final String NEW_LINE = "\n";
    private static final String ARROW = " -> ";
    private static final String WINNER = " 가 이겼습니다.";

    private ChessRunner chessRunner;
    private ChessBoard chessBoard;

    public void start() throws Exception {
        ChessBoardDAO chessBoardDAO = new ChessBoardDAO();
        PieceDAO pieceDAO = new PieceDAO();
        this.chessBoard = chessBoardDAO.findRecentChessBoard();
        if (this.chessBoard == null) { // 게임을 새로 시작했다면
            this.chessRunner = new ChessRunner();
            chessBoardDAO.addChessBoard();
            this.chessBoard = chessBoardDAO.findRecentChessBoard();
            List<TileDto> tileDtos = this.chessRunner.pieceTileDtos();
            pieceDAO.addPiece(chessBoard.getChessBoardId(), tileDtos);
        } else {
            List<PieceOnBoard> pieces = pieceDAO.findPiece(this.chessBoard.getChessBoardId());
            Map<String, String> pieceOnBoards = pieces.stream()
                    .collect(Collectors.toMap(entry -> entry.getPosition(),
                            entry -> entry.getPieceImageUrl(), (e1, e2) -> e1, HashMap::new));
            this.chessRunner = new ChessRunner(pieceOnBoards);
        }
    }

    public MoveResultDto move(final String source, final String target) {
        try {
            this.chessRunner.update(source, target);
            String moveResult = moveResult(this.chessRunner, source, target);
            return new MoveResultDto(moveResult, MESSAGE_STYLE_BLACK);
        } catch (IllegalArgumentException | StringIndexOutOfBoundsException e) {
            return new MoveResultDto(e.getMessage(), MESSAGE_STYLE_RED);
        }
    }

    private String moveResult(final ChessRunner chessRunner, final String source, final String target) {
        if (!this.isEndGame()) {
            return source + ARROW + target;
        }
        return chessRunner.getWinner() + WINNER;
    }

    public boolean isEndGame() {
        return this.chessRunner.isEndChess();
    }

    public TeamDto getCurrentTeam() {
        return new TeamDto(this.chessRunner.getCurrentTeam());
    }

    public List<TileDto> getTiles() {
        return this.chessRunner.entireTileDtos();
    }

    public String getScores() {
        return this.chessRunner.calculateScores().stream()
                .map(dto -> dto.getTeam() + DELIMITER + dto.getBoardScore())
                .collect(Collectors.joining(NEW_LINE));
    }
}
