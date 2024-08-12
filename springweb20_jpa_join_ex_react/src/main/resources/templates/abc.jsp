<%@page import="pack.entity.Buser"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>

<%@page import="pack.entity.Jikwon"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
    // Model에서 가져온 jikwonList
    List<Jikwon> jikwonList = (List<Jikwon>) request.getAttribute("list");

    // JSON 형식으로 변환
    JSONArray jsonArray = new JSONArray();
    for (Jikwon jikwon : jikwonList) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sabun", jikwon.getJno());
        jsonObject.put("name", jikwon.getJname());
        jsonObject.put("dept", jikwon.getBuser());
        jsonObject.put("position", jikwon.getJik());

        jsonArray.put(jsonObject);
    }

    String jsonOutput = jsonArray.toString();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Jikwon List</title>
</head>
<body>
<h3>JSON 형식의 직원 리스트</h3>
<pre><%= jsonOutput %></pre>
</body>
</html>
