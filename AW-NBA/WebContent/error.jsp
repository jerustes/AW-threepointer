<%@ page isErrorPage="true" import="java.io.*" contentType="text/plain"%>

<%	session.invalidate();	%>
<% 	HttpSession nsession = request.getSession(false);
	if(nsession!=null) {
		String data=(String)session.getAttribute( "fname" );
		out.println(data);
	} else {
		out.println("Session is not active");
	}
%>

Message: <%= exception.getMessage()%>

StackTrace: <%
	StringWriter stringWriter = new StringWriter();
	PrintWriter printWriter = new PrintWriter(stringWriter);
	exception.printStackTrace(printWriter);
	out.println(stringWriter);
	printWriter.close();
	stringWriter.close();
%>