function confirmDelete() {
  var result = confirm("정말 탈퇴하시겠습니까?\n탈퇴시, 복구가 불가능합니다.");
  if (result) {
      window.location.href = '/confirmdelte';
      // 임의로 붙인 탈퇴 페이지명
      // 나중에 탈퇴 페이지 만들어서 구현
      // 탈퇴 페이지에 들어가야할 것 : 비밀번호 다시 확인, 비밀번호를 확인했으면 정말 탈퇴할거냐고 다시 묻고 확인 누르면 탈퇴하기
  } else {
      alert("탈퇴가 취소되었습니다."); 
  }
}

function confirmUpdate() {
  var result = confirm("정보를 변경하시겠습니까?");
  if (result) {
      alert("변경되었습니다."); 
      // 이자리에 탈퇴 로직 추가
  } else {
      alert("변경이 취소되었습니다."); 
  }
}
function toggleDropdown() {
  const dropdownContent = document.getElementById("myDropdown");
  dropdownContent.style.display =
      dropdownContent.style.display === "block" ? "none" : "block";
}

function closeDropdown() {
  const dropdownContent = document.getElementById("myDropdown");
  dropdownContent.style.display = "none";
}

function viewDetail(url) {
  window.open(url, "_blank");
}
