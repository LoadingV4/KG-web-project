function confirmDelete() {
    var result = confirm("정말 탈퇴하시겠습니까?\n탈퇴시, 복구가 불가능합니다.");
    if (result) {
        var password = document.getElementById('passwordInput').value;
        fetch('/user/delete', {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ password: password })
        })
            .then(response => {
                return response.json().then(data => ({ status: response.status, body: data }));
            })
            .then(({ status, body }) => {
                if (status >= 200 && status < 300) {
                    alert(body.message);
                    window.location.href = '/'; // 탈퇴 후 메인 페이지로 이동
                } else {
                    alert(body.message || 'Unknown error occurred');
                }
            })
            .catch(error => {
                alert('오류가 발생했습니다: ' + error.message);
                console.error('Error:', error);
            });
    } else {
        alert("탈퇴가 취소되었습니다.");
    }
}
