<%@include file="/libs/foundation/global.jsp"%>
<script>
 $(document).ready(function(){
 	setInterval(function(){cache_clear()},60000);
 });
 function cache_clear()
 {
    window.location.reload(true);
 }
</script>
<style>
    #page-wrap {
     width: 800px;
     margin: 0 auto;
        border: 3px solid #73AD21;
        padding: 10px;
        background-image:
    linear-gradient(
      to right, 
      #0896A8,
      #0896A8 15%,
      #0896A8 15%,
      #82C8D1 50%,
      #82C8D1 50%
    );
}
</style>
<%

com.mycompany.myrestservice.Accuweather cs = sling.getService(com.mycompany.myrestservice.Accuweather.class);
String[] myXMLdata = cs.getLiveweather() ; 
request.setAttribute("myXMLdata", myXMLdata);

%>

<body>
<div id="page-wrap">
    <h3>Live Accuweather</h3><hr>
	<c:forEach  items="${myXMLdata}" var="myXMLdata">
        <c:if test="${not empty myXMLdata}">
    		<p >

                <strong><c:out value="${myXMLdata}" escapeXml="false"/></strong><hr>
    		</p>
        </c:if>
	</c:forEach>
</div>
</body>
