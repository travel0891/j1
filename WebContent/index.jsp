<%@ page language="java" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<title>test</title>
</head>
<body>
	<form action="login" method="post">
		<input type="text" name="storyAccount" value="storyAccount" /> <input
			type="text" name="storyPasswrod" value="storyPasswrod" />
		<button type="submit">post login</button>
	</form>
	<hr />
	<form action="list" method="post">
		<button type="submit">post list</button>
	</form>
	<hr />
	<form action="reply" method="post">
		<input type="text" name="type" value="type" /> <input type="text"
			name="list_id" value="list_id" /> <input type="text"
			name="reply_text" value="reply_text" /> <input type="text"
			name="begin_id" value="begin_id" /> <input type="text"
			name="reply_id" value="reply_id" />
		<button type="submit">post reply list</button>
	</form>
</body>
</html>