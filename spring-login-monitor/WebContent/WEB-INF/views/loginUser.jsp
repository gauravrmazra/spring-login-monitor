<%@page import="in.javawithgaurav.cache.SessionUserCache"%>
<%@page import="in.javawithgaurav.bean.SessionUser"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
</head>
<body>
	<div class="row">
		<div class="col-md-12">
			<div class="module">
				<div class="heading">
					<h2>List of Logged-in users</h2>
				</div>
				<div class="module-content">
					<div class="table-wrap">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>User</th>
									<th>Logged-in time</th>
								</tr>
							</thead>
							<tbody>
<%
							List<SessionUser> users = SessionUserCache.getInstance().getAll();
							boolean hasRows = (users != null && !users.isEmpty());
							if (hasRows) {
								for (SessionUser user : users) {
%>
									<tr>
										<td>
											<%=user.getUserName()%>
										</td>
										<td>
											<%=user.getLoggedInDateAsString()%>
										</td>
									</tr>
<%
								}
							}
							else {
							    
%>
								<tr>
									<td colspan="2">There is no data to show.</td>
								</tr>
<%
							}
%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>