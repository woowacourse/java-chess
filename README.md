# java-chess
체스 게임 구현을 위한 저장소

##To-do
 - Connection 닫기(try-with-resources)
 - Point.get 별도 예외 처리
 - isKing 리팩토링
##Done
 - isAlly 캐스팅 미필요 처리
 - Abstract 중복 로직 추상화
 - ChessGame play 고려
 - BoardCreator 메서드 명 동사 처리
 - Direction enum
 - e.printStackTrace 안티패턴 처리
 - Jdbc Template
 - Connection 닫기(try-with-resources)
 - DataBaseConnector sout 처리
 - 커넥션 실패에 대한 별도 예외 처리
 - List<List<String>> 처리
 - SelectJdbcTemplate 추상클래스 대신 함수형 인터페이스
 - UpdateJdbcTemplate 클래스 메서드 선언 or 싱글턴 적용