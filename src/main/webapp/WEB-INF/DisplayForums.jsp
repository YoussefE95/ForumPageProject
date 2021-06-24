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

	<title>Display Forums</title>
</head>

<body>
	<h3 class='text-center pt-5 mb-4'>All Forums</h3>

	<!-- display the name of each forum and the number of topics per forum -->
	<div class='container-fluid col-sm-6'>
		<table class='table table-bordered'>
			<thead class='thead-light'>
				<tr>
					<th scope='col'>Forum</th>
					<th scope='col'>Topics</th>
				</tr> 
			</thead>
			<tbody>
				<!-- loop through each forum -->
				<c:forEach items="${forums}" var="forum" varStatus="loop">
				<tr>
					<th scope="row"><!-- each forum name forwards to display topics -->
						<a href='DisplayTopics?forumIndex=${loop.index}'>${forum.name}</a>
					</th>
					<td>${forum.numberTopics}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<!-- link to create a new forum -->
	<div class='text-center'>
		<a href='CreateForum'>Create Forum</a>
	</div>
</body>
</html>