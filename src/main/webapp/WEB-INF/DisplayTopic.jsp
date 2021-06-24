<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">

	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css"
		integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
		integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">

	<title>Display Topic</title>
</head>

<body>
	<h3 class='text-center pt-5 mb-4'>
		<a href='DisplayForums'>All Forums</a>
		&gt; <a href='DisplayTopics?forumIndex=${forumIndex}'>${currentForumName}</a>
		&gt; ${currentTopic.subject}
	</h3>
		
	<!-- display all posts within current topic (will always be at least the initial post) -->
	<div class='container-fluid col-sm-10'>
		<table class='table table-bordered'>
			<thead class='thead-light'>
				<tr>
					<th scope='col'>Author</th>
					<th scope='col'>Content</th>
					<th scope='col'>Posted On</th>
				</tr>
			</thead>
			<tbody>
				<!-- loop through each post within current topic -->
				<c:forEach items="${currentTopic.posts}" var="post" varStatus="loop">
				<tr>
					<td>${post.author}</td>
					<td>${post.content}</td>
					<td><fmt:formatDate pattern="M/dd/yyyy hh:mm a" value="${post.date}"/></td>
				</tr>
				</c:forEach>
			</tbody>
		</table><br>
	</div>

	<!-- form for posting a reply -->
	<div class='container-fluid col-sm-9 col-sm-offset-4 pt-3 border'>
		<form method='post'>
			<div class='form-group'>
				<label for='author' class='form-label'>Your Name&colon;</label>
				<input type='text' class='form-control' id='author' name='author'>
			</div>
			<div class='form-group'>
				<label for='content' class='form-label'>Reply&colon;</label>
				<textarea name='content' class='form-control' id='content' rows='10' col='40'></textarea>
			</div>
			<div class='text-center pb-3'>
				<button type="submit" class="btn btn-primary">Post</button>
			</div>
		</form>
	</div>
</body>