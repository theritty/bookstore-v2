<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp"%>

<html>


<head>

    <link rel="stylesheet" type="text/css"
          href="<c:url value="/resources/css/styles.css"/>">

</head>
<body >
<hr>
<div style="padding:0px 30px; margin-bottom: 20px">
    <h4> Products </h4>

    <table style="width:100%">
        <tr>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
        </tr>

        <c:forEach items="${storeResponse.items}" var="prodRow">
            <tr>
                <c:forEach items="${prodRow}" var="prod">
                    <td style="width: 18%;padding-right: 1%;">
                        <a href="/product/${prod.itemId}" style="text-decoration: none">
                            <div style="height:17px;">
                                <c:if test="${prod.is_best_seller}">
                                    <button style="border-color: #086798;border-radius: 73px; font-size: x-small;color: #086798;margin-left: 1px;margin-bottom: 5px;"> Bestseller</button>
                                </c:if>
                                <c:if test="${prod.is_new}">
                                    <button style="border-color: #086798;border-radius: 73px; font-size: x-small;color: #086798;margin-left: 1px;margin-bottom: 5px;"> New</button>
                                </c:if>
                            </div>
                        </a>
                    </td>
                </c:forEach>
            </tr>
            <tr>
                <c:forEach items="${prodRow}" var="prod">
                    <td style="width: 18%;padding-right: 1%;">
                        <a href="/product/${prod.itemId}" style="text-decoration: none">

                            <img src="<c:url value="${prod.thumbnailImage}"/>" alt="Norway" style="width: 80%; padding-left: 10%; height: 300px;"
                                 class="w3-hover-opacity">
                        </a>

                    </td>
                </c:forEach>
            </tr>
            <tr>
                <c:forEach items="${prodRow}" var="prod">
                    <td style="width: 18%;padding-right: 1%;">
                        <a href="/product/${prod.itemId}" style="text-decoration: none">
                            <div style="color: black">
                                <p style="height:40px;margin-left: 1px;margin-bottom: 5px;"><b>${prod.name}</b></p>
                            </div>
                        </a>

                    </td>
                </c:forEach>
            </tr>
            <tr style="padding-bottom: 25px;">
                <c:forEach items="${prodRow}" var="prod">
                    <td style="width: 18%;padding-right: 1%; padding-bottom: 25px;">
                        <a href="/product/${prod.itemId}" style="text-decoration: none">

                            <div style="color: black; margin-bottom: 30px;">
                                <c:if test="${prod.msrp == null}">
                                    <p><strong>$${prod.salePrice}</strong></p>
                                </c:if>
                                <c:if test="${prod.msrp != null}">

                                    <strong> $${prod.salePrice}</strong>
                                    &nbsp;&nbsp;&nbsp;
                                    <span style="color: gray; font-size: 13px;">
                                        List </span>  <span class="discountText"> $${prod.msrp} </span>
                                </c:if>
                            </div>
                        </a>
                    </td>
                </c:forEach>
            </tr>
        </c:forEach>


    </table>




    <hr>


    <div style="text-align:center;width:100%;">

        <c:if test="${storeResponse.currentPage > 1}">
            <a href="/store/${storeResponse.currentId-1}" style="text-decoration:  none; margin:auto;">
                Prev
            </a>
        </c:if>

        <c:forEach items="${storeResponse.pages}" var="page">
            <a href="/store/${page}" style="text-decoration:  none; margin:auto;">

                <c:if test="${storeResponse.currentPage == page}">
                    <strong> ${page}</strong>
                </c:if>
                <c:if test="${storeResponse.currentPage != page &&
                ((storeResponse.currentPage - page <=20 && storeResponse.currentPage - page >0) ||
                 (storeResponse.currentPage - page >=-20 && storeResponse.currentPage - page <0))}">
                    ${page}
                </c:if>
            </a>
        </c:forEach>

        <c:if test="${storeResponse.currentPage < storeResponse.pages.size()}">
            <a href="/store/${storeResponse.currentPage + 1}" style="text-decoration:  none; margin:auto;">
                Next
            </a>
        </c:if>
    </div>
</div>
</body>
</html>
<%@ include file="footer.jsp"%>
