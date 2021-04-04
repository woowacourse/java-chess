package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import chess.domain.piece.Team;

import java.util.Map;

import static chess.controller.ChessController.boardFactory;

public class ChessGame {
    private Board board;
    private Team turnOwner;

    public void settingBoard() {
        board = boardFactory.create();
        turnOwner = Team.WHITE;
    }

    public void move(String target, String destination) {
        boardInitializeCheck();
        turnOwner = board.movePiece(convertStringToPosition(target),
                convertStringToPosition(destination), turnOwner);
    }

    public String scoreStatus() {
        boardInitializeCheck();
        double whiteScore = board.calculateScore(Team.WHITE);
        double blackScore = board.calculateScore(Team.BLACK);
        return "백 : " + whiteScore + "  흑 : " + blackScore;
    }

    private Position convertStringToPosition(String input) {
        return Position.convertStringToPosition(input);
    }

    private void boardInitializeCheck() {
        if (board == null) {
            throw new IllegalArgumentException("보드가 세팅되지 않았습니다. start 명령어를 입력해주세요.");
        }
    }

    public void loadSavedBoardInfo(Map<String, String> boardInfo, String turnOwner) {
        board = BoardFactory.loadSavedBoardInfo(boardInfo);
        this.turnOwner = Team.convertStringToTeam(turnOwner);
    }

    public Board getBoard() {
        return board;
    }

    public Team getTurnOwner() {
        return turnOwner;
    }
}
