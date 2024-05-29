1. 네이버 로그인 기능에서 사용자 정보 불러오는거 구현해놓기
   - https://developers.naver.com/docs/login/devguide/devguide.md
2. 유저 관리 기능
   - 네이버 로그인 api 사용해서 json 형식의 유저 정보 리턴 값 받기 
     - 리턴 된 json 에서 필요한 내용만 추출해서 db에 저장
     - https://developers.naver.com/docs/login/web/web.md
     - 회원 정보 수정, 삭제
3. 카카오 로그인 기능(네이버와 똑같은 방식으로)
4. 찜, 리스트 넣기 버튼 클릭 시 온클릭 함수 이용 백엔드로 포워딩(데이터가 컨트롤러로 가는 것)
     - 컨트롤러를 타고 가서 디비로 저장 후 포워드 기능 사용해서 저장한 정보 조회 후 프론트에 전송해서 띄우기
     - 리스트에서 경로 설정, 순서 바꾸기