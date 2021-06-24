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

	<title>Display Topics</title>
</head>

<body>
	<h3 class='text-center pt-5 mb-4'>
		<a href='DisplayForums'>All Forums</a> &gt; ${currentForum.name}
	</h3>

	<!-- display the name, author, number of replies, and last post date for each topic within the current forum -->
	<div class='container-fluid col-sm-8'>
		<table class='table table-bordered'>
				<thead class='thead-light'>
					<tr>
						<th scope='col'>Topic</th>
						<th scope='col'>Author</th>
						<th scope='col'>Replies</th>
						<th scope='col'>Last Post</th>
					</tr>
				</thead>
				<tbody>
					<!-- loop through each topic within current forum -->
					<c:forEach items="${currentForum.topics}" var="topic" varStatus="loop">
					<tr>
						<th scope="row"><!-- each topic name forwards to display topic -->
							<a href='DisplayTopic?forumIndex=${forumIndex}&topicIndex=${loop.index}'>${topic.subject}</a>
						</th>
						<td>${topic.firstPost.author}</td>
						<td>${topic.numberReplies}</td>
						<td>
							<fmt:formatDate pattern="M/dd/yyyy hh:mm a" value="${topic.lastPost.date}"/>
						</td>
					</tr>
					</c:forEach>
				</tbody>
		</table>
	</div>

	<!-- link to create new topic -->
	<div class='text-center'>
		<a href='CreateTopic?forumIndex=${forumIndex}'>Create Topic</a>
	</div>
</body>