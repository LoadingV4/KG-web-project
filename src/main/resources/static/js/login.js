document.addEventListener('DOMContentLoaded', function() {
    document.getElementById("loginForm").addEventListener("submit", function(event) {
        event.preventDefault();

        var id = document.getElementById("id").value;
        var password = document.getElementById("password").value;

        fetch('/user/loginService', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: id,
                password: password
            })
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                // 로그인 성공 시 리다이렉션 등 필요한 작업 수행
                window.location.href = '/';
            } else if (data.message === "회원가입을 먼저 해주세요.") {
                document.getElementById("error-message").textContent = data.message;
                document.getElementById("signup-link").style.display = "inline-block"; // 회원가입 링크를 보이게 함
            } else {
                document.getElementById("error-message").textContent = "Invalid username or password";
            }
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById("error-message").textContent = "아이디나 비밀번호가 일치하지 않습니다";
        });
    });

    document.getElementById("signupBtn").addEventListener("click", function() {
        // 회원가입 페이지로 이동
        window.location.href = "/user/signup";
    });
});
