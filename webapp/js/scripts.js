//
// show.jsp 매칭
// 답변 추가하면 목록 갱신
$(".answerWrite input[type=submit]").click(addAnswer); 

function addAnswer(e) { 
	e.preventDefault(); 
	var queryString = $("form[name=answer]").serialize(); 
	$.ajax({ type : 'post', url : '/api/qna/addanswer', data : queryString, dataType : 'json', error: onError, success : onSuccess }); 
} 

function onSuccess(json, status) { 
	var answerTemplate = $("#answerTemplate").html(); 
	var template = answerTemplate.format(json.writer, new Date(json.createdDate), json.contents, json.answerId, json.answerId); 
	$(".qna-comment-slipp-articles").prepend(template); 
} 

function onError(xhr, status) {
	alert("error"); 
}

//
// show.jsp 매칭
// 모든 삭제를 매칭
$(".form-delete input[type=submit]").click(delAnswer);

function delAnswer(e) {
	e.preventDefault();
	var queryString = $(this).closest('form').serialize();
	$.ajax({ type : 'post', url : '/api/qna/delanswer', data : queryString, dataType : 'json', error: onError, success : onSuccess2 });
}

function onSuccess2(json, status) { 
	var answerTemplate = $("#answerTemplate").html(); 
	var template = answerTemplate.format(json.writer, new Date(json.createdDate), json.contents, json.answerId, json.answerId); 
	$(".qna-comment-slipp-articles").prepend(template); 
} 

String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};