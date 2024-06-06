document.getElementById('signupForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const name = document.getElementById('name').value;
    const id = document.getElementById('id').value;
    const password = document.getElementById('password').value;
    const email = document.getElementById('email').value;
    const phone = document.getElementById('phone').value;

    fetch('/user/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: name,
            id: id,
            password: password,
            email: email,
            phone: phone
        })
    })
        .then(response => response.json())
        .then(data => {
            console.log(data); // 서버 응답을 확인하기 위해 콘솔에 출력
            if (data.success) {
                document.getElementById('success-message').style.display = 'block';
                document.getElementById('error-message').innerText = '';
                setTimeout(() => {
                    window.location.href = '/user/login';
                }, 3000); // 3초 후에 로그인 페이지로 이동
            } else {
                document.getElementById('error-message').innerText = data.message || '알 수 없는 오류가 발생했습니다.';
                document.getElementById('success-message').style.display = 'none';
            }
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById('error-message').innerText = '서버 오류가 발생했습니다.';
        });
});

function redirectToLogin() {
    window.location.href = "/user/login";
}
