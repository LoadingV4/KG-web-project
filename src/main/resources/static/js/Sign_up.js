// 'signupForm' 폼의 제출 이벤트를 감지하여 이벤트 핸들러를 추가
document.getElementById('signupForm').addEventListener('submit', function(event) {
    event.preventDefault(); // 폼 제출 시 페이지 리로드 방지

    // 폼 입력 필드의 값을 가져옴
    const name = document.getElementById('name').value;
    const id = document.getElementById('id').value;
    const password = document.getElementById('password').value;
    const email = document.getElementById('email').value;
    const phone = document.getElementById('phone').value;

    // fetch API를 사용하여 '/user/register' 엔드포인트에 POST 요청을 보냄
    fetch('/user/register', {
        method: 'POST', // HTTP 메소드 설정
        headers: {
            'Content-Type': 'application/json' // 요청 본문의 데이터 타입을 JSON으로 설정
        },
        body: JSON.stringify({
            name: name,
            id: id,
            password: password,
            email: email,
            phone: phone
        }) // JSON 문자열로 변환된 요청 본문
    })
        .then(response => response.json()) // 응답을 JSON 형식으로 파싱
        .then(data => {
            if (data.success) {
                // 회원가입 성공 시
                document.getElementById('success-message').style.display = 'block'; // 성공 메시지 표시
                document.getElementById('error-message').innerText = ''; // 오류 메시지 초기화
                setTimeout(() => {
                    window.location.href = '/user/login'; // 3초 후 로그인 페이지로 리다이렉션
                }, 3000); // 3초 대기
            } else {
                // 회원가입 실패 시
                document.getElementById('error-message').innerText = data.message; // 오류 메시지 표시
                document.getElementById('success-message').style.display = 'none'; // 성공 메시지 숨기기
            }
        })
        .catch(error => {
            // 서버 오류 또는 네트워크 오류 발생 시
            console.error('Error:', error); // 오류 콘솔 출력
            document.getElementById('error-message').innerText = '서버 오류가 발생했습니다.'; // 오류 메시지 표시
        });
});

// 로그인 페이지로 리다이렉션하는 함수
function redirectToLogin() {
    window.location.href = "/user/login"; // 로그인 페이지로 이동
}
