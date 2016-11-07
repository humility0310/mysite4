<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link
	href="${pageContext.request.contextPath }/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script type="text/javascript">
	var isEnd = false;
	var page = 0;
	var render = function(vo) {
		var htmls = "<li>" + "<strong>" + vo.name + "</strong>" + "<p>"
				+ vo.content + "</p>" + "<strong>" + vo.regDate + "</strong>"
				+ "<a href='&no="+vo.no+"'>삭제</a>" + "</li>"; // js template library -> ejs

		$("#list-guestbook").append(htmls);

	}

	var fetchList = function() {
		if (isEnd == true) {
			return;
		}
		++page;
		$.ajax({
				url : "${pageContext.request.contextPath }/api/guestbook?a=ajax-list&p="
						+ page,
				type : "get",
				dataType : "json",
				data : "",
				success : function(response) {
					if (response.result != "success") {
						console.error(response.message);
						isEnd = true;
						return;
					}

					// redering
					$(response.data).each(function(index, vo) {
						render(vo);
					});

					if (response.data.length < 5) {
						isEnd = true;
						$("#btn-fetch").prop("disabled", true);
					}
				},
				error : function(jqXHR, status, e) {
					console.error(status + ":" + e);
				}
			});
	}

	$(function() {

		$(document).on("click", "#list-guestbook li a", function() {
			event.preventDefault();
			console.log("여기서 비밀번호");

			dialog = $("#dialog-form").dialog({
				//autoOpen : false,
				height : 200,
				width : 350,
				modal : true,
				buttons : {
					"삭제" : function() {
						//$("li:nth-child(1)").remove();
						console.log($(this).attr( "no" ));
						
					},
					"취소" : function() {
						dialog.dialog("close");
					}
				},
				close : function() {
					$('form').each(function() {
						this.reset();
					});
				}
			});
			dialog.dialog();
		});

		$("#add-form").submit(function(event) {
			event.preventDefault();
			// ajax insert
		});

		$(window).scroll(function() {
			var $window = $(this);
			var scrollTop = $window.scrollTop();
			var windowHeight = $window.height();
			var documentHeight = $(document).height();

			// 스크롤 바가 바닥까지 왔을 때( 20px 덜 왔을 때 )
			if (scrollTop + windowHeight + 10 > documentHeight) {
				//console.log( "call fetchList" );
				fetchList();
			}
		});

		$("#btn-fetch").click(function() {
			fetchList();
		});

		// 1번째 리스트 가져오기
		fetchList();
	});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<h1>방명록</h1>
				<form id="add-form" action="/mysite2/add" method="post">
					<input type="text" name="name" placeholder="이름"> <input
						type="password" name="pass" placeholder="비밀번호">
					<textarea name="content" placeholder="내용을 입력해 주세요."></textarea>
					<input type="submit" value="보내기" />
				</form>
				<ul id="list-guestbook">

				</ul>
				<button style="margin-top: 20px;" id="btn-fetch">가져오기</button>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbook-ajax" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>






	<div id="dialog-form" title="Deletete Guestbook" style="display: none;">
		<p class="validateTips">삭제할 비밀번호를 입력해 주세요.</p>
		<form>
			<label for="password">비밀번호</label> 
			<input type="password" name="pw" id="pw" value="" class="text ui-widget-content ui-corner-all"> 
		</form>
	</div>




</body>
</html>