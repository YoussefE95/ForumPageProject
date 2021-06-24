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

	<title>Create Topic</title>
</head>

<body>
	<h3 class='text-center pt-5 mb-4'>
		<a href='DisplayForums'>All Forums</a> &gt;
		<a href='DisplayTopics?forumIndex=${forumIndex}'>${currentForum.name}</a> &gt; 
		Create Topic
	</h3>

	<div class='container-fluid col-sm-6 pt-3 border'>
		<form action='CreateTopic?forumIndex=${forumIndex}' method='post'>
			<div class='form-group'>
				<label for='author' class='form-label'>Your Name&colon;</label>
				<input type='text' class='form-control' id='author' name='author'>
			</div>
			<div class='form-group'>
				<label for='subject' class='form-label'>Subject&colon;</label>
				<input type='text' class='form-control' id='subject' name='subject'>
			</div>
			<div class='form-group'>
				<label for='content' class='form-label'>Content&colon;</label>
				<textarea name='content' class='form-control' id='content' rows='10' col='40'></textarea>
			</div>
			<div class='text-center pb-3'>
				<button type="submit" class="btn btn-primary">Create</button>
			</div>
		</form>
	</div>
</body>
</html>