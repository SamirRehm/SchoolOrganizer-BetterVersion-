<!doctype html>
<html lang="en">
   <head>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>jQuery UI Sortable - Connect lists</title>
      <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
      <link rel="stylesheet" href="/resources/demos/style.css">
      <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
      <style>
         #sortable1, #sortable2 {
         border: 1px solid #eee;
         width: 270px;
         min-height: 20px;
         list-style-type: none;
         margin: 0;
         padding: 5px 0 0 0;
         float: left;
         margin-right: 10px;
         }
         #sortable1 li, #sortable2 li {
         margin: 0 5px 5px 5px;
         padding: 5px;
         font-size: 1.2em;
         width: 250px;
         color: blue;
         text-align: center;
         }
         #footer {
         position:absolute;
         bottom:0;
         width:100%;
         height:80px;   /* Height of the footer */
         background:#6cf;
         }
         .button {
         border-radius: 4px;
         border: none;
         color: #FFFFFF;
         text-align: center;
         font-size: 14px;
         padding: 10px;
         width: 115px;
         height: 35px;
         transition: all 0.5s;
         cursor: pointer;
         margin: 5px;
         }
         .button span {
         cursor: pointer;
         display: inline-block;
         position: relative;
         transition: 0.5s;
         }
         .button span:after {
         content: '\00bb';
         position: absolute;
         opacity: 0;
         top: 0;
         right: -20px;
         transition: 0.5s;
         }
         .button:hover span {
         padding-right: 25px;
         }
         .button:hover span:after {
         opacity: 1;
         right: 0;
         }
         #mainselection select {
         border: 0;
         color: #EEE;
         background: transparent;
         font-size: 20px;
         font-weight: bold;
         padding: 2px 10px;
         width: 170px;
         *width: 158px;
         *background: #58B14C;
         -webkit-appearance: none;
         }
         #mainselection {
         overflow:hidden;
         width:170px;
         -moz-border-radius: 9px 9px 9px 9px;
         -webkit-border-radius: 9px 9px 9px 9px;
         border-radius: 9px 9px 9px 9px;
         box-shadow: 1px 1px 11px #330033;
         background: #58B14C url("http://i62.tinypic.com/15xvbd5.png") no-repeat scroll 319px center;
         }
         #priority1 {
             background-color: rgba(255,0,0,0.3);
         }
         #priority2 {
             background-color: rgba(0,255,0,0.3);
         }
         #priority3 {
             background-color: rgba(0,0,255,0.3);
         }
      </style>
      <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
      <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
      <script>
         $( function() {
           $( "#sortable1, #sortable2" ).sortable({
             connectWith: ".connectedSortable"
           }).disableSelection();
         } );
      </script>
   </head>
   <body>
      <div style="height: 500px;">
         <c:forEach items="${Statuses}" var="item">
            <ul id="sortable1" class="connectedSortable">
               <li class="ui-state-default">${item.getName()}</li>
               <c:forEach items="${item.getTasks()}" var="task">
                  <li style=" " id="priority${task.getPriority()}" class="ui-state-default">${task.getName()} <br> P${task.getPriority()} <br> ${task.getTimeSinceCreationString()}</li>
               </c:forEach>
            </ul>
         </c:forEach>
      </div>
      <div style="width: 170px; float: left">
         <form action="/addStatus">
            <div style="width: 170px; float: left">
               Status name: <input type="text" name="statusName"><br>
               <div id="button" style="text-align: center; margin: auto; float: left">
                  <button class="button" style="background-color: chartreuse"><span> Add Status </span></button>
               </div>
            </div>
      </div>
      </form>
      <div style="width: 170px; float: left">
         <form action="/addTask">
            <div style="width: 170px; float: left">
               Task name: <input type="text" name="taskName"><br>
               Task priority: <input type="number" name="priority"><br>
               Status: <input type="text" name="statusName"><br>
               <div id="button" style="text-align: center; margin: auto; float: left">
                  <button class="button" style="background-color: blue; opacity: 0.5; font-weight: bold"><span> Add Task </span></button>
               </div>
            </div>
      </div>
      </form>
      <div style="width: 170px; float: left">
         <form action="/moveTask">
            <div style="width: 170px; float: left">
               Task name: <input type="text" name="taskName"><br>
               Destination Status: <input type="text" name="destinationStatusName"><br>
               <div id="button" style="text-align: center; margin: auto; float: left">
                  <button class="button" style="width: 120px; background-color: violet; opacity: 0.5; font-weight: bold"><span> Move Task </span></button>
               </div>
            </div>
      </div>
      </form>
      <div style="width: 170px; float: left">
         <form action="/removeTask">
            <div style="width: 170px; float: left">
               Task name: <input type ="text" name="taskName"><br>
               <div id="button" style="text-align: center; margin: auto; float: left">
                  <button class="button" style="width: 137px; background-color: red; opacity: 0.5; font-weight: bold"><span> Remove Task </span></button>
               </div>
            </div>
      </div>
      </form>
      <div style="width: 170px; float: left">
         <div style="width: 170px; float: left">
            <form action="/clearStatus">
                     <label>Status Name</label>
                  <div id ="mainselection">
                      <select id = "myList" name="statusName">
                        <c:forEach items="${Statuses}" var="item">
                        <option value = "${item.getName()}">${item.getName()}</option>
                        </c:forEach>
                     </select>
                  </div>
               <div id="button" style="text-align: center; margin: auto; float: left">
                  <button class="button" style="width: 137px; background-color: red; opacity: 0.5; font-weight: bold"><span> Clear Status </span></button>
               </div>
         </div>
      </div>
      </form>
             <div>
                <a href="http://localhost:8080/load"> Load </a> </button>
             </div>
   </body>
</html>