// $(".qna-comment").on("click", ".answerWrite input[type=submit]", addAnswer);
$(".answerWrite input[type=submit]").click(addAnswer);

function addAnswer(e) {
  e.preventDefault();

  var queryString = $("form[name=answer]").serialize();

  $.ajax({
    type : 'post',
    url : '/api/qna/addAnswer',
    data : queryString,
    dataType : 'json',
    error: onError,
    success : onSuccess
  });
}

function onSuccess(json, status){
  var answer = json.answer;
  var answerTemplate = $("#answerTemplate").html();
  var template = answerTemplate.format(answer.writer, new Date(answer.createdDate), answer.contents, answer.answerId, answer.answerId);
  $(".qna-comment-slipp-articles").prepend(template);
}

function onError(xhr, status) {
  alert("error");
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


$(".form-delete-answer button[type=submit]").click(delAnswer)

function delAnswer(e) {
	e.preventDefault();
	
	var btnDelete = $(this);
	var queryString = btnDelete.closest("form").serialize();
	
	$.ajax({
	    type : 'post',
	    url : '/api/qna/deleteAnswer',
	    data : queryString,
	    dataType : 'json',
	    error: onError,
	    success : function(json, status){
	    	if (json.result.status) {
	    		btnDelete.closest("article").remove();
	    	} else {
	    		alert(json.result.message)
	    	}
	    }
	  });
}


$(".form-delete-question button[type=submit]").click(delQuestion)

function delQuestion(e) {
	e.preventDefault();
	
	 var smartPhones = [
	                    
	                    'iphone', 'ipad', 'windows ce', 'android', 'blackberry', 'nokia',
	                    'webos', 'opera mini', 'sonyerricsson', 'opera mobi', 'iemobile'
	 ];
	 
	 var isSmartPhone = false;
	 for (var i in smartPhones) {
		 if(navigator.userAgent.toLowerCase().match(new RegExp(smartPhones[i]))) {
			 isSmartPhone = true;
			 break;
	 	 }
	 }
	 
	 var btnDelete = $(this);
	 var queryString = btnDelete.closest("form").serialize();
	 
	 if (isSmartPhone) {
		 $.ajax({
		    type : 'post',
		    url : '/api/qna/deleteQuestion',
		    data : queryString,
		    dataType : 'json',
		    error: onError,
		    success : function(json, status){
		    	alert(json.result.message);
		    }
		  });
	 } else {
		 location.href="/qna/delete?" + queryString;
	 }
}