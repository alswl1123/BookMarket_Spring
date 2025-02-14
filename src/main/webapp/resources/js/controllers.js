function addToCart(action) {
	document.addForm.action = action;
	document.addForm.submit();
	alert("도서가 장바구니에 추가되었습니다!");
}

function removeFromCart(action) {
	document.removeForm.action = action;
	document.removeForm.submit();
	window.location.reload();
}

function clearCart() {
	document.clearForm.submit();
	window.location.reload();
}

function deleteConfirm(id) { //요청된 도서를 삭제할 때 확인 후 삭제하는 자바스크립트
	if (confirm("삭제합니다!!") == true) location.href = "./delete?id=" + id;
	else return;
}