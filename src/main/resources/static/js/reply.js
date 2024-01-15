//즉시실행함수로 만듦  (function(){ return {}; })()
//키와 메서드를 가진 JavaScript Object를 return함  
//RestFul방식으로 요청하기

//var token = $("meta[name='_csrf']").attr("content");
//var header = $("meta[name='_csrf_header']").attr("content");
 

var replyManager = (function() {

    ///특정board의 댓글가져오기 ==> replies/100, function(responseData){}
	var getAll2 = function(obj, callback2) {
		console.log("get All.....");
		$.getJSON("/reply/list/" + obj, callback2);	//getJSON 요청후 무엇을 할지는 호출한 쪽에서 결정
	};

    //선언적함수
    function beforeSend(xhr){
       xhr.setRequestHeader(header, token);
    }
    //익명함수를 만들어서 변수할당 
    //var beforeSend2 = function (xhr){
    //   xhr.setRequestHeader(header, token);
    //}
    

    //board의 댓글추가 {"bno":11, replyTest:"aa", replyer:"bb"}
	var add2 = function(obj, callback){
		console.log("add.....");
	    $.ajax({
	        //beforeSend: beforeSend,
			type:"post",
			url: "/reply/add/" + obj.bno,
			data: JSON.stringify(obj),
			dataType: "json",
			contentType: "application/json",
			success: callback
	   });
	};
	//댓글수정
    var update2 = function(obj, callback) {
		$.ajax({
		    //beforeSend: beforeSend,
			type: "put",
			url:"/reply/update/" +  obj.bno,
			data:JSON.stringify(obj),
			dataType:"json",
			contentType:"application/json",
			success:callback
		});
    };

    var remove2 = function(obj, callback)  {
	$.ajax({
	    //beforeSend: beforeSend,
		type:  "delete",
		url:  "/reply/delete/"+obj.bno + "/" + obj.rno,
		dataType: "json",
		contentType: "application/json",
		success: callback
	});

  };
  var aa = "홍길동";
   return {myname:aa, age:20,
            work : function(){},
            getAll: getAll2, 
   			add: add2, 
   			update: update2, 
   			remove: remove2 };
}) ();