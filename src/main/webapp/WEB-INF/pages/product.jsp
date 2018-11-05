<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>

<head>


    <link rel="stylesheet" type="text/css"
          href="<c:url value="/resources/css/styles.css"/>">

    <script>
        window.onload = function () {
            if(${productObj.discount == 0.0})
                document.getElementById("showPrice").style.visibility = "visible";
            else
                document.getElementById("showDiscount").style.visibility = "visible";
        }

        document.addEventListener('DOMContentLoaded', function() {
            document.getElementById("descriptionField").innerHTML = ${productObj.shortDescription};
        }, false);

        $(document).ready(function()
        {
            $("tr:odd").css({
                "background-color":"#000",
                "color":"#fff"});
        });


    </script>

    <style>
        tbody td{
            padding: 20px;
        }

        tbody tr:nth-child(odd){
            background-color: #F5F5F6;
            color: black;
        }

        .small_underlined {
            font-size: x-small;
            text-decoration: underline;
        }
    </style>

</head>
<body>
<hr>
<div  style="padding:0px 30px; margin-bottom: 20px">

    <h2>Product Details</h2>

    <div style="height: 300px;">
        <div class="divide-2">
            <img
                src="<c:url value="${productObj.largeImage}"/>"
                width="80%" height="100%" alt="${productObj.name}" /></td>
        </div>

        <div  class="divide-2">
            <h4> <b>${productObj.name }</b> </h4>
            <p> <span class="small_underlined"> ${productObj.numReviews} reviews</span>
                <span class="small_underlined">  ${productObj.brandName }</span> </p>

            <c:if test="${productObj.msrp == 0.0}">
                <p><strong>$${productObj.salePrice}</strong></p>
            </c:if>
            <c:if test="${productObj.msrp != 0.0}">

                <strong> $${productObj.salePrice}</strong>
                &nbsp;&nbsp;&nbsp;
                <span style="color: gray; font-size: 13px;"> List </span>  <span class="discountText"> $${productObj.msrp} </span>
            </c:if>

            <div id="selectQty" style="    margin-top: 70px">
                <p>Qty: ${productObj.quantityString}
                    <button type="button" onclick="" style="border-radius: 100px;
                            font-weight: 400;
                            height: 2rem;
                            text-align: center;
                            white-space: nowrap;
                            background-color: #0065ff;
                            color: #fff;
                            margin-left: 15px;">Add to Cart</button></p>
            </div>

        </div>
    </div>
    <hr>

    <h3>About The Product</h3>
        <p id="descriptionField">${productObj.shortDescription}</p>
    <hr>


    <h3>Specifications</h3>

    <table style="width:60%">
        <tr>
            <th></th>
            <th></th>
        </tr>
        <tr>
                <td>Series Title</td>
                <td>${productObj.specification.series_title}</td>
        </tr>
        <tr>
                <td>Publisher</td>
                <td>${productObj.specification.publisher}</td>
        </tr>
        <tr>
                <td>Book Format</td>
                <td>${productObj.specification.book_format}</td>
        </tr>
        <tr>
                <td>Original Languages</td>
                <td>${productObj.specification.original_languages}</td>
        </tr>
        <tr>
            <td>Number of Pages</td>
            <td>${productObj.specification.number_of_pages}</td>
        </tr>
        <tr>
                <td>Author</td>
                <td>${productObj.specification.author}</td>
        </tr>
        <tr>
                <td>ISBN-13</td>
                <td>${productObj.specification.isbn_13}</td>
        </tr>
        <tr>
            <td>Publication Date</td>
            <td>${productObj.specification.publication_date}</td>
        </tr>
        <tr>
            <td>Assembled Product Dimensions (L x W x H)</td>
            <td>${productObj.specification.dimensions}</td>
        </tr>
        <tr>
            <td>ISBN-10</td>
            <td>${productObj.specification.isbn_10}</td>
        </tr>
    </table>
    <hr>





    <h3>Reviews</h3>
    <p> Customer rating: ${productObj.customerRating} / 5</p>
    <c:forEach items="${productObj.reviews}" var="prod">

        <p> <b>${prod.reviewer}</b> ${prod.reviewText}</p>
    </c:forEach>

    <div>

    </div>


</div>


</body>
</html>
<%@ include file="footer.jsp"%>
