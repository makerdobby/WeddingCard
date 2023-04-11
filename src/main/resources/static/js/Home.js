/**
 * main javascript
 */
$(document).ready(function(){
	ajaxGuestBook(1);

	/** 계좌번호 복사 */
	var clipboard = new Clipboard(".copy-btn");

	clipboard.on('success', function(){
      alert("텍스트 복사가 완료되었습니다.");
    });

    clipboard.on('error', function() {
      alert("Ctrl + C 를 눌러서 복사해 주세요.");
    });


});

function ajaxGuestBook(curPage){
	$.ajax({
		url : '/wedding/ajax',
		type :"GET",
		data : {curPage: curPage},
		success: function(fragment){
			$('#ajaxGuestBook').html(fragment);
		},error: function(){
			console.log("실패");
		}
	});
}

/** 축하글 */

// 축하글 작성
function doGuestInsert(){
	document.wFrm01.action="/guest/insert";
	document.wFrm01.method ="post";
	document.wFrm01.submit();
}

function openModal(){
	$("#modal-background").css('display','block');
	$("#modal-box").css('display','block');
}

function closeModal(){
	$("#modal-background").css('display','none');
	$("#modal-box").css('display','none');
}

// 수정
function doGuestUpdate(){
	document.wFrm03.action="/guest/update";
	document.wFrm03.method ="post";
	document.wFrm03.submit();
}

// 삭제
function doGuestDelete(){

	if(confirm("삭제하시겠습니까?")){
		document.wFrm03.action="/guest/delete";
		document.wFrm03.method ="post";
		document.wFrm03.submit();
	}
}

/** 댓글 */

// 댓글 작성
function doGuestReplyInsert(){

	document.wFrm02.action="/reply/insert";
	document.wFrm02.method ="post";
	document.wFrm02.submit();
}
function openModalReply( gId ){

	// gId를 넘김
	$('#inputGId').val(gId);

	$("#modal-background").css('display','block');
	$("#modal-box-reply").css('display','block');
}

function closeModalReply(){
	$("#modal-background").css('display','none');
	$("#modal-box-reply").css('display','none');
}

// 글 수정
function doReplyUpdate(){
	document.wFrm04.action="/reply/update";
	document.wFrm04.method ="post";
	document.wFrm04.submit();
}

// 글 삭제
function doReplyDelete(){

	if(confirm("삭제하시겠습니까?")){
		document.wFrm04.action="/reply/delete";
		document.wFrm04.method ="post";
		document.wFrm04.submit();
	}
}

/** 계좌 */
function openModalAcc1(){

	$("#modal-background").css('display','block');
	$("#modal-box-acc1").css('display','block');
}

function closeModalAcc1(){
	$("#modal-background").css('display','none');
	$("#modal-box-acc1").css('display','none');
}
function openModalAcc2(){

	$("#modal-background").css('display','block');
	$("#modal-box-acc2").css('display','block');
}

function closeModalAcc2(){
	$("#modal-background").css('display','none');
	$("#modal-box-acc2").css('display','none');
}


