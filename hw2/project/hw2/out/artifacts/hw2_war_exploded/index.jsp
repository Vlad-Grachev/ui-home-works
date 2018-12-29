<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Equations solver</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <script src="jquery-3.3.1.js"></script>
    <script src="scripts.js"></script>
</head>
<body class="bodybackgr">
<div class="maindiv">
    <h1>Welcome to &laquo;Quadratic equations solver&raquo;. Enter coefficients >></h1>
    <div class="equationdiv">
        <input type="text" name="a" class="validimput" size="1"/> x^2 +
        <input type="text" name="b" class="validimput" size="1"/> x +
        <input type="text" name="c" class="validimput" size="1"/> = 0
    </div>
    <button id="calcbutton">Calculate roots</button>
</div>
</body>
</html>
