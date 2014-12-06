<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>SpaceProbe</title>
<style type="text/css">

.row {
    width: ${rowWidth};
    height: ${cellDimensions};
}

.row div {
    width: ${cellDimensions};
    height: ${cellDimensions};
    outline: 1px solid;
    float: left;
}

.celltype_0 {
	background-color: #000000;
}

.celltype_1 {
	background-color: #663300;
}

.celltype_2 {
	background-color: 	#194719;
}

.celltype_3 {
	background-color: 	#335C33;
}

.celltype_4 {
	background-color: 	#ff0000;
}

.container  {
	width: 100%; 
    margin: 0 auto;
}
</style>
</head>
<body>

<div class="container">
<div style="width: 600px; float: left;">
<c:forEach var="row" items="${grid}" >
	<div class="row">
    <c:forEach var="col" items="${row}" >
        <div class="celltype_${col}"></div>
    </c:forEach>
    </div>
</c:forEach>
</div> 
<div style="margin-left: 620px;">
<c:forEach var="row" items="${gridWithPath}" >
	<div class="row">
    <c:forEach var="col" items="${row}" >
        <div class="celltype_${col}"></div>
    </c:forEach>
    </div>
</c:forEach>
</div>
</div>
</body>
</html>