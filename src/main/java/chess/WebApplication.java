package chess;

import chess.controller.dto.BoardDto;
import chess.controller.web.WebController;
import chess.dao.BoardDao;
import chess.dao.DBConfig;
import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;

import java.sql.Connection;

public class WebApplication {
    public static void main(String[] args) {
        DBConfig dbConfig = new DBConfig();
        Connection connection = dbConfig.getConnection();
        //

        WebController webController = new WebController();
        webController.mapping();
    }
}
