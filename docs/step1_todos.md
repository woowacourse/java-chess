# 1차 피드백 리팩토링 목록

- [ ] 캐싱된 ERROR의 처리 (범위를 벗어나는 경우의 체크로직)
- [ ] 예외 메세지 적용
- [x] 추상클래스인데 폰만을 위한 메서드 정리
- [ ] 각 piece가 Position을 가져야 할까?
- [ ] instanceOf 내부화
- [x] column, row Enum으로 변경
- [x] 사용하지 않는 메소드에 한해 UnsupportedOperationException 처리
- [ ] com.google.common.collect.Table를 사용하여 테이블 형태의 자료구조 사용가능